package aston.group20.model;

/**
 * Characterises the shared behaviours of all Powered Aircraft; this class, alongside
 * Unpowered Aircraft, fulfils the remaining inheritance from the IAircraft interface.
 * @see Aircraft
 * 
 * @author Group_20
 * @version 1.0, March 2014
 *
 */
public class PoweredAircraft extends Aircraft {
	
	// The amount of time the aircraft can fly
	private int fuelLevel;
	
	/**
	 * Initialising a new aircraft; each aircraft has a certain 
	 * level of fuel, as well as a take off and landing time. 
	 * 
	 * @param takeoffTime the time it will take for the aircraft to take off
	 * @param landingTime the time it will take for the aircraft to land
	 * @param fuelLevel the time the aircraft can fly for
	 */
	public PoweredAircraft(int landingTime, int takeoffTime, int fuelLevel) {
		super(landingTime, takeoffTime);
		this.fuelLevel = fuelLevel;
	}
	
	/*
	 * The generic action; it is called every step of the 
	 * simulation for each and every aircraft.
	 */
	public void step() {
		super.step();
		fuelLevel--;
		if (isFlying && (fuelLevel - landingTime) < 0) { // if the plane doesn't have enough fuel to land
			crashed = true; // then it is crashed/going to crash
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