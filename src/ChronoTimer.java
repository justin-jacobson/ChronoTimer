import java.util.LinkedList;

public class ChronoTimer {
	
	public static final int CH_MAX = 12;
	static private boolean power;
	
	public TimeManager time;
	public Channel[] channels;
	private LinkedList<Run> runs;
	
	public ChronoTimer(){ 
		this.power = false;
		this.time=new TimeManager();
		this.channels= new Channel[CH_MAX];
		this.runs=new LinkedList<Run>();
	}
	
	public boolean tooglePower(){
		power = !power;
		return true;
	}
	
	public void reset(){
		this.power = false;
		this.time=new TimeManager();
		this.channels= new Channel[CH_MAX];
		this.runs=new LinkedList<Run>();
		this.setToDefault();
	}
	
	public boolean toggleChanel(int ch){
		if(!power) return false;
		if(channels[ch].isEnabled()){
			channels[ch].disable();
		}else{
			channels[ch].enable();
		}
		return true;
	}
	
	public boolean connect(SensorType t, int ch){
		if(!power) return false;
		if(!channels[ch].isEnabled()) return false;
		
		channels[ch].setSensor(t);
		
		return true;
	}
	
	public boolean disconnect(int ch){
		if(!power) return false;
		if(!channels[ch].isEnabled()) return false;
		
		channels[ch].setSensor(SensorType.NONE);
		
		return false;
	}
	//Gun: I need help at here!
	public boolean trigger(int ch){
		return false;
	}
	public void doNotFin(){
		
	}
	
	public boolean newRun(){
		if(!power) return false;
		//if "runs" is not empty and last runs was not finished >> false
		if(!runs.isEmpty() && !runs.getLast().finished) return false;
		
		runs.add(new Run());
		
		return true;
	}
	
	public boolean endRun(){
		if(!power) return false;
		if(runs.isEmpty()) return false;
		
		//Gun Q: is there something to check before make it finish?
		this.runs.getLast().finished=true;
		
		return true;
	}
	//NO NEED TO IMPLEMENT NOW
	public boolean swap(){
		return false;
	}
	
	public String print(int run){
		return "";
	}
	
	private boolean setToDefault(){
		return false;
	}
}
