import static org.junit.Assert.*;

import java.util.List;
import java.util.LinkedList;
import org.junit.Test;


public class TestChronoTimer {

	@Test
	public void testPower() {
		TChronoTimer timer = new TChronoTimer();
		assertTrue(!timer.isOn());
		assertTrue(timer.togglePower());
		assertTrue(timer.togglePower());
		assertTrue(timer.powerOn());
		assertTrue(timer.powerOff());
	}
	
	@Test
	public void testReset() {
		TChronoTimer timer = new TChronoTimer();
		assertTrue(timer.powerOn());
		assertTrue(timer.connect(SensorType.PAD, 1));
		assertTrue(timer.connect(SensorType.GATE, 2));
		assertNotNull(timer.getLatestRun().addRacer(5));
		assertTrue(timer.toggleChanel(1));
		TimeManager.setTime("00:00:00");
		assertTrue(timer.trigger(1));
		assertRange(timer.getLatestRun().getRacers().get(0).getStartTime(), 0, 3);
		assertEquals(timer.getLatestRun().getRacers().get(0).getFinishTime(), -1);
		assertTrue(timer.reset());
		assertEquals(0,timer.getLatestRun().getRacers().size());
	}
	
	@Test
	public void testChannels() {
		TChronoTimer timer = new TChronoTimer();
		assertTrue(timer.powerOn());
		
		List<Channel> channels = timer.getChannels();
		assertTrue(channels != null);
		assertTrue(channels.size() == ChronoTimer.MAXIMUM_CHANNELS);
		int i=1;
		for(Channel c : channels) {
			assertTrue(c != null);
			assertEquals(i,c.getID());
			assertEquals(SensorType.NONE, c.getSensorType());
			assertFalse(c.isEnabled());
			assertTrue(c.enable());
			//Test Channel enable/disable/connect
			for(int j=0; j<10; ++j) {
				SensorType s = SensorType.values()[(int)(Math.random()*SensorType.values().length)];
				c.SetSensorType(s);
				assertEquals(s, c.getSensorType());
				c.SetSensorType(SensorType.NONE);
				assertEquals(SensorType.NONE, c.getSensorType());
				assertTrue(timer.disconnect(i));
				assertFalse(c.isEnabled());
			}
			//Test ChronoTimer connect/disconnect
			for(int j=0; j<10; ++j) {
				SensorType s = SensorType.values()[(int)(Math.random()*SensorType.values().length)];
				assertTrue(timer.connect(s, i));
				assertFalse(c.isEnabled());
				assertTrue(c.enable());
				assertTrue(timer.disconnect(i));
			}
			++i;
		}
	}
	
	@Test
	public void testTrigger() {
		TChronoTimer timer = new TChronoTimer();
		for(int i=1; i<ChronoTimer.MAXIMUM_CHANNELS+1; ++i) {
			assertFalse(timer.trigger(i));
		}
		assertTrue(timer.powerOn());
		for(int i=1; i<ChronoTimer.MAXIMUM_CHANNELS+1; ++i) {
			assertFalse(timer.trigger(i));
		}
		assertTrue(timer.connect(SensorType.EYE, 1));
		assertTrue(timer.connect(SensorType.EYE, 2));
		assertFalse(timer.trigger(1));
		assertFalse(timer.trigger(2));
		assertTrue(timer.toggleChanel(1));
		assertTrue(timer.getChannels().get(0).isEnabled());
		assertTrue(timer.toggleChanel(2));
		assertTrue(timer.getChannels().get(1).isEnabled());
		Racer second = timer.getLatestRun().addRacer(25);
		assertNotNull(second);
		Racer first = timer.getLatestRun().addRacer(72);
		assertNotNull(first);
		TimeManager.setTime("00:00:00");
		assertTrue(timer.trigger(1));
		assertEquals(72, first.getID());
		assertRange(0,first.getStartTime(), 3);
		TimeManager.setTime("00:01:00");
		assertTrue(timer.trigger(2));
		assertRange(60*1000, first.getFinishTime(), 3);
		assertRange(60*1000, first.getFinishTime(), 3);
		assertFalse(timer.trigger(2));
		assertEquals(-1,second.getStartTime());
		assertEquals(-1,second.getFinishTime());
		TimeManager.setTime("00:01:30");
		assertTrue(timer.trigger(1));
		TimeManager.setTime("00:02:20");
		assertTrue(timer.trigger(2));
		assertRange(second.getStartTime(), 60*1500, 3);
		assertRange(second.getFinishTime(), 60*2000 + 20*1000, 3);
		assertTrue(timer.endRun());
		assertFalse(timer.endRun());
	}
	
	public void testRunner() {
		TChronoTimer timer = new TChronoTimer();
		assertTrue(timer.isOn());
		LinkedList<TRacer> racers = new LinkedList<TRacer>();
		
		//create list of 10 racers, test initial conditions are set properly
		for(int i = 0; i < 10; i++) {
			racers.add(new TRacer(i));
			assertEquals(racers.get(i).getID(), i);
			//since didNotFinish returns if both start/end time aren't set
			//this can be used to test if initial conditions are correct.
			assertTrue(racers.get(i).didNotFinish());
			assertFalse(racers.get(i).ended);
		}
		
		Run latest = timer.getLatestRun();
		for(TRacer r : racers) latest.addRacer(r.getID());
		//Start a race and test that each runner has a valid start/end time
		//not equal to -1. Then test that finish time > start time
		
	}
	
	/**
	 * Assert a range if the timings were off very slightly.
	 * @param result - The result
	 * @param expected - the Expected value
	 * @param range - The accepted range.
	 */
	private void assertRange(long result, long expected, long range) {
		assertFalse(Math.abs(result-expected) > range);
	}
	
}
