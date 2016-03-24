package team;

public enum EventType {
	IND(IndependentRun.class),
	PARIND(ParallelIndependentRun.class),
	GRP(null),
	PARGRP(null);
	
	private final Class<? extends TRun> c;
	
	public final TRun getNewInstance() {
		try {
			return c.newInstance();
		} catch (Exception e) {
			return null;
		}
	}
	
	public final TRun getNewInstanceFromOld(TRun old) {
		try {
			return c.getConstructor(TRun.class).newInstance(old);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	EventType(Class<? extends TRun> nc) {
		c = nc;
	}
	
}
