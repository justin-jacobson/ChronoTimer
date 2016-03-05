package team;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestTimeManager {

	@Test
	public void test() {
		
		TimeManager.setTime("12:00:00");
		long amount = 0, start = TimeManager.getTime();
		final Random r = new Random();
		final int iterations = 25;
		for(int i=1; i<iterations; ++i) {
			int add = r.nextInt(15 + 5);
			amount += add;
			try {
				TimeUnit.MILLISECONDS.sleep(add);
			} catch (InterruptedException e) {
				
			}
			TestChronoTimer.assertRange(amount, TimeManager.getTime() - start, 2);
		}
		
	}
	
}
