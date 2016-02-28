
public class Channel {
	
	/**
	 * The channels id.
	 */
	public final int id;
	
	/**
	 * The ChronoTimer that this channel is owned by.
	 */
	public final ChronoTimer timer;
	
	/**
	 * The sensor that is attached to this channel.
	 */
	protected SensorType t;
	
	/**
	 * The sensors state, if it is enabled or not.
	 */
	private boolean enabled;
	
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
	
	public boolean toggle() {
		enabled = !enabled;
		return true;
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
	
	public boolean trigger() {
		return timer.trigger(id);
	}
	
	public Channel(ChronoTimer timer, int id) {
		this.id = id;
		this.timer = timer;
		t = SensorType.NONE;
		enabled = false;
	}
	
	public Channel(ChronoTimer timer, int id, boolean enabled) {
		this.id = id;
		this.timer = timer;
		this.enabled = enabled;
	}
	
	public Channel(ChronoTimer timer, int id, SensorType t) {
		this.id = id;
		this.timer = timer;
		this.t = t;
		enabled = false;
	}
	
	public Channel(ChronoTimer timer, int id, SensorType t, boolean enabled) {
		this.id = id;
		this.timer = timer;
		this.t = t;
		this.enabled = enabled;
	}
	
}
