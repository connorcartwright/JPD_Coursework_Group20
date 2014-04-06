package aston.group20.model;

/**
* The Glider class that sets the times for both landing and taking off 
* fulfills the inheritence of UnpoweredAircraft 
* @see Unpowered_Aicraft
*
* @author Group_20
* @version 1.0, March 2014
*
*/

public class Glider extends Unpowered_Aircraft {
	
	// The amount of time it takes to take off
	private static final int GLIDER_TAKEOFF = 6; // measured in half minutes
	// The amount of time it takes to land
	private static final int GLIDER_LANDING = 8; // measured in half minutes
	
	public Glider() {
		super(GLIDER_TAKEOFF, GLIDER_LANDING);
	}
	
}
