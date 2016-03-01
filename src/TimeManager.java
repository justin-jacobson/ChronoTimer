import java.util.concurrent.TimeUnit;

public class TimeManager {
	private static long system_time;
	/**
	 * Get input from user. "TIME hour:min:sec" string format.
	 * Set system_time from 'time' to 'system_time'.
	 * @param time
	 */
	public static void setTime(String time){
		
	}
	/**
	 * 
	 * @return (long)(current time - system_time)
	 */
	public static long getTime(){
		return subtractTime(System.currentTimeMillis(),system_time);
	}
	/**
	 * get absolute time and return into "hour:min:sec" string format.
	 * @param input(system_time)
	 * @return "hour:min:sec"
	 */
	public static String formatTime(long input){
		return String.format("%d:%d:%d", 
					TimeUnit.MILLISECONDS.toHours(input),
					TimeUnit.MILLISECONDS.toMinutes(input) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(input)),
					TimeUnit.MILLISECONDS.toSeconds(input) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(input)));
	}
	public static long subtractTime(long first, long second){
		return first-second;
	}
	
}
