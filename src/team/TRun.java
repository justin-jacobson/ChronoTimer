package team;

import java.util.List;

public abstract class TRun implements Run {
	
	protected final ChronoTimer timer;
	protected final int id;
	protected EventType e;
	
	protected boolean finished;
	
	public void setEventType(EventType event){
		this.e=event;
	}
	
	public abstract boolean hasStarted();
	
	public abstract boolean removeRacer(int r);
	

	@Override
	public EventType getEventType() {
		return e;
	}

	public abstract List<Racer> getRacers();

	@Override
	public boolean isFinished() {
		return finished;
	}

	public abstract Racer getLast();

	@Override
	public int getID() {
		return id;
	}
	
	public abstract boolean trigger(Channel c);
	
	public TRun(ChronoTimer timer,int id, EventType t){
		this.timer=timer;
		this.id = id;
		e=t;
		finished=false;
	}
	
	public static final TRun getDefaultRun(ChronoTimer timer, int id) {
		return new IndependentRun(timer, id);
	}
	
}
