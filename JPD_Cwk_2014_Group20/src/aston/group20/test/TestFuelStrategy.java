package aston.group20.test;


import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import aston.group20.model.CommercialAircraft;
import aston.group20.model.FuelStrategy;
import aston.group20.model.LightAircraft;


public class TestFuelStrategy {

	Random rand = new Random(17);
	FuelStrategy strategy;
	LightAircraft l1;
	CommercialAircraft c1;
	CommercialAircraft cn;

	@Before
	public void setUp() throws Exception {
		strategy = new FuelStrategy();
		
		c1 = new CommercialAircraft(rand.nextInt(40) + 40);
		l1 = new LightAircraft(rand.nextInt(20) + 20);
		cn = null;	
	}
	
	@Test
	public void testCompare() {
		assertEquals(1, strategy.compare(c1, l1)); // commercial will always have more fuel than a light Aircraft
		assertEquals(-1, strategy.compare(l1, c1));
	}
	
	@Test
	public void testSchedule() {
		while (l1.getFuelLevel() > 13) {
			l1.step();
		}
		
		l1.setWaitingTime(0);
		c1.setWaitingTime(1); // outgoing Aircraft has been waiting longer, so 
		assertEquals(2, strategy.schedule(l1, c1)); // it should be allowed to takeoff
		assertEquals(1, strategy.schedule(c1, l1)); // c1 lands, due to it's higher waiting time
		
		l1.setWaitingTime(2);
		assertEquals(1, strategy.schedule(l1, c1)); // l1 lands, due to it's higher waiting time
		assertEquals(2, strategy.schedule(c1, l1)); // l1 takes off, due to it's higher waiting time, and it won't cause c1 to crash
		
		assertEquals(2, strategy.schedule(cn, c1)); // checking with a null Aircraft
		assertEquals(0, strategy.schedule(cn, cn)); // checking with a null Aircraft
		assertEquals(1, strategy.schedule(c1, cn)); // checking with a null Aircraft
	}
	
}