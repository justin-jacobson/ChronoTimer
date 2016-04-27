package team;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebExporter implements Exporter {

	protected final String address;
	protected final int port;
	protected final JSONExporter json;
	
	public WebExporter(String a, int p, JSONExporter e){
		address = a;
		port = p;
		json = e;
	}
	
	@Override
	public void export(Exportable e) {
		try {
			// build a string that contains JSON from console
			String content = json.exportToString(e);
			
			if(content == null){
				return;
			}
			
			//Client will connect to this location
			URL site = new URL("http://"+address+":"+port+"/sendresults");
			HttpURLConnection conn = (HttpURLConnection) site.openConnection();

			// now create a POST request
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			

			// write out string to output buffer for message
			out.writeBytes(content);
			out.flush();
			out.close();

			InputStreamReader inputStr = new InputStreamReader(conn.getInputStream());

			// string to hold the result of reading in the response
			StringBuilder sb = new StringBuilder();

			// read the characters from the request byte by byte and build up the sharedResponse
			int nextChar = inputStr.read();
			while (nextChar > -1) {
				sb=sb.append((char)nextChar);
				nextChar=inputStr.read();
			}
			System.out.println("Return String: "+ sb);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public void reset(){
		try {
			//Client will connect to this location
			URL site = new URL("http://"+address+":"+port+"/clear");
			HttpURLConnection conn = (HttpURLConnection) site.openConnection();

			// now create a POST request
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			

			// write out string to output buffer for message
			out.writeBytes("");
			out.flush();
			out.close();

			//InputStreamReader inputStr = new InputStreamReader(conn.getInputStream());

//			// string to hold the result of reading in the response
//			StringBuilder sb = new StringBuilder();
//
//			// read the characters from the request byte by byte and build up the sharedResponse
//			int nextChar = inputStr.read();
//			while (nextChar > -1) {
//				sb=sb.append((char)nextChar);
//				nextChar=inputStr.read();
//			}
//			System.out.println("Return String: "+ sb);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
