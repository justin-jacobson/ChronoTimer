import java.util.List;


public interface ChronoTimer {
	
	public boolean isOn();
	
	public boolean setPower(boolean on);
	
	public default boolean powerOn() {
		return setPower(true);
	}
	
	public default boolean powerOff() {
		return setPower(false);
	}
	
	public List<Channel> getChannels();
	
	public List<Run> getRuns();
	
	public Run newRun();
	public boolean endRun();
	
	public boolean reset();
	
	public default boolean trigger(Channel c) {
		if(c == null) return false;
		return trigger(c.getID());
	}
	
	public boolean trigger(int c);
	
	public boolean connect(SensorType s, int c);
	
	public default boolean connect(SensorType s, Channel c) {
		if(c == null) return false;
		return connect(s, c.getID());
	}
	
	public boolean disconnect(int c);
	
	public default boolean disconnect(Channel c) {
		if(c == null) return false;
		return disconnect(c.getID());
	}
	
	public boolean doNotFinish();
	
	public boolean swap();
	
}
