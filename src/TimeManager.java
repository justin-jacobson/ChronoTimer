import java.util.concurrent.*;
public class TimeManager {
	private static long system_time;
	/**
	 * Get input from user. "TIME hour:min:sec" string format.
	 * Set system_time from 'time' to 'system_time'.
	 * @param time
	 */
	public static void setTime(String time){
		String format[] = time.split(":");
		format[0] = format[0].substring(5);
		
		system_time = intoMillisecs(format);
	}
	
	private static long intoMillisecs(String[] unformatted) {
		long hrs, mins, secs; 
		
		hrs = TimeUnit.MILLISECONDS.convert(Long.parseLong(unformatted[0]), TimeUnit.HOURS);
		mins = TimeUnit.MILLISECONDS.convert(Long.parseLong(unformatted[1]), TimeUnit.MINUTES);
		secs = TimeUnit.MILLISECONDS.convert(Long.parseLong(unformatted[2]), TimeUnit.SECONDS);
		return (hrs + mins + secs);
	}
	/**
	 * 
	 * @return (long)system_time : must return relative time
	 */
	public static long getTime(){
		return system_time;
	}
	/**
	 * get absolute time and return into "hour:min:sec" string format.
	 * @param input(system_time)
	 * @return "hour:min:sec"
	 */
	public static String formatTime(long input){
		return "";
	}
	public static long subtractTime(long first, long second){
		return first-second;
	}
	
}
