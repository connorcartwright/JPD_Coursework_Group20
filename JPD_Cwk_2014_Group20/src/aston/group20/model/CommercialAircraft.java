package aston.group20.model;

/**
* The Commercial Aircraft class that sets the times for landing, taking off and flying.
* fulfills the inheritence of the PoweredAircraft
* @see PoweredAicraft
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
	// The amount of time the aircraft can fly
	private static final int COMMERCIAL_FUEL_FLYING_TIME = (rand.nextInt(40) + 40); // measured in half minutes
	
	public CommercialAircraft() {
		super(COMMERCIAL_TAKEOFF, COMMERCIAL_LANDING, COMMERCIAL_FUEL_FLYING_TIME);
	}

}
