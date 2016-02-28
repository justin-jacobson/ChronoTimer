
public class Racer {
	private int id;
	protected int start;
	protected int finish;
	protected boolean ended;
	
	public Racer(int id){
		this.id=id;
	}
	
	public void setStart(){
		this.start=TimeManager.getRelativeTime();
	}
	
	public void setFinish(){
		this.start=TimeManager.getRelativeTime();
	}
	
	public String returnLapTime(){
		return TimeManager.formatTime(finish-start);
	}
	
	public String toString(){
		return id+" "+TimeManager.formatTime(start)+" "+TimeManager.formatTime(finish)+" "+" "+this.returnLapTime();
	}
}
