package aston.group20.model;

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