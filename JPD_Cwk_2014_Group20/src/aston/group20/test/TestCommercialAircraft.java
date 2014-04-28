package aston.group20.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import aston.group20.model.CommercialAircraft;

public class TestCommercialAircraft {

	CommercialAircraft c1;
	CommercialAircraft c2;
	CommercialAircraft c3;
	
	@Before
	public void setUp() throws Exception {
		Random rand = new Random(17);
		c1 = new CommercialAircraft(rand.nextInt(40) + 40);
		c2 = new CommercialAircraft(rand.nextInt(40) + 40);
		c3 = new CommercialAircraft(rand.nextInt(40) + 40);
	}

	@Test
	public void testWaitingTime() {
		assertEquals(0, c1.getWaitingTime()); // checking that they start off at 0
		assertEquals(0, c2.getWaitingTime()); // checking that they start off at 0
		assertEquals(0, c3.getWaitingTime()); // checking that they start off at 0
		
		for (int i = 0; i < 10; i++) { // 10 steps for c1
			c1.step();
		}
		
		c2.setWaitingTime(10); // set c2's time to 10
		
		c3.setWaitingTime(5); // set c3's time to 5
		for (int i = 0; i < 5; i++) { // 5 steps for c1
			c3.step();
		}
		
		assertEquals(10, c1.getWaitingTime()); // they should all be at 10 now
		assertEquals(10, c2.getWaitingTime()); // they should all be at 10 now
		assertEquals(10, c3.getWaitingTime()); // they should all be at 10 now

		c1.incrementWaitingTime(); // add 1 more to c1
		assertEquals(11, c1.getWaitingTime()); // should be at 11
		
		c2.incrementWaitingTime(); // add 2 more to c2
		c2.incrementWaitingTime(); // add 2 more to c2
		assertEquals(12, c2.getWaitingTime()); // should be at 12
	}
	
	@Test 
	public void testMaintenanceTime() {
		assertEquals(c1.getMaintenanceTime(), 0); // should be set to 0 by default
		assertEquals(c2.getMaintenanceTime(), 0); // should be set to 0 by default
		assertEquals(c3.getMaintenanceTime(), 0); // should be set to 0 by defaults
		
		c1.setBrokeDown(true);
		c1.step();		
		assertEquals(c1.getMaintenanceTime(), 1); // should be 1, because it is brokedown and had 1 step
		
		c2.step(); c2.step();
		assertEquals(c2.getMaintenanceTime(), 0); // shouldn't be affected by the step() method because it isn't broke down
		
		c3.setBrokeDown(true);
		assertEquals(c3.getMaintenanceTime(), 0); // no steps yet, so still at 0
		for (int i = 0; i < 60; i++) {
			c3.step();
		}
		assertEquals(c3.getMaintenanceTime(), 60); // 60 steps, so the time should be at 60
		for (int i = 0; i < 60; i++) {
			c3.step();
		}
		assertEquals(c3.getMaintenanceTime(), 0); // Aircraft is fixed so reset to 0
	}
	
	@Test
	public void testTakeoffTime() {
		assertEquals(4, c1.getTakeoffTime());
		assertEquals(4, c2.getTakeoffTime());
		assertEquals(4, c3.getTakeoffTime());
	}
	
	@Test
	public void testLandingTime() {
		assertEquals(6, c1.getLandingTime());
		assertEquals(6, c2.getLandingTime());
		assertEquals(6, c3.getLandingTime());
	}
	
	@Test
	public void testFlyingBoolean() {
		assertFalse(c1.isFlying());
		assertFalse(c2.isFlying());
		assertFalse(c3.isFlying());
		
		c1.setIsFlying(true);
		assertTrue(c1.isFlying());
		
		c1.setIsFlying(false);
		assertFalse(c1.isFlying());
	}

	@Test 
	public void testCrashedBoolean() {
		assertFalse(c1.isCrashed());
		assertFalse(c2.isCrashed());
		assertFalse(c3.isCrashed());
		
		c1.setIsFlying(true);
		int fuelLevel = c1.getFuelLevel();
		for (int i = 0; i < fuelLevel; i++) {
			c1.step(); // put the fuel level down to 0, meaning the aircraft should have crashed
		}
		assertTrue(c1.isCrashed()); // the aircraft has crashed, which is correct
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(0, c1.compareTo(c1));
		assertEquals(1, c1.compareTo(c2));
		assertEquals(-1, c2.compareTo(c1));
		assertEquals(-1, c3.compareTo(c2));
		assertEquals(1, c2.compareTo(c3));
		assertEquals(1, c1.compareTo(c3));
		assertEquals(-1, c3.compareTo(c1));
	}
	
}