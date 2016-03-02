import java.util.List;


public interface Run {
	
	public int getID();
	public EventType getEventType();
	public List<Racer> getRacers();
	public Racer getLast();
	public boolean isFinished();
	public Racer addRacer(int id);
	public boolean removeRacer(int target);
	
}
