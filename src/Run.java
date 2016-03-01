import java.util.List;


public interface Run {
	
	public int getID();
	public EventType getEventType();
	public List<Racer> getRacers();
	public Racer getLast();
	public boolean isFinished();
	
}
