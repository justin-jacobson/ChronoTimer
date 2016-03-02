
public interface Racer {
	
	public int getID();
	public boolean didNotFinish();
	
	public long getStartTime();
	public long getFinishTime();
	
	public default long getElapsedTime() {
		if(didNotFinish()) return -1;
		return getFinishTime() - getStartTime();
	}
	
	
}
