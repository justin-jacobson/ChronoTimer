import java.util.LinkedList;

public class ChronoTimer {
	
	public static final int CH_MAX = 12;
	static private boolean power;
	
	//This is not a good idea to make it static.
	//However, I do not have a idea how new runner connected to specific channel.
	public static Channel[] channels;
	private LinkedList<Run> runs;
	
	public ChronoTimer(){ 
		this.power = false;
		this.channels= new Channel[CH_MAX];
		this.runs=new LinkedList<Run>();
	}
	
	public boolean tooglePower(){
		power = !power;
		return true;
	}
	
	public void reset(){
		this.power = false;
		this.channels= new Channel[CH_MAX];
		this.runs=new LinkedList<Run>();
		//setToDefault();
	}
	
	public boolean toggleChanel(int ch){
		if(!power) return false;
		if(channels[ch].isEnabled())
			channels[ch].disable();
		else
			channels[ch].enable();
		return true;
	}
	
	public boolean connect(SensorType t, int ch){
		if(!power || !channels[ch].isEnabled()) return false;
		channels[ch].setSensor(t);
		return true;
	}
	
	public boolean disconnect(int ch){
		if(!power || !channels[ch].isEnabled()) return false;
		channels[ch].setSensor(SensorType.NONE);
		return true;
	}
	public boolean trigger(int ch){
		if(!power || !channels[ch].isEnabled()) return false;
		channels[ch].trigger((ch%2));
		return true;
	}
	
	public void doNotFin(){
		runs.getLast().racers.getLast().ended=true;
	}
	
	public boolean newRun(){
		if(!power) return false;
		//if "runs" is not empty and last runs was not finished >> false
		if(!runs.isEmpty() && !runs.getLast().finished) return false;
		runs.add(new Run());
		return true;
	}
	
	public boolean endRun(){
		if(!power && runs.isEmpty()) return false;
		//Gun Q: is there something to check before make it finish?
		this.runs.getLast().finished=true;
		return true;
	}
	//NO NEED TO IMPLEMENT NOW
	public boolean swap(){
		return false;
	}
	
	/**
	 * Print out specific run into standard out format.
	 * @param run
	 * @return racer's records in run. start, end, and a lap time each line.
	 */
	public String print(int run){
		return runs.get(run).toString();
	}
}
