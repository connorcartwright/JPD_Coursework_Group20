package aston.group20.model;

/**
 * This class defines the characteristics of Light Aircraft, including the landing time,
 * the takeoff time and the length of time that it can fly for/it's fuel level. It extends
 * the abstract class PoweredAircraft and is a concrete subclass.
 * 
 * @see PoweredAircraft
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
	// The amount of time the aircraft can fly
	private static final int LIGHT_FUEL_FLYING_TIME = (rand.nextInt(20) + 20); // measured in half minutes

	public LightAircraft() {
		super(LIGHT_TAKEOFF, LIGHT_LANDING, LIGHT_FUEL_FLYING_TIME);
	}

}
