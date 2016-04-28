import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import team.Run;
import team.TimeManager;

public class WebServer {

	// a shared area where we get the POST data and then use it in the other handler
	public static Gson gson = new Gson();
	public Map<Integer, GRun> runs = new HashMap<Integer, GRun>();
	protected HttpServer serv;
	protected TimeManager t = new TimeManager();
	protected GRun latestRun;
	
	static Map<Integer, String> racerNames = new HashMap<Integer, String>();

	public WebServer() {
		// set up a simple HTTP server on our local host
		try{
			serv = HttpServer.create(new InetSocketAddress(8000), 0);

			// create a context to get the request to display the results
			serv.createContext("/display", new DisplayLastHandler());

			serv.createContext("/display/mystyle.css", new CSSHandler());

			// create a context to get the request for the POST
			serv.createContext("/sendresults",new PostHandler());
			
			serv.createContext("/clear", new ClearHandler());

			serv.setExecutor(null); // creates a default executor

		} catch(Exception e)
		{
			e.printStackTrace(System.out);
		}
	}

	private class GRun{
		private int id;
		private long epoch;
		private GRecord[] records;
	}

	private class GRecord {
		private boolean isRacer;
		private int run, racer;
		private long start,finish;
		private boolean ended;
		
		public boolean didNotFinish() {
			return ended && finish == -1;
		}
	}

	public static void main(String[] args) {
		// get it going
		WebServer server = new WebServer();
		server.start();
	}

	public void start() {
		// TODO Auto-generated method stub
		serv.start();
		System.out.println("Server started");
	}
	
    private class ClearHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {

            String response;
            
            response = "Cleared directory.";
            
            // write out the response
            OutputStream os = t.getResponseBody();
            
            runs.clear();
            
            os.write(response.getBytes());
            os.close();
        }
    }

	private class DisplayLastHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			t.getResponseHeaders().set("Content-Type", "text/html");
			System.out.println("Sending results page.");
			String response = sendResultsPage(latestRun);
			//String response = "This is a test.";
			t.sendResponseHeaders(200, response.length());
			// write out the response
			OutputStream os = t.getResponseBody();

			os.write(response.getBytes());
			os.close();
			System.out.println("Finished sending results page.");
		}
	}

	private String sendResultsPage(GRun run) {
		String response = "<link rel=\"stylesheet\" type=\"text/css\" href=\"mystyle.css\">";

		response += "<table><tbody><tr> <th>Racer</th> <th>Start</th> <th>Finish</th> <th>Department</th> <th>Phone</th> <th>Gender</th> </tr>";
		
		t.setEpoch(run.epoch);
		
		for(GRecord r : run.records) {
			response += "<tr>";
			response += "<td>";
			if(r.isRacer) {
				response += "Racer ";
			} else {
				response += "PlaceHolder ";
			}
			response += r.racer + "</td><td>" + t.formatTime(r.start) + "</td><td>" + t.formatTime(r.finish) + "</td><td>" + r.finish + "</td><td>" + r.finish + "</td><td>" + r.finish + "</td>\n";
			response += "</tr>";
		}

		response += "</tbody></table>";
		return response;
	}

	private class PostHandler implements HttpHandler {
		public void handle(HttpExchange transmission) throws IOException {

			// set up a stream to read the body of the request
			InputStream inputStr = transmission.getRequestBody();

			// set up a stream to write out the body of the response
			OutputStream outputStream = transmission.getResponseBody();

			// string to hold the result of reading in the request
			StringBuilder sb = new StringBuilder();

			// read the characters from the request byte by byte and build up the sharedResponse
			int nextChar = inputStr.read();
			while (nextChar > -1) {
				sb=sb.append((char)nextChar);
				nextChar=inputStr.read();
			}

			// create our response String to use in other handler
			String sharedResponse = sb.toString();

			// respond to the POST with ROGER
			String postResponse = "ROGER JSON RECEIVED";

			//sharedResponse = sharedResponse.substring(5);

			System.out.println("response: " + sharedResponse);

			GRun newRun = gson.fromJson(sharedResponse, GRun.class);
			
			System.out.println("Got json");
			
			if(latestRun == null || newRun.id >= latestRun.id) {
				latestRun = newRun;
				System.out.println("Latest run is now " + newRun.id);
			}
			runs.put(newRun.id, newRun);
			System.out.println(newRun);
			
			System.out.println("Finished looping new runs");
			
			// assume that stuff works all the time
			transmission.sendResponseHeaders(300, postResponse.length());

			// write it and return it
			outputStream.write(postResponse.getBytes());

			outputStream.close();
		}
	}

	private static class CSSHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange t) throws IOException {
			File css = new File("mystyle.css");
			Scanner in = new Scanner(css);
			String res = "";
			while(in.hasNextLine()) {
				res += in.nextLine();
			}
			System.out.println(res);
			t.sendResponseHeaders(200, res.length());
			t.getResponseBody().write(res.getBytes());
			t.getResponseBody().close();
		}
	}

}
