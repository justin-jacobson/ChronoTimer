package team;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestTRun {

	@Test
	public void hasStarted() {
		ChronoTimer timer = new TChronoTimer();
		TRun one =new TRun(timer, 1);
		assertEquals(one.hasStarted(),false);
		one.finished=true;
		assertEquals(one.hasStarted(),true);
		one.finished=false;
		one.racers.add(new TRacer(1));
		assertEquals(one.hasStarted(),false);
		one.racers.getFirst().start=10;
		assertEquals(one.hasStarted(),true);
	}
	@Test
	public void addRacer() {
		ChronoTimer timer = new TChronoTimer();
		timer.powerOn();
		TRun one =new TRun(timer, 1);
		TRacer target =(TRacer)one.addRacer(0);
		assertNotNull(target);
		assertEquals(target, one.racers.get(0));
		assertEquals(target, one.toStart.get(0));
		
		target =(TRacer)one.addRacer(1);
		assertNotNull(target);
		assertEquals(target, one.racers.get(0));
		assertEquals(target, one.toStart.get(0));
	}
	
	@Test
	public void removeRacer() {
		ChronoTimer timer = new TChronoTimer();
		timer.powerOn();
		TRun one =new TRun(timer, 1);
		one.racers.addFirst(new TRacer(0));
		one.toStart.addFirst(new TRacer(0));
		one.racers.addFirst(new TRacer(1));
		one.toStart.addFirst(new TRacer(1));
		one.finished=true;
		assertEquals(one.removeRacer(0),false);
		one.finished=false;
		assertFalse(one.removeRacer(3));
		assertTrue(one.racers.get(0)!=null);
		assertTrue(one.toStart.get(0)!=null);
		assertTrue(one.racers.get(1)!=null);
		assertTrue(one.toStart.get(1)!=null);
		
		assertTrue(one.removeRacer(0));
		assertEquals(one.racers.getFirst().getID(), 1);
		assertEquals(one.toStart.getFirst().getID(), 1);
		
	}
}
