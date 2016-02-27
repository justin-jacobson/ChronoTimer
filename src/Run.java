import java.util.LinkedList;

public class Run {
	
	protected EventType e;
	protected LinkedList<Racer> racers;
	private int front;
	protected boolean finished;
	
	public Run(){
		this.e=EventType.IND;
		this.racers=new LinkedList<Racer>();
		this.front=-1;
		this.finished=false;
	}
	
	public Run(EventType t){
		this.e=t;
		this.racers=new LinkedList<Racer>();
		this.front=-1;
		this.finished=false;
	}
	
}
