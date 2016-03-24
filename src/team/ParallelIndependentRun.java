package team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ParallelIndependentRun extends TRun {
	
	protected final List<TRacer> allRacers;
	protected final LinkedList<TRacer> toStart, ended;
	public final List<Racer> racers;
	
	protected final LinkedList<TRacer> track1, track2;
	
	private boolean started;
	private LinkedList<TRacer> lastStarted;
	
	@Override
	public boolean safeAddRacer(TRacer r) {
		allRacers.add(r);
		toStart.add(r);
		return true;
	}

	@Override
	public boolean endRun() {
		if(!timer.isOn() || finished) return false;
		finished = true;
		for(TRecord r : records.values()) {
			r.ended = true;
		}
		toStart.clear();
		ended.clear();
		return true;
	}

	@Override
	public boolean doNotFinish() {
		if(!timer.isOn() || finished || getRacers().isEmpty()) return false;
		TRacer r;
		if(lastStarted.isEmpty()) {
			if(toStart.isEmpty()) return false;
			r = toStart.pop();
		} else {
			r = lastStarted.pop();
			track1.remove(r);
			track2.remove(r);
		}
		records.get(r.id).ended = true;
		ended.add(r);
		return false;
	}
	
	@Override
	public boolean hasStarted() {
		return started;
	}
	
	protected TRacer safeRemoveRacer(int target) {
		TRacer r = null;
		for(TRacer rc : allRacers) {
			if(rc.id == target) {
				r = rc;
				break;
			}
		}
		return r;
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
		long time = TimeManager.getTime();
		started = true;
		TRacer r = null;
		LinkedList<TRacer> track = getTrackFromChannel(c.getID());
		switch (c.getID()) {
			case 1:
			case 3:
				r = toStart.poll();
				if(r == null) break;
				lastStarted.addLast(r);
				records.get(r.id).start = time;
				track.add(r);
				break;
			case 2:
			case 4:
				r = track.poll();
				if(r == null) break;
				records.get(r.id).finish = time;
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
		allRacers = new ArrayList<TRacer>();
		toStart = new LinkedList<TRacer>();
		ended = new LinkedList<TRacer>();
		track1 = new LinkedList<TRacer>();
		track2 = new LinkedList<TRacer>();
		racers = Collections.unmodifiableList(allRacers);
	}
	
	public ParallelIndependentRun(TRun old) {
		super(old);
		allRacers = new ArrayList<TRacer>();
		toStart = new LinkedList<TRacer>();
		ended = new LinkedList<TRacer>();
		track1 = new LinkedList<TRacer>();
		track2 = new LinkedList<TRacer>();
		racers = Collections.unmodifiableList(allRacers);
		for(TRecord r : old.getRecords().values()) {
			addRacer(r.getRacer().getID());
		}
	}
	
}
