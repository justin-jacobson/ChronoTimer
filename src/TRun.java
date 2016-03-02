import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TRun implements Run {
	
	private final ChronoTimer timer;
	protected final int id;
	protected EventType e;
	protected LinkedList<TRacer> racers;
	protected List<Racer> safe_racers;
	protected boolean finished;
	private boolean started;
	
	private TRacer front;
	private TRacer back;
	
	public TRun(ChronoTimer timer, int id){
		this.timer=timer;
		this.id = id;
		e=EventType.IND;
		racers=new LinkedList<TRacer>();
		safe_racers = Collections.unmodifiableList(racers);
		finished=false;
		started=false;
	}
	
	public TRun(ChronoTimer timer,int id, EventType t){
		this.timer=timer;
		this.id = id;
		e=t;
		racers=new LinkedList<TRacer>();
		safe_racers = Collections.unmodifiableList(racers);
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
	
	public Racer addRacer(int id){
		if(!timer.isOn()&&finished) return null;
		TRacer newRacer=new TRacer(id);
		racers.addFirst(newRacer);
		return newRacer;
	}
	
	public boolean removeRacer(int target){
		if(!timer.isOn()&&finished) return false;
		return racers.remove(target)!=null;
		
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

	@Override
	public int getID() {
		return id;
	}
	
}
