package team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParallelGroupRun extends TRun {

	protected long start = -1;
	protected int emptyLane = 0;
	protected List<TRacer> racers = new ArrayList<TRacer>(ChronoTimer.MAXIMUM_CHANNELS);
	public final List<TRacer> safeRacers;
	protected TRacer[] lanes = new TRacer[ChronoTimer.MAXIMUM_CHANNELS];
	
	public ParallelGroupRun(TRun run) {
		super(run);
		if(run.recordList.size() > ChronoTimer.MAXIMUM_CHANNELS){
			throw new UnsupportedOperationException("Not enough lanes for all racers.");
		}
		int i = 0;
		for(Racer r: run.getRacers()){
			TRacer tr = (TRacer)r;
			racers.add(tr);
			lanes[i++] = tr;
		}
		emptyLane = i;
		safeRacers = Collections.unmodifiableList(racers);
		
		// TODO Auto-generated constructor stub
	}
	
	public ParallelGroupRun(TChronoTimer timer, int id) {
		super(timer, id);
		safeRacers = Collections.unmodifiableList(racers);
		// TODO Auto-generated constructor stub
	}

	@Override
	public EventType getEventType() {
		return EventType.PARGRP;
	}

	@Override
	public boolean hasStarted() {
		return start > -1;
	}

	@Override
	public boolean endRun() {
		for(int i = 0; i < lanes.length; i++){
			if(lanes[i] != null) records.get(lanes[i].id).ended = true;
		}
		finished = true;
		return true;
	}

	@Override
	public boolean doNotFinish() {
		for(int i = 0; i < lanes.length; i++){
			if(lanes[i] != null){ 
				records.get(lanes[i].id).ended = true;
				return true;
			}
		}
		return false;
	}

//	@Override
//	protected TRacer safeRemoveRacer(int target) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected boolean safeAddRacer(TRacer r) {
//		if(lanes[ChronoTimer.MAXIMUM_CHANNELS-1] == null){
//			
//		}
//		return false;
//	}

	@Override
	public List<? extends Racer> getRacers() {
		return safeRacers;
	}

	@Override
	public Racer getLast() {
		for(int i = lanes.length-1; i >= 0; i--){
			if(lanes[i] != null) return lanes[i];
		}
		return null;
	}

	@Override
	protected boolean safeTrigger(Channel c) {
		long time = timer.getTimeManager().getTime();
		int id = c.getID();
		if(!hasStarted()){
			if(id == 1){
				start = time;
				return true;
			}
			return false;
		}
		if(lanes[id-1] != null){
			TRecord r = getRecord(lanes[id-1].getID());
			r.ended = true;
			r.finish = time;
			lanes[id-1] = null;
			return true;
		}
		
		return false;
	}

}
