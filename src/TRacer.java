
public class TRacer implements Racer {
	private final int id;
	protected long start;
	protected long finish;
	protected boolean ended;
	
	public TRacer(int id){
		this.id=id;
		start=-1;
		finish=-1;
		ended=false;
	}
	
	public String toString(){
		if(ended)
			return id+" "+TimeManager.formatTime(start)+" "+TimeManager.formatTime(finish)+" "+" "+ getElapsedTime();
		else
			return id+" "+TimeManager.formatTime(start)+" "+TimeManager.formatTime(TimeManager.getTime())+" "+" "+ getElapsedTime();
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public boolean didNotFinish() {
		return ended && finish == -1;
	}
	
	@Override
	public long getStartTime() {
		return start;
	}

	@Override
	public long getFinishTime() {
		return finish;
	}
}
