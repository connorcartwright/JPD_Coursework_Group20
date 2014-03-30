package aston.group20.model;

/**
 * Characterizes the shared behaviours of all powered aircraft
 * 
 * @author Group_20
 * @version 1.0, March 2014
 *
 */
public abstract class PoweredAircraft extends Aircraft {
	
	// The amount of time the aircraft can fly
	protected int fuelLevel;
	
	/**
	 * Initialising a new aircraft; each aircraft has a certain 
	 * level of fuel, as well as a take off and landing time. 
	 * 
	 * @param takeOffTime the time it will take for the aircraft to take off
	 * @param landingTime the time it will take for the aircraft to land
	 * @param fuelFlyingTime the time the aircraft can fly for
	 */
	public PoweredAircraft(int landingTime, int takeoffTime, int fuelFlyingTime) {
		super(landingTime, takeoffTime);
		this.fuelLevel = fuelFlyingTime;
	}
	
	/*
	 * The generic action; it is called every step of the 
	 * simulation for each and every aircraft.
	 */
	public void step() {
		incrementWaitingTime();
		fuelLevel--;
		if(isBrokeDown() == false && fuelLevel > 0) {
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


	/**
	 * Returns the fuel level of the aircraft
	 * @return the fuel level of the aircraft
	 */
	public int getFuelLevel() {
		return fuelLevel;
	}
	
}