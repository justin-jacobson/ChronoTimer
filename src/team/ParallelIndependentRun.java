package team;

import java.util.List;

public class ParallelIndependentRun extends TRun {

	@Override
	public Racer addRacer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean endRun() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doNotFinish() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeRacer(int r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Racer> getRacers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Racer getLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean trigger(Channel c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public ParallelIndependentRun(ChronoTimer timer, int id) {
		super(timer, id, EventType.PARIND);
	}
	
}
