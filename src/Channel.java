
public class Channel {
	public sensorType t;
	public boolean enabled;
	
	public Channel(sensorType t, boolean enabled){
		
	}
	
	public boolean isEnabled(){
		return enabled;
	}
	
	public boolean enable(){
		return false;
	}
	
	public boolean disable(){
		return false;
	}
	
	public boolean changeSensor(sensorType type){
		return false;
	}
}
