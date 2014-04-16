package aston.group20.model;

/**
* The Counter class is used to store and display information 
* for the final report when the GUI is run
*
* @author Group_20
* @version 1.0, March 2014
*
*/

public class Counter {

	private int totalPlanes;
	private int waitingTime;
	private int breakdowns;
	private int crashes;
	private int takeoffs;
	private int landings;
	private int grounded;
	private int flying;
	
	public Counter() {
		clear();
	}
	
	public void incrementTotalPlanes() {
		totalPlanes++;
	}
	
	public void incrementWaitingTime(int increment) {
		waitingTime += increment;
	}
	
	public void incrementBreakdowns() {
		breakdowns++;
	}
	
	public void incrementCrashes() {
		crashes++;
	}
	
	public int getCrashes() {
		return crashes;
	}
	
	public void incrementTakeoffs() {
		takeoffs++;
	}
	
	public int getTakeoffs() {
		return takeoffs;
	}
	
	public void incrementLandings() {
		landings++;
	}
	
	public int getLandings() {
		return landings;
	}
	
	public void setFlying(int flying) {
		this.flying = flying;
	}
	
	public int getFlying() {
		return flying;
	}
	
	public void setGrounded(int grounded) {
		this.grounded = grounded;
	}
	
	public int getGrounded() {
		return grounded;
	}
	
	public void clear() {
		totalPlanes = 0;
		waitingTime = 0;
		takeoffs = 0;
		landings = 0;
		breakdowns = 0;
		crashes = 0;
		grounded = 0;
		flying = 0;
	}
	
	public String toString() {
		return (
				"Total Planes: " + totalPlanes + "\n" +
				"Total Takeoffs: " + takeoffs + "\n" +
				"Total Landings: " + landings + "\n" +
				"Average Waiting Time: " + waitingTime/totalPlanes + "\n" +
				"Total Crashes: " + crashes + "\n" +
				"Total Breakdowns: " + breakdowns + "\n" +
				"Planes still grounded: " + grounded + "\n" + 
				"Planes still flying: " + flying + "\n"
				);
	}
	
	public String longReport() {
		return ( 
	    "       |       Grounded: " + grounded + 
		"       |       Takeoffs: " + takeoffs +
		"       |       Flying: " + flying +
		"       |       Landings: " + landings +
		"       |       Crashes: " + crashes +
		"       |       Breakdowns: " + breakdowns
		);
	}

}
