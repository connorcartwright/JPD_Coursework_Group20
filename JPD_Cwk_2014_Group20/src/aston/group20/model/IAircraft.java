package aston.group20.model;

/**
* The IAircraft class defines the funcationaly of all the aircrafts
* such as the take off and landing time as well as whether or not the aircaraft has broken down
*
* @author Group_20
* @version 1.0, March 2014
*
*/

public interface IAircraft extends Comparable<IAircraft> {

	public abstract void step();
	
	public abstract int getFuelLevel(); 
	
	public abstract int getID();
	
	public abstract int getLandingTime(); 

	public abstract int getTakeoffTime(); 
	
	public abstract int getMaintenanceTime(); 
	
	public abstract int getWaitingTime(); 
	
	public abstract void incrementMaintenanceTime(); 
	
	public abstract void incrementWaitingTime();
	
	public abstract boolean isBrokeDown(); 
	
	public abstract void setBrokeDown(boolean brokeDown); 
	
	public abstract void setWaitingTime(int waitingTime);

}
