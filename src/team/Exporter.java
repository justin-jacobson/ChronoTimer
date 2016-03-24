package team;

public class Exporter {

	private class GRun{
		private int id;
		private GRacer[] racers;

		public GRun(TRun run){
			id = run.getID();
			for(Racer r : run.getRacers()){

			}
		}
	}

	private class GRacer{
		private int id;
		private long start;
		private long finish;
		private boolean ended;

		public GRacer(TRacer racer){
			id = racer.getID();
			start = racer.getStartTime();
			finish = racer.getFinishTime();
            ended = racer.
		}
	}

	public void export(int run){

	}
}
