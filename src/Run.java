import java.util.List;


public interface Run {
	
	public EventType getEventType();
	public List<Racer> getRacers();
	public Racer getLast();
	public boolean isFinished();
	
}
