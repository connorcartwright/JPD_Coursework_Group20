package aston.group20.model;

import java.util.Random;

/**
 * The IAircraft class defines the functionality that all Aircrafts share, including both
 * the currently implemented Powered and Unpowered Aircraft classes.
 * 
 * @author Group_20
 * @version 1.0, March 2014
 * 
*/
public interface IAircraft extends Comparable<IAircraft> {

	/**
	 * The step method defines the Actions that Aircraft take.
	 */
	public abstract void step();
	
	/**
	 * This method returns the Aircrafts fuel level.
	 * @return the Aircrafts fuel level.
	 */
	public abstract int getFuelLevel(); 
	
	/**
	 * This method returns the Aircrafts unique ID.
	 * @return the Aircrafts ID.
	 */
	public abstract int getID();
	
	/**
	 * This method returns the Aircrafts landing time.
	 * @return the Aircrafts landing time.
	 */
	public abstract int getLandingTime(); 

	/**
	 * This method returns the Aircrafts takeoff time.
	 * @return the Aircrafts takeoff time.
	 */
	public abstract int getTakeoffTime(); 
	
	/**
	 * This method returns the amount of time it takes to repair an Aircraft.
	 * @return the amount of time it takes to repair the Aircraft.
	 */
	public abstract int getMaintenanceTime(); 
	
	/**
	 * This method returns the Aircrafts waiting time.
	 * @return the Aircrafts waiting time.
	 */
	public abstract int getWaitingTime(); 
	
	/**
	 * This method increments the Aircrafts maintenance time field.
	 */
	public abstract void incrementMaintenanceTime(); 
	
	/**
	 * This method increments the Aircrafts waiting time field.
	 */
	public abstract void incrementWaitingTime();
	
	/**
	 * This method returns whether or not the Aircraft is broken down.
	 * @return whether or not the Aircraft is broken down.
	 */
	public abstract boolean isBrokeDown(); 
	
	/**
	 * This method either sets the Aircraft to broken down from normal.
	 * or from broken down to normal.
	 * @param brokeDown true if the Aircraft is broken down, false otherwise.
	 */
	public abstract void setBrokeDown(boolean brokeDown); 
	
	/**
	 * This method sets the waiting time of the Aircraft.
	 * @param waitingTime the time to set the Aircrafts waiting time.
	 */
	public abstract void setWaitingTime(int waitingTime);
	
	/**
	 * This method returns whether or not the Aircraft has crashed.
	 * @return whether or not the Aircraft has crashed.
	 */
	public abstract boolean isCrashed();
	
	/**
	 * This method allows the recipient to set whether or not the Aircraft is 
	 * in the air/flying, and thus landing/taking off.
	 * 
	 * @param isFlying whether or not the Aircraft is flying
	 */
	public abstract void setIsFlying(boolean isFlying);
	
	public abstract void setRandom(Random rand);
	
}
