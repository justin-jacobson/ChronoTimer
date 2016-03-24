package team;
import java.util.List;
import java.util.Map;


public interface Run {
	
	/**
	 * @return The runs id.
	 */
	public int getID();
	
	/**
	 * @return The runs event type.
	 */
	public EventType getEventType();
	
	/**
	 * @return A list of all the racers that are in this run.
	 */
	public List<Racer> getRacers();
	
	/**
	 * @return A map that maps a racers id to his/her record for this run.
	 */
	public Map<Integer,? extends Record> getRecords();
	
	public default Record getRecord(Racer r) {
		return getRecords().get(r.getID());
	}
	
	public default Record getRecord(int id) {
		return getRecords().get(id);
	}
	
	/**
	 * @return The last racer in this run.
	 */
	public Racer getLast();
	
	/**
	 * @return True if the run has started or finished. False if it has not started.
	 */
	public boolean hasStarted();
	
	/**
	 * @return True if this race has finished.
	 */
	public boolean isFinished();
	
	/**
	 * Adds a new racer to the front of the run.
	 * @param id - The id of the racer.
	 * @return True if the operation was successful.
	 */
	public Racer addRacer(int id);
	
	/**
	 * Ends this run if it is not finished yet.
	 * @return True if the run was ended successfully.
	 */
	public boolean endRun();
	
	/**
	 * Sets the next racer in the run to not finish.
	 * @return True if the operation was successful.
	 */
	public boolean doNotFinish();
	
	/**
	 * Removes a racer from the race. This can only before the race has started.
	 * @param target - The racer that should be removed from the race.
	 * @return True if the operation was successful.
	 */
	public boolean removeRacer(int target);
	
}
