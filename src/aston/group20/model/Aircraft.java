package aston.group20.model;

public interface Aircraft {

	public abstract void step();
	
	public abstract int getTakeoffTime();
	
	public abstract int getLandingTime();
	
	public abstract void setBrokedown(boolean brokeDown);
	
	public abstract boolean isBrokedown();

	public abstract int getMaintenanceTime();

	public abstract int getFuelFlyingTime();
	
}