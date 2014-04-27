package aston.group20.model;

/**
 * This concrete subclass defines the characteristics of CommercialAircraft; it defines the landing time,
 * the takeoff time and the length of time it can fly for (it's fuel level) in order to
 * successfully inherit from the PoweredAircraft class. 
 *
 * @author Group_20
 * @version 1.0, March 2014
 *
 */

public class CommercialAircraft extends PoweredAircraft {
	
	// The amount of time it takes to take off
	private static final int COMMERCIAL_TAKEOFF = 4; // measured in half minutes
	// The amount of time it takes to land
	private static final int COMMERCIAL_LANDING = 6; // measured in half minutes

	/**
	 * Creating a new CommercialAircraft, calling the constructor of the PoweredAircraft class
	 * and filling in the values required to instantiate the object.
	 * 
	 * @see PoweredAircraft
	 */
	public CommercialAircraft() {
		super(COMMERCIAL_LANDING, COMMERCIAL_TAKEOFF, generateFuelLevel());
	}
	
	/**
	 * Generates the fuel level for the Commercial Aircrafts
	 * @return the fuel level generated for this particular Commercial Aircraft
	 */
	private static int generateFuelLevel() {
		return (rand.nextInt(40) + 40);
	}

}