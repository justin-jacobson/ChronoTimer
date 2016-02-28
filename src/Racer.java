
public class Racer {
	private int id;
	protected long start;
	protected long finish;
	protected boolean ended;
	
	public Racer(int id){
		this.id=id;
		start=-1;
		finish=-1;
		ended=false;
	}
	
	public boolean setTime(int n){
		if(!ended) return false;
		if(n==1 && start == -1){
			this.start=TimeManager.getTime();
			return true;
		} else if(n==0 && finish==-1){
			this.finish=TimeManager.getTime();
			ended=true;
			return true;
		}else{
			return false;
		}
	}
	
	public String returnLapTime(){
		if(ended && finish!=-1){
			return TimeManager.formatTime(TimeManager.subtractTime(start, finish));
		}
		return TimeManager.formatTime(TimeManager.subtractTime(start, TimeManager.getTime()));
	}
	
	public String toString(){
		if(ended)
			return id+" "+TimeManager.formatTime(start)+" "+TimeManager.formatTime(finish)+" "+" "+this.returnLapTime();
		else
			return id+" "+TimeManager.formatTime(start)+" "+TimeManager.formatTime(TimeManager.getTime())+" "+" "+this.returnLapTime();
	}
}
