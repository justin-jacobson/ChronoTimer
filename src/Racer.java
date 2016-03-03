
public interface Racer {
	
	/**
	 * @return The racers ID.
	 */
	public int getID();
	
	/**
	 * @return True if the racer did not finish(DNF).
	 */
	public boolean didNotFinish();
	
	/**
	 * @return The racers start time, will return -1 if they do not have a start time recorded.
	 */
	public long getStartTime();
	
	/**
	 * @return The racers finish time, will return -1 if they do not have a finish time recorded.
	 */
	public long getFinishTime();
	
	/**
	 * @return The elapsed time it took the racer to complete the run.
	 * Will return -1 if they did not finish or do not have both a start and finish time recorded.
	 */
	public default long getElapsedTime() {
		if(didNotFinish() || getStartTime() == -1 || getFinishTime() == -1) return -1;
		return getFinishTime() - getStartTime();
	}
	
	
}
