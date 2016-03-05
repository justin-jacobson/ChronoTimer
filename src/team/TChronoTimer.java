package team;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TChronoTimer implements ChronoTimer {
	
	/**
	 * The current power state of the ChronoTimer.
	 */
	private boolean power;
	/**
	 * Array holding all the channels that belong to this ChonoTimer.
	 */
	private final TChannel[] channels;
	/**
	 * List containing all safe channels
	 */
	private final List<Channel> safe_channels;
	/**
	 * List containing all the runs.
	 */
	private final List<TRun> runs;
	/**
	 * List containing all safe runs
	 */
	private final List<Run> safe_runs;
	/**
	 * The index of the current/latest run.
	 */
	private int current_run;
	
	/**
	 * Checks power status of the ChronoTimer
	 * @return The current status of power
	 */
	public boolean isOn() {
		return power;
	}
	
	/**
	 * Toggles the power of the ChonoTimer
	 * @return True if power was toggled
	 */
	public boolean togglePower(){
		power = !power;
		return true;
	}
	/**
	 * @param New power value
	 * @return True if power status has changed
	 */
	public boolean setPower(boolean pow) {
		boolean result = power != pow;
		power = pow;
		return result;
	}
	
	/**
	 * Resets the ChronoTimer to default settings and clears all runs.
	 */
	public boolean reset() {
		for(int i=0; i<ChronoTimer.MAXIMUM_CHANNELS; i++) {
			channels[i].setSensorType(SensorType.NONE);
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
	 * @return True if the operation was successful. false if Chrono Timer is off or latest run already ended.
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
	
	/**
	 * @return A list of channels used by the ChonoTimer
	 */
	@Override
	public List<Channel> getChannels() {
		return safe_channels;
	}
	
	/**
	 * @return All runs in use by the ChonoTimer
	 */
	@Override
	public List<Run> getRuns() {
		return safe_runs;
	}
	/**
	 * Triggers or starts the the latest run on the ChonoTimer.
	 * @param channel to trigger
	 * @return true if successfully triggered
	 * @return false if either Run's start or end queues are empty
	 */
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
	
	/**
	 * Flags a runner as "Do Not Finish."
	 * @return True if the runner was successfully flagged
	 * @return False if there are no runners to flag or the run is finished
	 */
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
	
	/**
	 * Sets the current event type for the latest run.
	 * @param New EventType
	 * @return True if the run's event was changed
	 * @return False for no event
	 */
	@Override
	public boolean setEvent(EventType event) {
		if(!power || event == null) return false;
		runs.get(current_run).setEventType(event);
		return true;
	}
	/**
	 * @return The current/latest run on this ChonoTimer
	 */
	@Override
	public Run getLatestRun() {
		return runs.get(current_run);
	}
	
	/**
	 * Create a new TChonoTimer with parameters set to default.
	 * This automatically instantiates a new run for this
	 * TChonoTimer instance, and by default the TChonoTimer's power is off.
	 * @return A new TChonoTimer instance
	 */
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
