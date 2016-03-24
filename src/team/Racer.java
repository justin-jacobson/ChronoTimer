package team;

import java.util.Map;

public interface Racer {
	
	public static Racer getRacer(int id) {
		return TRacer.racers.get(id);
	}
	
	/**
	 * @return The racers ID.
	 */
	public int getID();
	
	public Map<Integer,? extends Record> getRecords();
	
	
}
