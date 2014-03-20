package aston.group20.model;

import java.util.Random;

public class Unpowered_Aircraft implements Aircraft {
	
	// The time it takes for the aircraft to take off
	protected int takeoffTime = 0;
	
	// The time that it takes for the aircraft to land
	protected int landingTime = 0;
	
	// The amount of time the aircraft has been waiting
	protected int waitingTime;
	
	// True if the aircraft has broke down
	protected boolean brokeDown = false;
	
	// Shared Random number generator
	private static final int SEED = 24;
	protected static Random rand = new Random(SEED);
	
	public Unpowered_Aircraft(int takeOffTime, int landingTime) {
		waitingTime = 0;
	}

}
