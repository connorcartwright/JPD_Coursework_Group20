package aston.group20.model;

/**
 * Characterizes the shared behaviours of all Unpowered aircraft fulfills the
 * inheritence of the Aircraft class
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
		incrementWaitingTime();
		if (isBrokeDown()) {
			incrementMaintenanceTime();
			if (maintenanceTime >= 120) {
				brokeDown = false;
				maintenanceTime = 0;
			}
		} 
		else if (!isFlying && rand.nextDouble() < 0.0001) {
			brokeDown = true;
		}
	}

	public int getFuelLevel() {
		return Integer.MAX_VALUE;
	}

}
