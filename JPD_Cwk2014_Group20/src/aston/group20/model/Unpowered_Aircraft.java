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
	protected int maintenanceTime;
	
	// Shared Random number generator
	private static final int SEED = 24;
	protected static Random rand = new Random(SEED);
	
	public Unpowered_Aircraft(int takeoffTime, int landingTime) {
		this.takeoffTime = takeoffTime;
		this.landingTime = landingTime;
		waitingTime = 0;
		maintenanceTime = 0;
	}

	@Override
	public void step() {
		if(isBrokedown()) {
			
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTakeoffTime() {
		// TODO Auto-generated method stub
		return takeoffTime;
	}

	@Override
	public int getLandingTime() {
		// TODO Auto-generated method stub
		return landingTime;
	}

	@Override
	public void setBrokedown(boolean brokeDown) {
		this.brokeDown = brokeDown;
		
	}

	@Override
	public boolean isBrokedown() {
		return brokeDown;
	}

	@Override
	public int getMaintenanceTime() {
		return maintenanceTime;
	}

	@Override
	public int getFuelFlyingTime() {
		return 0;
	}

}
