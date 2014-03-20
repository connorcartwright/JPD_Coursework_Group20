package aston.group20.model;

public class Light_Aircraft extends Aircraft {
	// The amount of time it takes to take off
	private static final int LIGHT_TAKEOFF = 4; // measured in half minutes
	// The amount of time it takes to land
	private static final int LIGHT_LANDING = 6; // measured in half minutes
	// The amount of time the aircraft can fly
	private static final int LIGHT_FUEL_FLYING_TIME = (rand.nextInt(20) + 20); // measured in half minutes

	public Light_Aircraft() {
		super(LIGHT_TAKEOFF, LIGHT_LANDING, LIGHT_FUEL_FLYING_TIME);
	}

}
