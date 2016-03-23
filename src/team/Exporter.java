package team;

import com.google.gson.Gson;

public class Exporter {
	
	static final Gson gson = new Gson();

	private class GRun{
		private int id;
		private GRacer[] racers;
		
		public GRun(TRun run){
			id = run.getID();
			int i = 0;
			for(Racer r : run.getRacers()){
				racers[i] = new GRacer(r);
			}
		}
	}
	
	private class GRacer{
		private int id;
		private long start;
		private long finish;
		private boolean ended;
		
		public GRacer(Racer racer){
			id = racer.getID();
			start = racer.getStartTime();
			finish = racer.getFinishTime();
			ended = !racer.didNotFinish();
		}
	}
	
	public void export(TRun run){
		GRun grun = new GRun(run);
		String json = gson.toJson(grun);		//incomplete
		
	}
}
