package team;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IndependentRun extends TRun {
	
	protected LinkedList<TRacer> racers, toStart, toEnd;
	protected List<Racer> safe_racers;
	
	protected int front;
	protected int back;
	
	/**
	 * check this run is started or not
	 * @return
	 */
	public boolean hasStarted() {
		if(finished) return true;
		if(racers.isEmpty()) return false;
		return racers.getFirst().start != -1;
	}
	
	@Override
	public boolean trigger(Channel c) {
		long time = TimeManager.getTime();
		if(c.getID() == 1){
			if(toStart.isEmpty()) return false;
			TRacer racer = toStart.pop();
			racer.start = time;
			toEnd.addLast(racer);
		}else if(c.getID() == 2){
			if(toEnd.isEmpty()) return false;
			TRacer racer = toEnd.pop();
			racer.finish = time;
			racer.ended = true;
		}
		return true;
	}

	@Override
	public Racer addRacer(int r) {
		if(!timer.isOn()&&finished) return null;
		TRacer newRacer=new TRacer(id);
		racers.addFirst(newRacer);
		toStart.addFirst(newRacer);
		return newRacer;
	}
	
	/**
	 * Ends the current run.
	 * @return True if the operation was successful. false if Chrono Timer is off or latest run already ended.
	 */
	public boolean endRun() {
		if(!timer.isOn()) return false;
		finished=true;
		for(TRacer r : toEnd)
			r.ended = true;
		return true;
	}
	
	/**
	 * Flags a runner as "Do Not Finish."
	 * @return True if the runner was successfully flagged
	 * @return False if there are no runners to flag or the run is finished
	 */
	@Override
	public boolean doNotFinish() {
		if(!timer.isOn() || finished || getRacers().isEmpty()) return false;
		TRacer r = toEnd.getFirst();
		if(r == null) {
			r = toStart.getFirst();
			if(r == null) return false;
		}
		r.ended = true;
		return true;
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
	
	@Override
	public List<Racer> getRacers() {
		return safe_racers;
	}
	
	@Override
	public Racer getLast() {
		return racers.getLast();
	}
	
	
	/**
	 * This method removes the target id racer from racers, start, and finish list.
	 * @return working correctly or not
	 * @param target racer's id.
	 */
	public boolean removeRacer(int target){
		if(!timer.isOn()||finished || (racers.size()<=target)) return false;
		TRacer temp= racerSearch(target);
		if(temp!=null){
			racers.remove(temp);
			if(toStart.indexOf(temp)!=-1) toStart.remove(temp);
			if(toEnd.indexOf(temp)!=-1) toEnd.remove(temp);
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * private method to find out the target id racer
	 * @param target
	 * @return the target id racer
	 */
	private TRacer racerSearch(int target){
		Iterator<TRacer> t=racers.iterator();
		while(t.hasNext()){
			TRacer cur=(TRacer)t.next();
			if(cur.getID()==target)
				return cur;
		}
		return null;
	}
	
	public IndependentRun(ChronoTimer timer, int id) {
		super(timer, id, EventType.IND);
		racers=new LinkedList<TRacer>();
		toStart = new LinkedList<TRacer>();
		toEnd = new LinkedList<TRacer>();
		safe_racers = Collections.unmodifiableList(racers);
	}
	
}
