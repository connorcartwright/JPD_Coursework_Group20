package aston.group20.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import aston.group20.model.Glider;

public class TestGlider {


	Glider g1;
	Glider g2;
	Glider g3;
	
	@Before
	public void setUp() throws Exception {
		g1 = new Glider();
		g2 = new Glider();
		g3 = new Glider();
	}
	
	@Test
	public void testWaitingTime() {
		assertEquals(0, g1.getWaitingTime()); // checking that they start off at 0
		assertEquals(0, g2.getWaitingTime()); // checking that they start off at 0
		assertEquals(0, g3.getWaitingTime()); // checking that they start off at 0
		
		for (int i = 0; i < 10; i++) { // 10 steps for c1
			g1.step();
		}
		
		g2.setWaitingTime(10); // set c2's time to 10
		
		g3.setWaitingTime(5); // set c3's time to 5
		for (int i = 0; i < 5; i++) { // 5 steps for c1
			g3.step();
		}
		
		assertEquals(10, g1.getWaitingTime()); // they should all be at 10 now
		assertEquals(10, g2.getWaitingTime()); // they should all be at 10 now
		assertEquals(10, g3.getWaitingTime()); // they should all be at 10 now

		g1.incrementWaitingTime(); // add 1 more to c1
		assertEquals(11, g1.getWaitingTime()); // should be at 11
		
		g2.incrementWaitingTime(); // add 2 more to c2
		g2.incrementWaitingTime(); // add 2 more to c2
		assertEquals(12, g2.getWaitingTime()); // should be at 12
	}
	
	@Test 
	public void testMaintenanceTime() {
		assertEquals(g1.getMaintenanceTime(), 0); // should be set to 0 by default
		assertEquals(g2.getMaintenanceTime(), 0); // should be set to 0 by default
		assertEquals(g3.getMaintenanceTime(), 0); // should be set to 0 by defaults
		
		g1.setBrokeDown(true);
		g1.step();		
		assertEquals(g1.getMaintenanceTime(), 1); // should be 1, because it is brokedown and had 1 step
		
		g2.step(); g2.step();
		assertEquals(g2.getMaintenanceTime(), 0); // shouldn't be affected by the step() method because it isn't broke down
		
		g3.setBrokeDown(true);
		assertEquals(g3.getMaintenanceTime(), 0); // no steps yet, so still at 0
		for (int i = 0; i < 60; i++) {
			g3.step();
		}
		assertEquals(g3.getMaintenanceTime(), 60); // 60 steps, so the time should be at 60
		for (int i = 0; i < 60; i++) {
			g3.step();
		}
		assertEquals(g3.getMaintenanceTime(), 0); // Aircraft is fixed so reset to 0
	}
	
	@Test
	public void testTakeoffTime() {
		assertEquals(6, g1.getTakeoffTime());
		assertEquals(6, g2.getTakeoffTime());
		assertEquals(6, g3.getTakeoffTime());
	}
	
	@Test
	public void testLandingTime() {
		assertEquals(8, g1.getLandingTime());
		assertEquals(8, g2.getLandingTime());
		assertEquals(8, g3.getLandingTime());
	}
	
	@Test
	public void testFlyingBoolean() {
		assertFalse(g1.isFlying());
		assertFalse(g2.isFlying());
		assertFalse(g3.isFlying());
		
		g1.setIsFlying(true);
		assertTrue(g1.isFlying());
		
		g1.setIsFlying(false);
		assertFalse(g1.isFlying());
	}

	@Test 
	public void testCrashedBoolean() {
		assertFalse(g1.isCrashed()); // gliders can never crash
		assertFalse(g2.isCrashed());
		assertFalse(g3.isCrashed());
		
		g1.setIsFlying(true);
		
		for (int i = 0; i < 10000; i++) {
			g1.step();
		}
		assertFalse(g1.isCrashed()); // gliders can never crash
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(0, g1.compareTo(g1));
		assertEquals(1, g1.compareTo(g2));
		assertEquals(-1, g2.compareTo(g1));
		assertEquals(-1, g3.compareTo(g2));
		assertEquals(1, g2.compareTo(g3));
		assertEquals(1, g1.compareTo(g3));
		assertEquals(-1, g3.compareTo(g1));
	}
	
}