import java.util.List;


public interface ChronoTimer {
	
	/**
	 * The maximum amount of channels for any ChronoTimer.
	 */
	public static final int MAXIMUM_CHANNELS = 12;
	
	public boolean isOn();
	
	public boolean setPower(boolean on);
	
	public default boolean powerOn() {
		return setPower(true);
	}
	
	public default boolean powerOff() {
		return setPower(false);
	}
	
	public default boolean togglePower() {
		if(isOn())
			setPower(false);
		else
			setPower(true);
		return true;
	}
	
	public boolean setEvent(EventType event);
	
	public List<Channel> getChannels();
	public List<Run> getRuns();
	
	public Run getLatestRun();
	
	public Run newRun();
	public boolean endRun();
	
	public boolean reset();
	
	public boolean trigger(Channel c);
	
	public default boolean trigger(int c) {
		if(c < 1 || c > MAXIMUM_CHANNELS) return false;
		return trigger(getChannels().get(c-1));
	}
	
	public default boolean connect(SensorType s, Channel c) {
		if(!isOn() || c == null) return false;
		if(s == null) s = SensorType.NONE;
		c.setEnabled(false);
		c.SetSensorType(s);
		return true;
	}
	
	public default boolean connect(SensorType s, int c) {
		if(c < 1 || c > MAXIMUM_CHANNELS) return false;
		return connect(s,getChannels().get(c-1));
	}
	
	public default boolean disconnect(Channel c) {
		if(!isOn() || c == null) return false;
		c.disable();
		c.SetSensorType(SensorType.NONE);
		return true;
	}
	
	public default boolean disconnect(int c) {
		if(c < 1 || c > MAXIMUM_CHANNELS) return false;
		return disconnect(getChannels().get(c-1));
	}
	
	public boolean doNotFinish();
	
	public boolean swap();
	
}
