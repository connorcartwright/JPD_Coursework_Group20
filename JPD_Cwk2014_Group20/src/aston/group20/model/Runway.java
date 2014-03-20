package aston.group20.model;

public abstract class Runway {
	
	private boolean isAvailable;
	private int occupiedTime;
	private int clear;

	public Runway() {
		isAvailable = true;
		clear = 0;
		occupiedTime = 0;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public void setOccupiedTime(int occupiedTime) {
		this.occupiedTime = occupiedTime;
	}
	
	public void incrementTime() {
		clear++;
		if(clear >= occupiedTime) {
			clear = 0;
			occupiedTime = 0;
			isAvailable = true;
		}
	}

}
