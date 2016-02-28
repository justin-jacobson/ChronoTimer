import java.util.Iterator;
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
	/**
	 * This create a list of racer's records and return String
	 * @return String of racer's record list
	 * 
	 */
	public String toString(){
		String result="";
		Iterator<Racer> t=racers.iterator();
		while(t.hasNext()){
			result+=(racers.toString()+"\n");
		}
		return result;
	}
	
	public void setEvent(EventType event){
		this.e=event;
	}
	
	public void addRacer(){
		racers.add(new Racer(++front));
	}
	
}
