
import java.util.concurrent.TimeUnit;


public class TimeManager {
	private static long system_time;
	/**
	 * Get input from user. "TIME hour:min:sec" string format.
	 * Set system_time from 'time' to 'system_time'.
	 * @param time
	 */
	public static void setTime(String time){
		String format[] = time.split(":");
		if(time.contains("."))
			format[2] = format[2].substring(0,format[2].indexOf("."));
		system_time = System.currentTimeMillis() - intoMillisecs(format);
	}
	
	public static long intoMillisecs(String unformatted) {
		String[] s = unformatted.split(":");
		s[s.length-1] = s[s.length-1].substring(0, s[s.length-1].indexOf("."));
		return intoMillisecs(s);
	}
	
	public static long intoMillisecs(String[] unformatted) {
		long hrs, mins, secs;

		hrs = TimeUnit.MILLISECONDS.convert(Long.parseLong(unformatted[0]), TimeUnit.HOURS);
		mins = TimeUnit.MILLISECONDS.convert(Long.parseLong(unformatted[1]), TimeUnit.MINUTES);
		secs = TimeUnit.MILLISECONDS.convert(Long.parseLong(unformatted[2]), TimeUnit.SECONDS);
		return (hrs + mins + secs);
	}
	public static long getTime(){
		return subtractTime(System.currentTimeMillis(),system_time);
	}
	/**
	 * get absolute time and return into "hour:min:sec" string format.
	 * @param input(system_time)
	 * @return "hour:min:sec"
	 */
	public static String formatTime(long input){
		if(input < 0) return "NOT RECORDED";
		return String.format("%d:%s:%s.%d",
					TimeUnit.MILLISECONDS.toHours(input),
					String.format( "%02d",TimeUnit.MILLISECONDS.toMinutes(input) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(input))),
					String.format( "%02d",TimeUnit.MILLISECONDS.toSeconds(input) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(input))),
					TimeUnit.MILLISECONDS.toMillis(input) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(input)));
	}
	public static long subtractTime(long first, long second){
		return first-second;
	}

}
