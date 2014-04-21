package aston.group20.model;
import java.util.Random;

/**
 * The Aircraft class implements the IAircraft interface and contains the 
 * fields shared by all Aircraft and the majority of the method implementation
 * required by the IAircraft interface, however it still allows for expansion and 
 * currently has two the two separate types of Aircraft below it (Powered & Unpowered)
 * as well as the concrete subclasses (Commercial, Glider, Light).
 * 
 * @see IAircraft
 * @author Group_20
 * @version 1.0, March 2014
 *  
 */
public abstract class Aircraft implements IAircraft {
	
	// the fields below are measured in half minutes
	
	// The time that it takes for the aircraft to land.
	protected int landingTime;
	// The time it takes for the aircraft to take off.
	protected int takeoffTime;
	// The amount of time the aircraft has been waiting.
	private int waitingTime;
	// True if the aircraft has broken down.
	protected boolean brokeDown;
	// The amount of time the aircraft has been maintained once it
	// reaches 120 the aircraft is fixed, and the value reset to 0.
	protected int maintenanceTime;
	private static int lastID = 0;
	private int ID;
	
	protected boolean isFlying;
	protected boolean crashed;
	
	// Shared Random number generator
	private static final int SEED = 42;
	protected static Random rand = new Random(SEED);

	/**
	 * Creating a new Aircraft and setting its default values.
	 * @param landingTime the time the Aircraft takes to land.
	 * @param takeoffTime the time the Aircraft takes to takeoff.
	 */
	public Aircraft(int landingTime, int takeoffTime) {
		this.landingTime = landingTime;
		this.takeoffTime = takeoffTime;
		brokeDown = false;
		crashed = false;
		maintenanceTime = 0;
		waitingTime = 0;
		this.ID = nextID();
	}
	
	/**
	 * The action that all Aircraft take at all turns.
	 */
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
	
	public void setRandom(Random rand) {
		this.rand = rand;
	}
	
	public boolean isCrashed() {
		return crashed;
	}
	
	public void setIsFlying(boolean isFlying) {
		this.isFlying = isFlying;
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