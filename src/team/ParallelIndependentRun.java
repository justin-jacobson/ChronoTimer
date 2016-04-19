package team;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ParallelIndependentRun extends TRun {
	
	protected final LinkedList<TRacer> allRacers;
	protected final LinkedList<TRacer> toStart1, toStart2, ended;
	protected boolean addTo1 = true;
	public final List<Racer> racers;
	
	protected final LinkedList<TRacer> track1, track2;
	
	private boolean started;
	private LinkedList<TRacer> lastStarted = new LinkedList<TRacer>();
	
	@Override
	public boolean safeAddRacer(TRacer r) {
		allRacers.addFirst(r);
		if(addTo1) toStart1.addFirst(r);
		else toStart2.addFirst(r);
		addTo1 = !addTo1;
		return true;
	}

	@Override
	public boolean endRun() {
		if(!timer.isOn() || finished) return false;
		finished = true;
		for(TRecord r : records.values()) {
			r.ended = true;
		}
		toStart1.clear();
		toStart2.clear();
		ended.clear();
		return true;
	}

	@Override
	public boolean doNotFinish() {
		if(!timer.isOn() || finished || getRacers().isEmpty()) return false;
		TRacer r;
		if(lastStarted.isEmpty()) {
			if(ended.size() == allRacers.size()) return false;
			r = allRacers.get(ended.size());
			toStart1.remove(r);
			toStart2.remove(r);
		} else {
			r = lastStarted.pop();
			track1.remove(r);
			track2.remove(r);
		}
		records.get(r.id).ended = true;
		ended.addFirst(r);
		return true;
	}
	
	@Override
	public boolean hasStarted() {
		if(finished) return true;
		return started;
	}
	
	protected TRacer safeRemoveRacer(int target) {
		Iterator<TRacer> it = allRacers.iterator();
		while(it.hasNext()) {
			TRacer ir = it.next();
			if(ir.id == target) {
				it.remove();
				toStart1.remove(ir);
				toStart2.remove(ir);
				return ir;
			}
		}
		return null;
	}

	@Override
	public List<Racer> getRacers() {
		return racers;
	}

	@Override
	public Racer getLast() {
		return allRacers.get(allRacers.size()-1);
	}

	@Override
	public boolean safeTrigger(Channel c) {
		long time = timer.getTimeManager().getTime();
		started = true;
		TRacer r = null;
		LinkedList<TRacer> track = getTrackFromChannel(c.getID());
		switch (c.getID()) {
			case 1:
				r = toStart1.poll();
				if(r == null) break;
			case 3:
				if(r == null)
					r = toStart2.poll();
				if(r == null) break;
				lastStarted.addLast(r);
				records.get(r.id).start = time;
				track.add(r);
				break;
			case 2:
			case 4:
				r = track.poll();
				if(r == null) break;
				TRecord rec = records.get(r.id);
				rec.finish = time;
				rec.ended = true;
				lastStarted.remove(r);
				ended.add(r);
				break;
		}
		return true;
	}
	
	private LinkedList<TRacer> getTrackFromChannel(int c) {
		if(c == 1 || c == 2) return track1;
		else if(c == 3 || c == 4) return track2;
		return null;
	}
	
	@Override
	public EventType getEventType() {
		return EventType.PARIND;
	}
	
	public ParallelIndependentRun(TChronoTimer timer, int id) {
		super(timer, id);
		allRacers = new LinkedList<TRacer>();
		toStart1 = new LinkedList<TRacer>();
		toStart2 = new LinkedList<TRacer>();
		ended = new LinkedList<TRacer>();
		track1 = new LinkedList<TRacer>();
		track2 = new LinkedList<TRacer>();
		racers = Collections.unmodifiableList(allRacers);
	}
	
	public ParallelIndependentRun(TRun old) {
		super(old);
		allRacers = new LinkedList<TRacer>();
		toStart1 = new LinkedList<TRacer>();
		toStart2 = new LinkedList<TRacer>();
		ended = new LinkedList<TRacer>();
		track1 = new LinkedList<TRacer>();
		track2 = new LinkedList<TRacer>();
		racers = Collections.unmodifiableList(allRacers);
		for(TRecord r : old.getRecords()) {
			if(r instanceof TRacerRecord){
				TRacerRecord rec = (TRacerRecord) r;
				allRacers.add(rec.racer);
			}
		}
	}
	
}
