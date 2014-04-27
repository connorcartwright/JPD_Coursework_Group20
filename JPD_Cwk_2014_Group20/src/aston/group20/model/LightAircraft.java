package aston.group20.model;

/**
 * This concrete subclass defines the characteristics of LightAircraft; it defines the landing time,
 * the takeoff time and the length of time it can fly for (it's fuel level) in order to
 * successfully inherit from the PoweredAircraft class. 
 *
 * @author Group_20
 * @version 1.0, March 2014
 *
 */

public class LightAircraft extends PoweredAircraft {
	
	// The amount of time it takes to take off
	private static final int LIGHT_TAKEOFF = 4; // measured in half minutes
	// The amount of time it takes to land
	private static final int LIGHT_LANDING = 6; // measured in half minutes
	
	/**
	 * Creating a new LightAircraft, calling the constructor of the PoweredAircraft class
	 * and filling in the values required to instantiate the object.
	 * 
	 * @see PoweredAircraft
	 */
	public LightAircraft() {
		super(LIGHT_LANDING, LIGHT_TAKEOFF, generateFuelLevel());
	}
	
	/**
	 * Generates the fuel level for the Light Aircrafts
	 * @return the fuel level generated for this particular Light Aircraft
	 */
	private static int generateFuelLevel() {
		return (rand.nextInt(20) + 20);
	}

}