import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TChronoTimer implements ChronoTimer {
	
	/**
	 * The current power state of the chrono timer.
	 */
	private boolean power;
	/**
	 * Array holding all the channels that belong to this chrono timer.
	 */
	private final TChannel[] channels;
	
	private final List<Channel> safe_channels;
	
	/**
	 * List containing all the runs.
	 */
	private final List<TRun> runs;
	
	private final List<Run> safe_runs;
	
	/**
	 * The index of the current/latest run.
	 */
	private int current_run;
	
	public boolean isOn() {
		return power;
	}
	
	public boolean togglePower(){
		power = !power;
		return true;
	}
	
	public boolean setPower(boolean pow) {
		boolean result = power != pow;
		power = pow;
		return result;
	}
	
	/**
	 * Resets the chrono timer to default settings and clears all runs.
	 */
	public boolean reset() {
		for(int i=0; i<ChronoTimer.MAXIMUM_CHANNELS; i++) {
			channels[i].setSensor(SensorType.NONE);
			channels[i].disable();
		}
		runs.clear();
		runs.add(new TRun(this, 1));
		current_run = 0;
		// Reset time?
		return true;
	}
	
	/**
	 * Toggles the given channel.
	 * @param ch - The channel.
	 * @return True if the operation was successful.
	 */
	public boolean toggleChanel(int ch){
		if(!power || ch < 1 || ch > channels.length-1) return false;
		return channels[ch-1].toggle();
	}
	
	/**
	 * Creates a new run.
	 * @return True if operation was successful, otherwise false if the chrono timer is off or the current race has not ended.
	 */
	public Run newRun(){
		if(!power || !runs.isEmpty() && !runs.get(current_run).finished) return null;
		TRun result = new TRun(this, ++current_run + 1);
		runs.add(result);
		return result;
	}
	
	/**
	 * Ends the current run.
	 * @return True if the operation was successful. false if chrono timer is off or latest run already ended.
	 */
	public boolean endRun(){
		if(!power || runs.get(current_run).finished) return false;
		runs.get(current_run).finished=true;
		for(TRacer r : runs.get(current_run).toEnd)
			r.ended = true;
		return true;
	}
	
	//NO NEED TO IMPLEMENT NOW
	public boolean swap(){
		return false;
	}
	
	@Override
	public List<Channel> getChannels() {
		return safe_channels;
	}
	
	
	@Override
	public List<Run> getRuns() {
		return safe_runs;
	}
	
	@Override
	public boolean trigger(Channel c) {
		if(!power || c == null || !c.isEnabled()||this.getLatestRun().isFinished()) return false;
		long time = TimeManager.getTime();
		TRun cur = (TRun)getLatestRun();
		if(c.getID() == 1){
			if(cur.toStart.isEmpty()) return false;
			TRacer racer = cur.toStart.pop();
			racer.start = time;
			cur.toEnd.addLast(racer);
		}else if(c.getID() == 2){
			if(cur.toEnd.isEmpty()) return false;
			TRacer racer = cur.toEnd.pop();
			racer.finish = time;
			racer.ended = true;
		}
		return true;
	}
	
	@Override
	public boolean doNotFinish() {
		if(!power || runs.get(current_run).finished) return false;
		TRun run = runs.get(current_run);
		if(run.finished || run.getRacers().isEmpty()) return false;
		TRacer r = run.toEnd.getFirst();
		if(r == null) {
			r = run.toStart.getFirst();
			if(r == null) return false;
		}
		r.ended = true;
		return true;
	}
	
	@Override
	public boolean setEvent(EventType event) {
		if(!power || event == null) return false;
		runs.get(current_run).setEventType(event);
		return true;
	}
	
	@Override
	public Run getLatestRun() {
		return runs.get(current_run);
	}
	
	public TChronoTimer() {
		power = false;
		
		channels = new TChannel[ChronoTimer.MAXIMUM_CHANNELS];
		safe_channels = Collections.unmodifiableList(Arrays.asList(channels));
		for(int i=0; i<ChronoTimer.MAXIMUM_CHANNELS; i++)
			channels[i] = new TChannel(this,i+1);
		
		runs = new ArrayList<TRun>();
		safe_runs = Collections.unmodifiableList(runs);
		runs.add(new TRun(this, 1)); // Always starts at run 1 by default.
	}
	
}