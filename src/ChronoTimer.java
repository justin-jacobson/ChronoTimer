import java.util.LinkedList;

public class ChronoTimer {
	
	public static final int CH_MAX = 12;
	static private boolean power;
	
	public TimeManager time;
	public Channel[] channels;
	private LinkedList<Run> runs;
	
	public ChronoTimer(){ 
		
	}
	
	public boolean tooglePower(){
		return false;
	}
	public void reset(){
		return;
	}
	
	public boolean toggleChanel(int ch){
		return false;
	}
	public boolean connect(sensorType t, int ch){
		return false;
	}
	public boolean disconnect(int ch){
		return false;
	}
	public boolean trigger(int ch){
		return false;
	}
	public void doNotFin(){
		
	}
	public boolean newRun(){
		return false;
	}
	public boolean endRun(){
		return false;
	}
	public boolean swap(){
		return false;
	}
	public String print(int run){
		return "";
	}
}
