package team;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GroupRun extends TRun {
	
	List<TRacer> racers = new ArrayList<TRacer>();
	int ind;
	
	public GroupRun(TRun run){
		super(run);
		for(TRecord r : recordList) {
			racers.add(r.racer);
		}
	}

	public GroupRun(TChronoTimer timer, int id) {
		super(timer, id);
	}

	@Override
	public EventType getEventType() {
		return EventType.GRP;
	}

	@Override
	public boolean hasStarted() {
		if(racers.isEmpty() || records.get(racers.get(0).id).start == -1) return false;
		return true;
	}

	@Override
	public boolean endRun() {
		for(int i = ind; i < racers.size(); i++){
			getRecord(i).ended = true;
		}
		finished = true;
		return true;
	}

	@Override
	public boolean doNotFinish() {
		if(finished || racers.isEmpty()) return false;
		getRecord(ind++).ended = true;
		return true;
	}

	@Override
	protected TRacer safeRemoveRacer(int target) {
		Iterator<TRacer> it = racers.iterator();
		while(it.hasNext()) {
			TRacer r = it.next();
			if(r.id == target) {
				it.remove();
				return r;
			}
		}
		return null;
	}

	@Override
	protected boolean safeAddRacer(TRacer r) {
		racers.add(r);
		return true;
	}

	@Override
	public List<TRacer> getRacers() {
		return racers;
	}

	@Override
	public TRacer getLast() {
		if (racers.isEmpty()) return null;
		return racers.get(racers.size()-1);
	}

	@Override
	protected boolean safeTrigger(Channel c) {
		long time = TimeManager.getTime();
		int id = c.getID();
		if(id == 1){
			if(hasStarted()) return false;
			for(TRecord r : records.values()){
				r.start = time;
			}
			return true;
		}
		else if(id == 2 && hasStarted()){
			TRacer racer = racers.get(ind++);
			records.get(racer.id).finish = time;
			if(ind == racers.size()) finished = true;
			return true;
		}
		return false;
	}

}
