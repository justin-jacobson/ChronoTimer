
public class TChannel implements Channel {
	
	/**
	 * The channels id.
	 */
	public final int id;
	
	/**
	 * The ChronoTimer that this channel is owned by.
	 */
	public final TChronoTimer timer;
	
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
	
	public TChannel(TChronoTimer timer, int id) {
		this.id = id;
		this.timer = timer;
		t = SensorType.NONE;
		enabled = false;
	}
	
	public TChannel(TChronoTimer timer, int id, boolean enabled) {
		this.id = id;
		this.timer = timer;
		this.enabled = enabled;
	}
	
	public TChannel(TChronoTimer timer, int id, SensorType t) {
		this.id = id;
		this.timer = timer;
		this.t = t;
		enabled = false;
	}
	
	public TChannel(TChronoTimer timer, int id, SensorType t, boolean enabled) {
		this.id = id;
		this.timer = timer;
		this.t = t;
		this.enabled = enabled;
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public SensorType getSensorType() {
		return t;
	}

	@Override
	public boolean setEnabled(boolean e) {
		if (!timer.isOn()) return false;
		boolean old = enabled;
		enabled = e;
		return old != enabled;
	}

	@Override
	public void SetSensorType(SensorType s) {
		if (!timer.isOn()) return;
		if(s == null) s = SensorType.NONE;
		t = s;
	}
	
}
