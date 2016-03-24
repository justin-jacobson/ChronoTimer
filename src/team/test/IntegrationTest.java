package team.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import team.ChronoTimer;
import team.EventType;
import team.Record;
import team.Run;
import team.SensorType;
import team.TChronoTimer;
import team.TimeManager;
/*
 * This is black box testing between components.
 * 1. ChronoTimer and IND
 * 2. ChronoTimer and PARIND
 * 3. Run and Runner
 */
//Do not manipulate direct to component!
//This is "Black Box" testing, which means you can only access to exposed method and field.
public class IntegrationTest {

	@Test
	public void testIND() {
		
		ChronoTimer timer = new TChronoTimer();
		
		assertFalse(timer.trigger(1));
		assertFalse(timer.trigger(2));
		assertTrue(timer.powerOn());
		assertFalse(timer.trigger(1));
		assertFalse(timer.trigger(2));
		
		timer.getChannels().get(0).setSensorType(SensorType.EYE);
		assertFalse(timer.trigger(1));
		assertEquals(timer.getChannels().get(0).getSensorType(),SensorType.EYE);
		timer.getChannels().get(1).setSensorType(SensorType.EYE);
		assertFalse(timer.trigger(2));
		assertEquals(timer.getChannels().get(1).getSensorType(),SensorType.EYE);
		
		Run run = timer.getLatestRun();
		assertTrue(run != null);
		assertEquals(run.getEventType(), EventType.IND);
		assertTrue(run.getRecords().isEmpty());
		
		assertFalse(run.addRacer(25) == null);
		assertTrue(run.addRacer(25) == null);
		assertFalse(run.addRacer(5) == null);
		assertTrue(run.addRacer(5) == null);
		assertFalse(run.addRacer(7) == null);
		assertFalse(run.addRacer(2) == null);
		assertTrue(run.removeRacer(7));
		assertFalse(run.removeRacer(7));
		
		assertFalse(timer.trigger(1));
		assertFalse(timer.getChannel(1).isEnabled());
		assertFalse(timer.trigger(2));
		assertFalse(timer.getChannel(2).isEnabled());
		assertTrue(timer.getChannel(1).enable());
		assertTrue(timer.getChannel(2).enable());
		assertTrue(timer.getChannel(1).isEnabled());
		assertTrue(timer.getChannel(2).isEnabled());
		
		TimeManager.setTime("12:00:00");
		assertTrue(timer.trigger(1));
		assertTrue(timer.doNotFinish());
		assertTrue(timer.trigger(2));
		assertTrue(timer.trigger(1));
		assertTrue(timer.trigger(2));
		
		Record rec = run.getRecord(25);
		assertTrue(rec != null);
		assertFalse(rec.didNotFinish());
		
		rec = run.getRecord(5);
		assertTrue(rec != null);
		assertFalse(rec.didNotFinish());
		
		rec = run.getRecord(2);
		assertTrue(rec != null);
		assertTrue(rec.didNotFinish());
		
	}

}
