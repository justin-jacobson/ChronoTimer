import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TRun implements Run {
	
	private final ChronoTimer timer;
	protected final int id;
	protected EventType e;
	protected LinkedList<TRacer> racers, toStart, toEnd;
	protected List<Racer> safe_racers;
	protected boolean finished;
	
	protected int front;
	protected int back;
	
	public TRun(ChronoTimer timer, int id){
		this.timer=timer;
		this.id = id;
		e=EventType.IND;
		racers=new LinkedList<TRacer>();
		toStart = new LinkedList<TRacer>();
		toEnd = new LinkedList<TRacer>();
		safe_racers = Collections.unmodifiableList(racers);
		finished=false;
	}
	
	public TRun(ChronoTimer timer,int id, EventType t){
		this.timer=timer;
		this.id = id;
		e=t;
		racers=new LinkedList<TRacer>();
		toStart = new LinkedList<TRacer>();
		toEnd = new LinkedList<TRacer>();
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
	/**
	 * check this run is started or not
	 * @return
	 */
	public boolean hasStarted() {
		if(finished) return true;
		if(racers.isEmpty()) return false;
		return racers.getFirst().start != -1;
	}
	/**
	 * add Racer to racers list and start list.
	 * @return new racer which is added to list.
	 * @param take racers id number
	 */
	public Racer addRacer(int id){
		if(!timer.isOn()&&finished) return null;
		TRacer newRacer=new TRacer(id);
		racers.addFirst(newRacer);
		toStart.addFirst(newRacer);
		return newRacer;
	}
	/**
	 * This method removes the target id racer from racers, start, and finish list.
	 * @return working correctly or not
	 * @param target racer's id.
	 */
	public boolean removeRacer(int target){
		if(!timer.isOn()||finished || (racers.size()<=target)) return false;
		TRacer temp=racerSearch(target);
		if(temp!=null){
			racers.remove(temp);
			if(toStart.indexOf(temp)!=-1) toStart.remove(temp);
			if(toEnd.indexOf(temp)!=-1) toEnd.remove(temp);
			return true;
		}else{
			return false;
		}
	}
	
	private TRacer racerSearch(int target){
		Iterator t=racers.iterator();
		while(t.hasNext()){
			TRacer cur=(TRacer)t.next();
			if(cur.getID()==target)
				return cur;
		}
		return null;
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
