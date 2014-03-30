package aston.group20.test;
import aston.group20.model.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestAircraft {
	
	CommercialAircraft commercial;
	Glider glider;
	LightAircraft light;

	@Before
	public void setUp() throws Exception {
		commercial = new CommercialAircraft();
		glider = new Glider();
		light = new LightAircraft();
	}

	
	@Test
	public void testWaitingTime() {
		assertEquals(0, commercial.getWaitingTime());
		assertEquals(0, light.getWaitingTime());
		assertEquals(0, glider.getWaitingTime());
		for (int i = 0; i < 10; i++) {
			commercial.step();
		}
		assertEquals(10, commercial.getWaitingTime());
		commercial.incrementWaitingTime();
		assertEquals(11, commercial.getWaitingTime());
	}
	
	@Test
	public void testTakeoffTime() {
		assertEquals(4, commercial.getTakeoffTime());
		assertEquals(6, glider.getTakeoffTime());
		assertEquals(4, light.getTakeoffTime());
	}

}
