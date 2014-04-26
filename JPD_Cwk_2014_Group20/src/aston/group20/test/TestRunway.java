package aston.group20.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import aston.group20.model.Runway;

public class TestRunway {

	Runway runway;

	@Before
	public void setUp() throws Exception {
		runway = new Runway();
	}
	
	@Test
	public void testAvailability() {
		assertTrue(runway.isAvailable());
		runway.setAvailable(false);	
		assertFalse(runway.isAvailable());
		runway.setAvailable(true);
		assertTrue(runway.isAvailable());
		runway.setAvailable(true);
		assertTrue(runway.isAvailable());
	}
	
	@Test
	public void testOccupiedTime() {
		assertTrue(runway.isAvailable());
		runway.setAvailable(false);
		assertFalse(runway.isAvailable());
		
		runway.setOccupiedTime(5);
		
		for (int i = 0; i < 5; i++) {
			runway.incrementTime();
		}
		assertTrue(runway.isAvailable());	
	}
	
}