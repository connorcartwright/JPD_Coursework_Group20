package aston.group20.model;

/**
 * This concrete subclass defines the characteristics of Gliders; it defines the landing time and
 * the takeoff time in order to successfully inherit from the UnoweredAircraft class. 
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
	
	/**
	 * Creating a new Glider, calling the constructor of the UnPoweredAircraft class
	 * and filling in the values required to instantiate the object.
	 * 
	 * @see UnpoweredAircraft
	 */
	public Glider() {
		super(GLIDER_LANDING, GLIDER_TAKEOFF);
	}
	
}