
public class Channel {
	
	/**
	 * The sensor that is attached to this channel.
	 */
	protected SensorType t;
	/**
	 * The sensors state, if it is enabled or not.
	 */
	private boolean enabled;
	private Racer target;
	
	/**
	 * Returns true if the channel is enabled.
	 * @return true if channel is enabled. False if channel is disabled.
	 */
	public boolean isEnabled(){
		return enabled;
	}
	
	/**
	 * Enables the channel.
	 * @return true if the channel was disabled originally. False if the channel was already on.
	 */
	public boolean enable(){
		boolean old = enabled;
		enabled = true;
		return old != enabled;
	}
	
	/**
	 * Disables the channel.
	 * @return true if the channel was enabled originally. False if the channel was already off.
	 */
	public boolean disable(){
		boolean old = enabled;
		enabled = false;
		return old != enabled;
	}
	
	/**
	 * Attaches a sensor to the channel.
	 * @param type - the new sensor type.
	 * @return True if the new sensor is different from previous sensor.
	 */
	public boolean setSensor(SensorType type){
		if(type == null) type = SensorType.NONE;
		SensorType old = t;
		t = type;
		return old != t;
	}
	
	public Channel() {
		t = SensorType.NONE;
		enabled = false;
	}
	
	public Channel(boolean enabled) {
		this.enabled = enabled;
	}
	
	public Channel(SensorType t) {
		this.t = t;
		enabled = false;
	}
	
	public Channel(SensorType t, boolean enabled){
		this.t = t;
		this.enabled = enabled;
	}
	
	public boolean trigger(int n){
		if(target.ended) return false;
		target.setTime(n);
		return true;
	}
	
}
