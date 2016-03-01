
public interface Racer {
	
	public int getID();
	public boolean didNotFinish();
	
	public long getStartTime();
	public long getEndTime();
	
	public default long getElapsedTime() {
		if(didNotFinish()) return -1;
		return getEndTime() - getStartTime();
	}
	
	
}
