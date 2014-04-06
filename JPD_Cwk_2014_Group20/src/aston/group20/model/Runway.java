package aston.group20.model;

/**
* The Runway class is used to decide whether a plane can land/takeoff
* It is called within the airport class
*
* @author Group_20
* @version 1.0, March 2014
*
*/

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
