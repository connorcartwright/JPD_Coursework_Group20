package aston.group20.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import aston.group20.model.Counter; 

public class TestCounter {

	Counter counter;

	@Before
	public void setUp() throws Exception {
		counter = new Counter();
	}
	
	@Test
	public void testIncrements() {
		for (int i = 0; i < 10; i++) {
			counter.incrementCrashes();
			counter.incrementLandings();
			counter.incrementTakeoffs();
		}
		
		assertEquals(10, counter.getCrashes());
		assertEquals(10, counter.getLandings());
		assertEquals(10, counter.getTakeoffs());
		
		counter.incrementLandings();
		
		assertEquals(11, counter.getLandings());
	}
	
	@Test
	public void testMutators() {
		counter.setFlying(55);
		counter.setGrounded(60);
		
		assertEquals(55, counter.getFlying());
		assertEquals(60, counter.getGrounded());
		
	}

	public void testClear() {
		counter.setFlying(5);
		counter.setGrounded(15);
		counter.incrementCrashes(); counter.incrementCrashes();
		counter.incrementLandings();
		counter.incrementTakeoffs();
		
		counter.clear();
		
		assertEquals(0, counter.getCrashes());
		assertEquals(0, counter.getFlying());
		assertEquals(0, counter.getGrounded());
		assertEquals(0, counter.getLandings());
		assertEquals(0, counter.getTakeoffs());
	}
	
}