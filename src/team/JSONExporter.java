package team;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSONExporter implements Exporter {

	static final Gson gson = new Gson();

	private static class GRecord {
		private int run, racer;
		private long start,finish;
		private boolean ended;
		GRecord(Record r) {
			run = r.getRun().getID();
			racer = r.getRacer().getID();
			start = r.getStartTime();
			finish = r.getFinishTime();
			ended = r.isFinished();
		}
	}

	private static class GRun{
		private final int id;
		private final long epoch;
		private GRecord[] records;

		public GRun(TRun run){
			id = run.id;
			epoch = TimeManager.getEpoch();
			records = new GRecord[run.getRecords().values().size()];
			int i = 0;
			for(Record r : run.getRecords().values()) {
				records[i++] = new GRecord(r);
			}
		}
	}

	private static class GRacer{
		private final int id;
		private GRecord[] records;

		public GRacer(Racer racer){
			id = racer.getID();
			records = new GRecord[racer.getRecords().values().size()];
			int i = 0;
			for(Record r : racer.getRecords().values()) {
				records[i++] = new GRecord(r);
			}
		}
	}

	public void export(Exportable e) {
		if(e instanceof TRun) {
			TRun run = (TRun) e;
			GRun grun = new GRun(run);
			String fileName = "Run" + run.getID() + ".txt";
			String json = gson.toJson(grun);

			try(FileWriter file = new FileWriter(fileName)) {
				file.write(json);
				System.out.println("Copied JSON object into local file");
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}
}