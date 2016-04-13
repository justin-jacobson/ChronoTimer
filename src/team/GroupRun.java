package team;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GroupRun extends TRun {
	
	protected List<TRacer> racers = new ArrayList<TRacer>();
	protected int ind, ph = 1;
	
	protected long startTime = -1;
	
	public GroupRun(TRun run) {
		super(run);
		for(TRecord r : recordList) {
			if(!(r instanceof TRacerRecord)) continue;
			TRacerRecord rec = (TRacerRecord) r;
			racers.add(rec.racer);
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
		return startTime != -1;
	}

	@Override
	public boolean endRun() {
		for(int i = ind; i < racers.size(); i++){
			records.get(racers.get(i).id).ended = true;
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
		racers.add(0,r);
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
		long time = timer.getTimeManager().getTime();
		int id = c.getID();
		if(id == 1){
			if(hasStarted()) return false;
			startTime = time;
			for(TRecord r : records.values()){
				r.start = startTime;
			}
			return true;
		}
		else if(id == 2 && hasStarted()){
			TRecord record;
			if(ind >= recordList.size()) {
				record = new TPlaceHolderRecord(this,ph++);
				record.start = startTime;
				recordList.addLast(record);
				++ind;
			}
			else
				record = recordList.get(ind++);
			record.finish = time;
			record.ended = true;
			return true;
		}
		return false;
	}

}
