package aston.group20.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import aston.group20.model.LightAircraft;

public class TestLightAircraft {

	Random rand = new Random(17);
	LightAircraft l1;
	LightAircraft l2;
	LightAircraft l3;
	
	@Before
	public void setUp() throws Exception {
		l1 = new LightAircraft(rand.nextInt(20) + 20);
		l2 = new LightAircraft(rand.nextInt(20) + 20);
		l3 = new LightAircraft(rand.nextInt(20) + 20);
	}
	
	@Test
	public void testWaitingTime() {
		assertEquals(0, l1.getWaitingTime()); // checking that they start off at 0
		assertEquals(0, l2.getWaitingTime()); // checking that they start off at 0
		assertEquals(0, l3.getWaitingTime()); // checking that they start off at 0
		
		for (int i = 0; i < 10; i++) { // 10 steps for c1
			l1.step();
		}
		
		l2.setWaitingTime(10); // set c2's time to 10
		
		l3.setWaitingTime(5); // set c3's time to 5
		for (int i = 0; i < 5; i++) { // 5 steps for c1
			l3.step();
		}
		
		assertEquals(10, l1.getWaitingTime()); // they should all be at 10 now
		assertEquals(10, l2.getWaitingTime()); // they should all be at 10 now
		assertEquals(10, l3.getWaitingTime()); // they should all be at 10 now

		l1.incrementWaitingTime(); // add 1 more to c1
		assertEquals(11, l1.getWaitingTime()); // should be at 11
		
		l2.incrementWaitingTime(); // add 2 more to c2
		l2.incrementWaitingTime(); // add 2 more to c2
		assertEquals(12, l2.getWaitingTime()); // should be at 12
	}
	
	@Test 
	public void testMaintenanceTime() {
		assertEquals(l1.getMaintenanceTime(), 0); // should be set to 0 by default
		assertEquals(l2.getMaintenanceTime(), 0); // should be set to 0 by default
		assertEquals(l3.getMaintenanceTime(), 0); // should be set to 0 by defaults
		
		l1.setBrokeDown(true);
		l1.step();		
		assertEquals(l1.getMaintenanceTime(), 1); // should be 1, because it is brokedown and had 1 step
		
		l2.step(); l2.step();
		assertEquals(l2.getMaintenanceTime(), 0); // shouldn't be affected by the step() method because it isn't broke down
		
		l3.setBrokeDown(true);
		assertEquals(l3.getMaintenanceTime(), 0); // no steps yet, so still at 0
		for (int i = 0; i < 60; i++) {
			l3.step();
		}
		assertEquals(l3.getMaintenanceTime(), 60); // 60 steps, so the time should be at 60
		for (int i = 0; i < 60; i++) {
			l3.step();
		}
		assertEquals(l3.getMaintenanceTime(), 0); // Aircraft is fixed so reset to 0
	}
	
	@Test
	public void testTakeoffTime() {
		assertEquals(4, l1.getTakeoffTime());
		assertEquals(4, l2.getTakeoffTime());
		assertEquals(4, l3.getTakeoffTime());
	}
	
	@Test
	public void testLandingTime() {
		assertEquals(6, l1.getLandingTime());
		assertEquals(6, l2.getLandingTime());
		assertEquals(6, l3.getLandingTime());
	}
	
	@Test
	public void testFlyingBoolean() {
		assertFalse(l1.isFlying());
		assertFalse(l2.isFlying());
		assertFalse(l3.isFlying());
		
		l1.setIsFlying(true);
		assertTrue(l1.isFlying());
		
		l1.setIsFlying(false);
		assertFalse(l1.isFlying());
	}

	@Test 
	public void testCrashedBoolean() {
		assertFalse(l1.isCrashed());
		assertFalse(l2.isCrashed());
		assertFalse(l3.isCrashed());
		
		l1.setIsFlying(true);
		int fuelLevel = l1.getFuelLevel();
		for (int i = 0; i < fuelLevel; i++) {
			l1.step(); // put the fuel level down to 0, meaning the aircraft should have crashed
		}
		assertTrue(l1.isCrashed()); // the aircraft has crashed, which is correct
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(0, l1.compareTo(l1));
		assertEquals(1, l1.compareTo(l2));
		assertEquals(-1, l2.compareTo(l1));
		assertEquals(-1, l3.compareTo(l2));
		assertEquals(1, l2.compareTo(l3));
		assertEquals(1, l1.compareTo(l3));
		assertEquals(-1, l3.compareTo(l1));
	}
	
}