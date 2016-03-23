package team;

public interface Record {
	
	public Run getRun();
	public Racer getRacer();
	
	public long getStartTime();
	public long getFinishTime();
	public boolean isFinished();
	
}
