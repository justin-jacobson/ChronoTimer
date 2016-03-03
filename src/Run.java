import java.util.List;


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
	 * @return The last racer in this run.
	 */
	public Racer getLast();
	
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
	 * Removes a racer from the race. This can only before the race has started.
	 * @param target - The racer that should be removed from the race.
	 * @return True if the operation was successful.
	 */
	public boolean removeRacer(int target);
	
}
