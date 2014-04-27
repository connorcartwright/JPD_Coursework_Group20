package aston.group20.model;

/**
 * Characterises the shared behaviours of all Unpowered Aircraft; this class, alongside the
 * Powered Aircraft class, fulfils the remaining Inheritance from the IAircraft interface.
 * 
 * @see Aircraft
 * 
 * @author Group_20
 * @version 1.0, March 2014
 * 
 */

public class UnpoweredAircraft extends Aircraft {

	public UnpoweredAircraft(int landingTime, int takeoffTime) {
		super(landingTime, takeoffTime);
	}

	public void step() {
		super.step();
	}

	public int getFuelLevel() {
		return Integer.MAX_VALUE;
	}

}
