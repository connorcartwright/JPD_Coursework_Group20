package aston.group20.model;

/**
 * The Runway class is used to accommodate Aircraft taking off and landing;
 * it is simple yet critical to the Simulation. It is used in the Airport class,
 * where the Simulation can manipulate it's methods in order to see whether the
 * Runway is available for a plane to takeoff or land. The functionality is relatively
 * simple, using two Integers and a boolean to set the availability of the Runway.
 *
 * @see Airport
 * @author Group_20
 * @version 1.0, March 2014
 *
 */
public class Runway { // maybe make abstract later, e.g. large Runway
	
	private boolean isAvailable; // whether or not the Runway is available
	private int occupiedTime; // how long the Runway will be unavailable for
	private int clearRunway; // timer to tell us when the Runway is available again

	/**
	 * Creating a new Runway which will set the occupied time and the integer 
	 * clearRunway which counts up to that occupied time as 0. It will also make
	 * sure that the Runway is available upon creation.
	 */
	public Runway() {
		isAvailable = true;
		clearRunway = 0;
		occupiedTime = 0;
	}

	/**
	 * This method returns a boolean which will show whether or not the Runway
	 * is available for use.
	 * 
	 * @return whether or not the Runway is available.
	 */
	public boolean isAvailable() {
		return isAvailable;
	}

	/**
	 * This method will set the availability of the Runway to the parameter value.
	 * 
	 * @param isAvailable whether or not the Runway is available.
	 */
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	/**
	 * This method will set the occupied time of the Runway to the parameter value.
	 * This means that the Runway will be occupied time for the number of steps that the
	 * parameter value is.
	 * 
	 * @param occupiedTime the number of steps the Runway will be occupied for.
	 */
	public void setOccupiedTime(int occupiedTime) {
		this.occupiedTime = occupiedTime;
	}
	
	/**
	 * This method increments the time of clearRunway. Once this integer either meets
	 * or exceeds the occupiedTime integer, they will both be set back to 0 and the 
	 * isAvailable boolean will be set to true, meaning that the Runway is ready for
	 * usage.
	 */
	public void incrementTime() {
		clearRunway++;
		if(clearRunway >= occupiedTime) {
			clearRunway = 0;
			occupiedTime = 0;
			isAvailable = true;
		}
	}

}