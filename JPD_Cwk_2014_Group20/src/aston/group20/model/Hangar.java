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
	
	private Random gen = new Random(17);
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
	public IAircraft generateAircraft() {
		IAircraft aircraft;
		if (gen.nextDouble() <= GLIDER_CREATION_PROBABILITY) { 
			aircraft = generateGlider(); // setting variable aircraft to be a glider
		}
		else if (gen.nextDouble() <= LIGHT_CREATION_PROBABILITY + GLIDER_CREATION_PROBABILITY) {
			aircraft = generateLightAircraft(); // setting variable aircraft to be a light aircraft
		}
		else if (gen.nextDouble() <= COMMERCIAL_CREATION_PROBABILITY) {
			aircraft = generateCommercialAircraft(); // setting variable aircraft to be a commercial aircraft
		}
		else {
			aircraft = null; // if the double didn't meet any of the rules, the aircraft is null
		}
		
		if(aircraft != null && gen.nextInt(100) < 50 && !(aircraft instanceof Glider)) {
			aircraft.setIsFlying(true);
		}
		return aircraft; // return the aircraft
	}
	
	public IAircraft generateCommercialAircraft() {
		System.out.println(gen.nextInt(40) + 40);
		IAircraft aircraft = (IAircraft) new CommercialAircraft(gen.nextInt(40) + 40);
		return aircraft;
	}
	
	public IAircraft generateGlider() {
		IAircraft aircraft = (IAircraft) new Glider();
		return aircraft;
	}
	
	public IAircraft generateLightAircraft() {
		IAircraft aircraft = (IAircraft) new LightAircraft(gen.nextInt(20) + 20);
		return aircraft;
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
	
	public void setSeed(int seed) {
		gen.setSeed(seed);
	}
	
}