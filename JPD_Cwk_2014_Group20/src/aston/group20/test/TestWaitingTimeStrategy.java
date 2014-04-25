package aston.group20.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import aston.group20.model.CommercialAircraft;
import aston.group20.model.LightAircraft;
import aston.group20.model.WaitingTimeStrategy;

public class TestWaitingTimeStrategy {

	WaitingTimeStrategy strategy;
	LightAircraft l1;
	CommercialAircraft c1;
	CommercialAircraft cn;

	@Before
	public void setUp() throws Exception {
		strategy = new WaitingTimeStrategy();
		
		c1 = new CommercialAircraft();
		l1 = new LightAircraft();
		cn = null;	
	}
	
	@Test
	public void testCompare() {
		c1.setWaitingTime(5);
		l1.setWaitingTime(3);
		
		assertEquals(1, strategy.compare(c1, l1)); // commercial has a higher waiting time
		assertEquals(-1, strategy.compare(l1, c1)); // light has a lower waiting time
		
		l1.setWaitingTime(7);
		
		assertEquals(-1, strategy.compare(c1, l1)); // commercial has a lower waiting time
		assertEquals(1, strategy.compare(l1, c1)); // light has a higher waiting time
	}
	
	@Test
	public void testSchedule() {
		assertEquals(1, strategy.schedule(c1, l1)); // if there's an incoming Aircraft it can always land
		assertEquals(1, strategy.schedule(l1, c1)); // if there's an incoming Aircraft it can always land
		assertEquals(1, strategy.schedule(l1, cn)); // if there's an incoming Aircraft it can always land
		assertEquals(1, strategy.schedule(c1, cn)); // if there's an incoming Aircraft it can always land
		
		assertEquals(0, strategy.schedule(cn, cn)); // two null Aircrafts should do nothing (return 0)
		
		assertEquals(2, strategy.schedule(cn, c1)); // no incoming Aircraft so should take off (return 2)
		assertEquals(2, strategy.schedule(cn, l1)); // no incoming Aircraft so should take off (return 2)
	}
	
}
