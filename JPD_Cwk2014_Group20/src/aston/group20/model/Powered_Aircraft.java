package aston.group20.model;
import java.util.Random;

/**
 * Characterizes the shared behaviours of all aircraft
 * 
 * @author Group_20
 * @version 1.0, March 2014
 *
 */
public abstract class Powered_Aircraft implements Aircraft {

	// The time it takes for the aircraft to take off
	protected int takeoffTime = 0;
	
	// The time that it takes for the aircraft to land
	protected int landingTime = 0;
	
	// The amount of time the aircraft can fly
	protected int fuelFlyingTime = 0;
	
	// The amount of time the aircraft has been waiting
	protected int waitingTime;
	
	// True if the aircraft has broke down
	protected boolean brokeDown = false;
	protected int maintenanceTime;
	
	protected boolean canFly = true;
	
	// Shared Random number generator
	private static final int SEED = 24;
	protected static Random rand = new Random(SEED);
	
	///
	//////// private int priority = null; - can be set later? IMPORTANT
	///
	
	
	/**
	 * Initialising a new aircraft; each aircraft has a certain 
	 * level of fuel, as well as a take off and landing time. 
	 * 
	 * @param takeOffTime the time it will take for the aircraft to take off
	 * @param landingTime the time it will take for the aircraft to land
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
		// if (aircraft is outgoing) {
		// if (1st in queue && takeoffTime < incoming 1st fuelFlyingTime - landingTime) {
		// takeOff();
		
		// if is first in queue
		// if pos in list = 1 && waitingToLand ! true then takeOff();
		// else incrementWaitingTime()
		
		if(brokeDown == true) {
			incrementMainteanceTime();
			
		}
	}
	
	/*
	 * Add one to the waiting time of the aircraft
	 */
	public void incrementWaitingTime() {
		waitingTime++;
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
	
	public void incrementMainteanceTime() {
		maintenanceTime++;
	}
	
	public int getMaintenanceTime() {
		return maintenanceTime;
	}
	
}
