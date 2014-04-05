package aston.group20.model;
import java.util.Random;

public abstract class Aircraft implements IAircraft {
	
	// the fields below are measured in half minutes
	
	// The time that it takes for the aircraft to land
	private int landingTime;
	// The time it takes for the aircraft to take off
	private int takeoffTime;
	// The amount of time the aircraft has been waiting
	private int waitingTime;
	// True if the aircraft has broken down
	protected boolean brokeDown;
	// The amount of time the aircraft has been maintained once it
	// reaches 120 the aircraft is fixed, and the value reset to 0
	protected int maintenanceTime;
	private static int lastID = 0;
	private int ID;
	
	// Shared Random number generator
	private static final int SEED = 22;
	protected static Random rand = new Random(SEED);

	public Aircraft(int landingTime, int takeoffTime) {
		this.landingTime = landingTime;
		this.takeoffTime = takeoffTime;
		brokeDown = false;
		maintenanceTime = 0;
		waitingTime = 0;
		this.ID = nextID();
	}
	
	public abstract void step();
	
	public int getID() {
		return ID;
	}
	
	public int nextID() {
		return ++lastID;
	}
	
	public int getMaintenanceTime() {
		return maintenanceTime;
	}
	
	public int getLandingTime() {
		return landingTime;
	}
	
	public int getTakeoffTime() {
		return takeoffTime;
	}
	
	public int getWaitingTime() {
		return waitingTime;
	}
	
	public void incrementMaintenanceTime() {
		maintenanceTime++;
	}
	
	public void incrementWaitingTime() {
		waitingTime++;
	}

	public boolean isBrokeDown() {
		return brokeDown;
	}
	
	public void setBrokeDown(boolean brokeDown) {
		this.brokeDown = brokeDown;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}
	
	public int compareTo(IAircraft a) {
		if (a.getID() == ID) {
			return 0;
		}
		else if (a.getID() < ID) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
}