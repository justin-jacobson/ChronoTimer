import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TRun implements Run {
	
	protected final long start;
	protected EventType e;
	protected LinkedList<TRacer> racers;
	protected List<Racer> safe_racers;
	private int front;
	protected boolean finished;
	
	public TRun(){
		start = System.currentTimeMillis();
		e=EventType.IND;
		racers=new LinkedList<TRacer>();
		safe_racers = Collections.unmodifiableList(racers);
		front=-1;
		finished=false;
	}
	
	public TRun(EventType t){
		start = System.currentTimeMillis();
		e=t;
		racers=new LinkedList<TRacer>();
		safe_racers = Collections.unmodifiableList(racers);
		front=-1;
		finished=false;
	}
	/**
	 * This create a list of racer's records and return String
	 * @return String of racer's record list
	 * 
	 */
	public String toString(){
		String result="";
		Iterator<TRacer> t=racers.iterator();
		while(t.hasNext()){
			result+=(racers.toString()+"\n");
		}
		return result;
	}
	
	public void setEventType(EventType event){
		this.e=event;
	}
	
	public void addRacer(){
		racers.add(new TRacer(++front));
	}

	@Override
	public EventType getEventType() {
		return e;
	}

	@Override
	public List<Racer> getRacers() {
		return safe_racers;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	@Override
	public Racer getLast() {
		return racers.getLast();
	}
	
}
