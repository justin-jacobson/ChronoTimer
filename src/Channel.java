
public interface Channel {
	
	public int getID();
	
	public SensorType getSensorType();
	public void SetSensorType(SensorType s);
	
	public boolean isEnabled();
	public boolean setEnabled(boolean e);
	
	public default boolean enable() {
		return setEnabled(true);
	}
	
	public default boolean disable() {
		return setEnabled(false);
	}
	
	public default boolean toggle() {
		if(isEnabled())
			return disable();
		else
			return enable();
	}
	
	/**
	 * Triggers this channel.
	 * @return
	 */
	public boolean trigger();
	
}
