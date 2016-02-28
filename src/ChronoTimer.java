import java.util.ArrayList;
import java.util.List;

public class ChronoTimer {
	
	/**
	 * The maximum amount of channels for any ChronoTimer.
	 */
	public static final int CH_MAX = 12;
	
	/**
	 * The current power state of the chrono timer.
	 */
	private boolean power;
	/**
	 * Array holding all the channels that belong to this chrono timer.
	 */
	private Channel[] channels;
	/**
	 * List containing all the runs.
	 */
	private List<Run> runs;
	/**
	 * The index of the current/latest run.
	 */
	private int current_run;
	
	public ChronoTimer(){ 
		power = false;
		
		channels = new Channel[CH_MAX];
		for(int i=0; i<CH_MAX; i++)
			channels[i] = new Channel(this,i);
		
		runs = new ArrayList<Run>();
		runs.add(new Run()); // Always starts at run 1 by default.
	}
	
	/**
	 * Toggles the power of the chrono timer.
	 * @return True if operation was sucessful.
	 */
	public boolean togglePower(){
		power = !power;
		return true;
	}
	
	/**
	 * Sets the power state of the chrono timer.
	 * @param pow - The power state the chrono timer should have.
	 * @return True if the state was changed, false if it was already set to given state.
	 */
	public boolean setPower(boolean pow) {
		boolean result = power != pow;
		power = pow;
		return result;
	}
	
	/**
	 * Resets the chrono timer to default settings and clears all runs.
	 */
	public void reset(){
		for(int i=0; i<CH_MAX; i++) {
			channels[i].setSensor(SensorType.NONE);
			channels[i].disable();
		}
		runs.clear();
		runs.add(new Run());
		current_run = 0;
		// Reset time?
	}
	
	/**
	 * Toggles the given channel.
	 * @param ch - The channel.
	 * @return True if the operation was successful.
	 */
	public boolean toggleChanel(int ch){
		if(!power) return false;
		return channels[ch].toggle();
	}
	
	/**
	 * Connects a sensor to the specified channel. This method overwrites the old channels sensor and disables it.
	 * @param t - The SensorType the channel should be connected to.
	 * @param ch - The channel to connect the sensor to.
	 * @return True if operation was successful.
	 */
	public boolean connect(SensorType t, int ch){
		if(!power || ch < 0 || ch > CH_MAX-1) return false;
		channels[ch].setSensor(t);
		channels[ch].disable();
		return true;
	}
	
	/**
	 * Disconnects the specified channel and disables it.
	 * @param ch - The specified channel.
	 * @return True if operation was successful.
	 */
	public boolean disconnect(int ch){
		if(!power || ch < 0 || ch > CH_MAX-1) return false;
		channels[ch].setSensor(SensorType.NONE);
		channels[ch].disable();
		return true;
	}
	
	/**
	 * Triggers the given channel.
	 * @param ch - The channel that should be triggered.
	 * @return True if the operation was successful.
	 */
	public boolean trigger(int ch){
		if(!power || runs.get(current_run).finished || ch < 0 || ch > CH_MAX-1 || !channels[ch].isEnabled()) return false;
		
		//Implement trigger here.
		
		return true;
	}
	
	/**
	 * Sets the next racer to finish to not finish and leave the race.
	 */
	public void doNotFin(){
		runs.get(current_run).racers.getLast().ended=true;
	}
	
	/**
	 * Creates a new run.
	 * @return True if operation was successful, otherwise false if the chrono timer is off or the current race has not ended.
	 */
	public boolean newRun(){
		if(!power) return false;
		//if "runs" is not empty and last runs was not finished >> false
		if(!runs.isEmpty() && !runs.get(current_run).finished) return false;
		runs.add(new Run());
		current_run++;
		return true;
	}
	
	/**
	 * Ends the current run.
	 * @return True if the operation was successful. false if chrono timer is off or latest run already ended.
	 */
	public boolean endRun(){
		if(!power || runs.get(current_run).finished) return false;
		//Gun Q: is there something to check before make it finish?
		runs.get(current_run).finished=true;
		return true;
	}
	
	//NO NEED TO IMPLEMENT NOW
	public boolean swap(){
		return false;
	}
	
	/**
	 * Print out specific run into standard out format.
	 * @param run
	 * @return racer's records in run. start, end, and a lap time each line.
	 */
	public String print(int run){
		return runs.get(run).toString();
	}
}
