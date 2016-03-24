package team;

public class TRecord implements Record {
	
	public final TRun run;
	public final TRacer racer;
	protected long start,finish;
	protected boolean ended;

	@Override
	public Run getRun() {
		return run;
	}

	@Override
	public Racer getRacer() {
		return racer;
	}

	@Override
	public long getStartTime() {
		return start;
	}

	@Override
	public long getFinishTime() {
		return finish;
	}

	@Override
	public boolean isFinished() {
		return ended;
	}
	
	public TRecord(TRun r, TRacer rc) {
		run = r;
		racer = rc;
		start = -1;
		finish = -1;
	}
	
}
