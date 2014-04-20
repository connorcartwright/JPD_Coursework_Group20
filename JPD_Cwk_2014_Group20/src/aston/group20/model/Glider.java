package aston.group20.model;

/**
 * This class defines the characteristics of Gliders, including both the landing time and 
 * the takeoff It extends the abstract class UnpoweredAircraft and is a concrete subclass.
 * 
 * @see UnpoweredAircraft
 *
 * @author Group_20
 * @version 1.0, March 2014
 *
 */


public class Glider extends UnpoweredAircraft {
	
	// The amount of time it takes to take off
	private static final int GLIDER_TAKEOFF = 6; // measured in half minutes
	// The amount of time it takes to land
	private static final int GLIDER_LANDING = 8; // measured in half minutes
	
	public Glider() {
		super(GLIDER_TAKEOFF, GLIDER_LANDING);
	}
	
}
