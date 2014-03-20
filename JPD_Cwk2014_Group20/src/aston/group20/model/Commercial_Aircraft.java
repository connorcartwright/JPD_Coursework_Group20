package aston.group20.model;

public class Commercial_Aircraft extends Powered_Aircraft {
	
	// The amount of time it takes to take off
	private static final int COMMERCIAL_TAKEOFF = 4; // measured in half minutes
	// The amount of time it takes to land
	private static final int COMMERCIAL_LANDING = 6; // measured in half minutes
	// The amount of time the aircraft can fly
	private static final int COMMERCIAL_FUEL_FLYING_TIME = (rand.nextInt(40) + 40); // measured in half minutes
	

	public Commercial_Aircraft() {
		super(COMMERCIAL_TAKEOFF, COMMERCIAL_LANDING, COMMERCIAL_FUEL_FLYING_TIME);
		
	}
	
	
	public String toString() {
		return (super.toString());
	}


}
