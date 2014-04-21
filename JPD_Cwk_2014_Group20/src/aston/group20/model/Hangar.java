package aston.group20.model;
import java.util.Random;

/**
 * The Hangar class is used to control the creation of Aircraft; it is created in the Airport class
 * and has it's methods called from the Simulator in order to potentially generate Aircraft at every step. 
 * The variables are not static/final due to the fact that they are required to be changeable, mostly 
 * through the user interaction provided with the GUI.
 * 
 * @see Simulator#generateAircraft()
 *
 * @author Group_20
 * @version 1.0, March 2014
 */
public class Hangar {

	private double COMMERCIAL_CREATION_PROBABILITY = 0.1;
	private double LIGHT_CREATION_PROBABILITY = 0.005;
	private double GLIDER_CREATION_PROBABILITY = 0.002;
	
	/**
	 * Creates a new <code>Hangar</code>.
	 */
	Hangar() {
	}
	
	/** 
	 * This method controls the creation of Aircraft; it is called from the Simulator class; it takes
	 * a Random number generator in order to respect the seed used in the Simulator class (so that the
	 * Simulation results are reproducible). The method has a chance of returning a specific type of Aircraft,
	 * (Commercial, Glider, Light) based on the probability fields; in the chance that the probabilities are
	 * too low, a null Aircraft is returned which is dealt with in the Simulator class.
	 * 
	 * @param rand the random number generator from the Simulator class.
	 * @return an Aircraft of any of the types supported, or null.
	 */
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

	/**
	 * This method is called from the Simulator class and supports the User Interaction that the GUI
	 * provides; it allows the user to change the results of the Simulation, due to how often and what
	 * types of Aircraft are likely to be created.
	 * 
	 * @param commercial the chance of a commercial aircraft being generated.
	 * @param glider the chance of a glider being generated.
	 * @param light the chance of a light aircraft being generated.
	 */
	public void setProbabilities(double commercial, double glider, double light) {
		COMMERCIAL_CREATION_PROBABILITY = commercial;
		GLIDER_CREATION_PROBABILITY = glider;
		LIGHT_CREATION_PROBABILITY = light;
	}	
	
}