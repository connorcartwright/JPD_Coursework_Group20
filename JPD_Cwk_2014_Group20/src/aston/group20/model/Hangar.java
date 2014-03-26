package aston.group20.model;
import java.util.Random;

public class Hangar {

	private static final double COMMERCIAL_CREATION_PROBABILITY = 0.1;
	private static final double LIGHT_CREATION_PROBABILITY = 0.005;
	private static final double GLIDER_CREATION_PROBABILITY = 0.002;
	
	public Hangar() {
		
	}
	
	public Aircraft generateAircraft(Random rand) {
		Aircraft aircraft;
		if (rand.nextDouble() <= GLIDER_CREATION_PROBABILITY) { 
			aircraft = new Glider();                           // setting variable aircraft to be a glider
		}
		else if (rand.nextDouble() <= LIGHT_CREATION_PROBABILITY + GLIDER_CREATION_PROBABILITY) {
			aircraft = new Light_Aircraft();                // setting variable aircraft to be a light aircraft
		}
		else if (rand.nextDouble() <= COMMERCIAL_CREATION_PROBABILITY) {
			aircraft = new Commercial_Aircraft();        // setting variable aircraft to be a commercial aircraft
		}
		else {
			aircraft = null; // if the double didn't meet any of the rules, the aircraft is null
		}
		return aircraft; // return the aircraft
}
}
