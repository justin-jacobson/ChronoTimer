
public interface Channel {
	
	public int getID();
	public SensorType getSensorType();
	public boolean isEnabled();
	public boolean setEnabled(boolean e);
	
	public default boolean enable() {
		return setEnabled(true);
	}
	
	public default boolean disable() {
		return setEnabled(false);
	}
	
	/**
	 * Triggers this channel.
	 * @return
	 */
	public boolean trigger();
	
}
