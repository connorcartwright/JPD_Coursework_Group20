package aston.group20.model;
import java.util.Random;

/**
 * The Hangar class is used to create aircrafts
 * as well as assigning the probabilities of each aircraft being created
 *
 * @author Group_20
 * @version 1.0, March 2014
 */

public class Hangar {

	private double COMMERCIAL_CREATION_PROBABILITY = 0.1;
	private double LIGHT_CREATION_PROBABILITY = 0.005;
	private double GLIDER_CREATION_PROBABILITY = 0.002;
	
	public IAircraft generateAircraft(Random rand) {
		IAircraft aircraft;
		if (rand.nextDouble() <= GLIDER_CREATION_PROBABILITY) { 
			aircraft = (IAircraft) new Glider(); // setting variable aircraft to be a glider
		}
		else if (rand.nextDouble() <= LIGHT_CREATION_PROBABILITY + GLIDER_CREATION_PROBABILITY) {
			aircraft = (IAircraft) new LightAircraft(); // setting variable aircraft to be a light aircraft
		}
		else if (rand.nextDouble() <= COMMERCIAL_CREATION_PROBABILITY) {
			aircraft = (IAircraft) new CommercialAircraft(); // setting variable aircraft to be a commercial aircraft
		}
		else {
			aircraft = null; // if the double didn't meet any of the rules, the aircraft is null
		}
		return aircraft; // return the aircraft
	}
	
	public void setProbabilities(double commercial, double glider, double light) {
		COMMERCIAL_CREATION_PROBABILITY = commercial;
		GLIDER_CREATION_PROBABILITY = glider;
		LIGHT_CREATION_PROBABILITY = light;
	}	
}
