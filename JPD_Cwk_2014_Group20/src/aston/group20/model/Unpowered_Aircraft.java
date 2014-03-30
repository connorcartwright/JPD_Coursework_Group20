package aston.group20.model;

public class Unpowered_Aircraft extends Aircraft {
	
	public Unpowered_Aircraft(int landingTime, int takeoffTime) {
		super(landingTime, takeoffTime);
	}

	public void step() {
		incrementWaitingTime();
		if(isBrokeDown() == false) {
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

	public int getFuelLevel() {
		return 0;
	}

}
