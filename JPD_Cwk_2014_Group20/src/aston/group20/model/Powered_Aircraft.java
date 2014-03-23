package aston.group20.model;
import java.util.Random;

/**
 * Characterizes the shared behaviours of all powered aircraft
 * 
 * @author Group_20
 * @version 1.0, March 2014
 *
 */
public abstract class Powered_Aircraft implements Aircraft {
	
	// the four fields below are measured in half minutes
	
	// The time it takes for the aircraft to take off
	protected int takeoffTime = 0;
	// The time that it takes for the aircraft to land
	protected int landingTime = 0;
	// The amount of time the aircraft can fly
	protected int fuelFlyingTime = 0;
	// The amount of time the aircraft has been waiting
	protected int waitingTime;
	
	// True if the aircraft has broken down
	protected boolean brokeDown = false;
	// The amount of time the aircraft has been maintained
	// once it reaches 120 the aircraft is fixed, and the value reset to 0
	protected int maintenanceTime;
	
	// Shared Random number generator
	private static final int SEED = 24;
	protected static Random rand = new Random(SEED);
	
	protected boolean inTheAir;
	
	
	/**
	 * Initialising a new aircraft; each aircraft has a certain 
	 * level of fuel, as well as a take off and landing time. 
	 * 
	 * @param takeOffTime the time it will take for the aircraft to take off
	 * @param landingTime the time it will take for the aircraft to land
	 * @param fuelFlyingTime the time the aircraft can fly for
	 */
	public Powered_Aircraft(int takeoffTime, int landingTime, int fuelFlyingTime) {
		this.takeoffTime = takeoffTime;
		this.landingTime = landingTime;
		this.fuelFlyingTime = fuelFlyingTime;
		waitingTime = 0;
		maintenanceTime = 0;
	}
	
	/*
	 * The generic action; it is called every step of the 
	 * simulation for each and every aircraft.
	 */
	public void step() {
		incrementWaitingTime();
		decrementFuelFlyingTime();
		if(isBrokedown() == false && fuelFlyingTime > 0) {
			if (rand.nextDouble() < 0.0001) {
				brokeDown = true;
			}
		}
		else {
			incrementMaintenanceTime();
			if(maintenanceTime >= 120) {
				brokeDown = false;
				maintenanceTime = 0;
			}
		}
	}
	
	/*
	 * Add one to the waiting time of the aircraft
	 */
	public void incrementWaitingTime() {
		waitingTime++;
	}
	
	public void decrementFuelFlyingTime() {
		fuelFlyingTime--;
	}

	/**
	 * Returns the take off time of the aircraft
	 * @return the take off time of the aircraft
	 */
	public int getTakeoffTime() {
		return takeoffTime;
	}
	
	/**
	 * Returns the landing time of the aircraft
	 * @return the landing time of the aircraft
	 */
	public int getLandingTime() {
		return landingTime;
	}

	/**
	 * Returns the fuel level of the aircraft
	 * @return the fuel level of the aircraft
	 */
	public int getFuelFlyingTime() {
		return fuelFlyingTime;
	}
	
	public void setBrokedown(boolean brokeDown) {
		this.brokeDown = brokeDown;
		maintenanceTime = 0;
	}
	
	/**
	 * Returns whether or not the aircraft is broke down
	 * @return whether or not the aircraft is broke down
	 */
	public boolean isBrokedown() {
		return brokeDown;
	}
	
	public void incrementMaintenanceTime() {
		maintenanceTime++;
	}
	
	public int getMaintenanceTime() {
		return maintenanceTime;
	}
	
	@Override
	public int getWaitingTime() {
		return waitingTime;
	}

	@Override
	public int compareTo(Aircraft a) {
		if (this == a) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
