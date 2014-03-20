package aston.group20.model;

public class Glider extends Unpowered_Aircraft {
	
	// The amount of time it takes to take off
	private static final int GLIDER_TAKEOFF = 6; // measured in half minutes
	// The amount of time it takes to land
	private static final int GLIDER_LANDING = 8; // measured in half minutes
	
	public Glider() {
		super(GLIDER_TAKEOFF, GLIDER_LANDING);
	}
	

}
