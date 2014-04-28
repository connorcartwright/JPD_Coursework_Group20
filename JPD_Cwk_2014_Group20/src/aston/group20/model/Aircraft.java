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
	
	// True if the aircraft is in the air
	protected boolean isFlying;
	// True if the aircraft has crashed
	protected boolean crashed;
	
	// Shared Random number generator
	private static final int SEED = 17;
	private static final Random rand = new Random(SEED);

	/**
	 * Creating a new <code>Aircraft</code> which takes two parameters, the landing time
	 * and the takeoff time of the aircraft, both of which are measured in half minutes. 
	 * The constructor also sets the default values upon creation of an Aircraft,
	 * such as the fact that it is not broken down, etc.
	 * 
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
	 * This method updates the aircrafts's state; it will be fulfilled by the abstract classes
	 * PoweredAircraft and UnpoweredAircraft, which will define how the Aircrafts should act.
	 */
	public void step() {
		incrementWaitingTime();
		
		if (isBrokeDown() == false) {
			if (!isFlying && rand.nextDouble() < 0.0001) {
				brokeDown = true;
			}
		}
		else if (isBrokeDown()) {
			incrementMaintenanceTime();
			if(maintenanceTime >= 120) {
				brokeDown = false;
				maintenanceTime = 0;
				}
		}
	}
	
	/**
	 * This method returns the Aircrafts unique identifier.
	 * @return the Aircrafts ID.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * This method returns the next identifier to be used by an Aircraft.
	 * @return the next ID to be used.
	 */
	public int nextID() {
		return ++lastID;
	}
	
	/**
	 * This method returns the current maintenance time of the Aircraft.
	 * @return the maintenance time of the Aircraft.
	 */
	public int getMaintenanceTime() {
		return maintenanceTime;
	}
	
	/**
	 * This method returns the landing time of the Aircraft.
	 * @return the landing time of the Aircraft.
	 */
	public int getLandingTime() {
		return landingTime;
	}
	
	/**
	 * This method returns the takeoff time of the Aircraft.
	 * @return the takeoff time of the Aircraft.
	 */
	public int getTakeoffTime() {
		return takeoffTime;
	}
	
	/**
	 * This method returns the waiting time of the Aircraft.
	 * @return the waiting time of the Aircraft.
	 */
	public int getWaitingTime() {
		return waitingTime;
	}
	
	/** 
	 * This method increments the maintenance time of the Aircraft.
	 */
	public void incrementMaintenanceTime() {
		maintenanceTime++;
	}
	
	/**
	 * This method increments the waiting time of the Aircraft.
	 */
	public void incrementWaitingTime() {
		waitingTime++;
	}

	/**
	 * This method returns a boolean which is true of the Aircraft
	 * has broken down, and false otherwise.
	 * @return whether or not the Aircraft has broken down.
	 */
	public boolean isBrokeDown() {
		return brokeDown;
	}
	
	/**
	 * This method sets the state of the boolean which tells us whether
	 * or not the Aircraft has broken down - if the parameter is true, than the 
	 * Aircraft's state will be seen as broken down.
	 * 
	 * @param brokeDown whether or not the Aircraft is broken down.
	 */
	public void setBrokeDown(boolean brokeDown) {
		this.brokeDown = brokeDown;
	}

	/**
	 * This method sets the waiting time of the Aircraft.
	 * 
	 * @param waitingTime the waiting time of the Aircraft.
	 */
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}
	
	/**
	 * This method returns a boolean informing us as to whether or not the Aircraft
	 * has crashed; if true the Aircraft has crashed.
	 * @return whether or not the Aircraft has crashed.
	 */
	public boolean isCrashed() {
		return crashed;
	}
	
	/**
	 * This method sets the state of the boolean which tells us whether or not the plane 
	 * is in the air/flying. If the parameter is true, then the Aircraft will be seen to be
	 * in the air.
	 */
	public void setIsFlying(boolean isFlying) {
		this.isFlying = isFlying;
	}
	
	/**
	 * This method returns whether or not the plane is in the air/flying.
	 * @return whether or not the plane is in the air/flying.
	 */
	public boolean isFlying() {
		return isFlying;
	}
	
	/**
	 * This method compares this Aircraft with the parameter Aircraft by identifier; it can give 
	 * information as to whether the parameter Aircraft is the same Aircraft as this Aircraft,
	 * was created before this Aircraft, or was created after this Aircraft.
	 * 
	 * @param a the Aircraft in question.
	 * @return a negative integer, zero, or a positive integer if this Aircraft is less than, equal to, or greater than the parameter Aircraft.
	 */
	public int compareTo(IAircraft a) {
		return Integer.compare(a.getID(), ID);
	}
	
}