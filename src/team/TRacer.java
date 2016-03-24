package team;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TRacer implements Racer {
	
	protected static final Map<Integer,TRacer> racers = new HashMap<Integer,TRacer>();
	
	protected static final boolean racerExists(int id) {
		return racers.containsKey(id);
	}
	
	protected static final TRacer getRacer(int id) {
		TRacer r = racers.get(id);
		if(r == null) {
			r = new TRacer(id);
			racers.put(id, r);
		}
		return r;
	}
	
	public final int id;
	protected Map<Integer,TRecord> records = new HashMap<Integer,TRecord>(), safe_records;

	@Override
	public int getID() {
		return id;
	}
	
	public String toString(){
		return "Racer " + id;
	}
	
	@Override
	public Map<Integer,TRecord> getRecords() {
		return safe_records;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof TRacer)
			return id == ((TRacer)o).id;
		return false;
	}
	
	public TRacer(int id){
		this.id=id;
		safe_records = Collections.unmodifiableMap(records);
	}
	
}
