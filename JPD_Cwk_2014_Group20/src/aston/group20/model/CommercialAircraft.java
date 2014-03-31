package aston.group20.model;

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