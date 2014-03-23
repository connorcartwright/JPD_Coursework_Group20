package aston.group20.model;

public class Runway { // maybe make abstract later
	
	private boolean isAvailable;
	private int occupiedTime;
	private int clearRunway;

	public Runway() {
		isAvailable = true;
		clearRunway = 0;
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
		clearRunway++;
		if(clearRunway >= occupiedTime) {
			clearRunway = 0;
			occupiedTime = 0;
			isAvailable = true;
		}
	}

}