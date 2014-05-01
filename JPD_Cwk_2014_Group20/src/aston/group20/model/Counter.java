package aston.group20.model;

/**
 * The Counter class is used to store and display information regarding the Simulation;
 * it is used for both the basic console interface as well as the short and long GUI reports.
 *
 * @author Group_20
 * @version 1.0, April 2014
 *
 */
public class Counter {

	private int totalPlanes; // the total number of planes in the Simulation
	private int commercialAircraft;
	private int gliders;
	private int lightAircraft;
	private int waitingTime; // the total waitingTime of all planes
	private int breakdowns; // the number of aircraft breakdowns
	private int crashes; // the number of aircraft crashes
	private int takeoffs; // the number of aircrafts that have taken off
	private int landings; // the number of aircrafts that have landed
	private int grounded; // the number of aircrafts on the ground
	private int flying; // the number of aircrafts in the air
	
	/**
	 * Creates a new <code>Counter</code>, which will call the clear method to ensure that
	 * all of the fields are reset to zero, ready for the Simulation to begin.
	 * 
	 * @see #clear()
	 */
	public Counter() {
		clear();
	}
	
	/**
	 * Increments the totalPlanes field to show that a new Aircraft has been created/is now
	 * present in the simulation.
	 */
	public void incrementTotalPlanes() {
		totalPlanes++;
	}
	
	/**
	 * Increments the waitingTime field, this is done when an Aircraft has taken off/landed/crashed, 
	 * essentially when it has exited the Simulation so that we get it's true total waiting time.
	 * 
	 * @param increment the waiting time of the Aircraft exiting the Simulation.
	 */
	public void incrementWaitingTime(int increment) {
		waitingTime += increment;
	}
	
	/**
	 * Increments the breakDowns field to show that an Aircraft on the ground has suffered
	 * from a breakdown and requires maintenance.
	 */
	public void incrementBreakdowns() {
		breakdowns++;
	}
	
	/**
	 * Increments the crashes field to show that an Aircraft in the air has crashed.
	 */
	public void incrementCrashes() {
		crashes++;
	}
	
	/**
	 * Increments the commercialAircraft field in order to indicate that a Commercial Aircraft
	 * has been generated and has entered the Simulation.
	 */
	public void incrementCommercialAircraft() {
		commercialAircraft++;
	}
	
	/**
	 * Increments the gliders field in order to indicate that a Glider
	 * has been generated and has entered the Simulation.
	 */
	public void incrementGlider() {
		gliders++;
	}
	
	/**
	 * Increments the lightAircraft field in order to indicate that a Lighty Aircraft
	 * has been generated and has entered the Simulation.
	 */
	public void incrementLightAircraft() {
		lightAircraft++;
	}
	
	
	
	/**
	 * Returns the number of Aircraft crashes in the current Simulation.
	 * @return the number of crashes in the current Simulation.
	 */
	public int getCrashes() {
		return crashes;
	}
	
	/**
	 * Increments the takeoffs field to show that an Aircraft on the ground has taken off.
	 */
	public void incrementTakeoffs() {
		takeoffs++;
	}
	
	/**
	 * Returns the number of Aircraft that have taken off in the current Simulation.
	 * @return the number of Aircraft that have taken off in the current Simulation.
	 */
	public int getTakeoffs() {
		return takeoffs;
	}
	
	/**
	 * Increments the landings field to show that an Aircraft in the air has landed.
	 */
	public void incrementLandings() {
		landings++;
	}
	
	/**
	 * Returns the number of Aircraft that have landed in the current Simulation.
	 * @return the number of Aircraft that have landed in the current simulation.
	 */
	public int getLandings() {
		return landings;
	}
	
	/**
	 * This method sets the flying field to equal the parameter value; it is called at
	 * each step of the Simulation, so that the statistics are up to date.
	 * 
	 * @param flying the number of Aircraft in the air in the current Simulation.
	 */
	public void setFlying(int flying) {
		this.flying = flying;
	}
	
	/**
	 * Returns the number of Aircraft that are in the air in the current Simulation.
	 * @return the number of Aircraft that are in the air in the current Simulation.
	 */
	public int getFlying() {
		return flying;
	}
	
	/**
	 * This method sets the grounded field to equal the parameter value; it is called at
	 * each step of the Simulation, so that the statistics are up to date. It is identical
	 * in function to the {@link #setFlying(int)} method.
	 * 
	 * @param grounded the number of Aircraft on the ground in the current Simulation.
	 */
	public void setGrounded(int grounded) {
		this.grounded = grounded;
	}
	
	/**
	 * Returns the number of Aircraft that are on the ground in the current Simulation.
	 * @return the number of Aircraft that are on the ground in the current Simulation.
	 */
	public int getGrounded() {
		return grounded;
	}
	
	/**
	 * This method clears all of the fields, so that they are ready to be used for a 
	 * new Simulation. It is called in the {@link #Counter()} constructor.
	 */
	public void clear() {
		totalPlanes = 0;
		commercialAircraft = 0;
		gliders = 0;
		lightAircraft = 0;
		waitingTime = 0;
		takeoffs = 0;
		landings = 0;
		breakdowns = 0;
		crashes = 0;
		grounded = 0;
		flying = 0;
	}

	
	/**
	 * This method is called at the end of a Simulation to show summary information regarding
	 * the finished Simulation. It shows statistics that could be used to analyse the variables
	 * used and to choose the best/safest probabilities for the Simulation.
	 * 
	 * @return summary information regarding the current, finished Simulation.
	 */
	public String toString() {
		return (
				" Total Planes: " + totalPlanes + "\n" +
			    " Commercial Aircraft: " + commercialAircraft + "\n" +
				" Gliders: " + gliders + "\n" +
				" LightAircraft: " + lightAircraft + "\n" +
				" Total Takeoffs: " + takeoffs + "\n" +
				" Total Landings: " + landings + "\n" +
				" Average Waiting Time: " + waitingTime/totalPlanes + "\n" +
				" Total Crashes: " + crashes + "\n" +
				" Total Breakdowns: " + breakdowns + "\n" +
				" Planes still grounded: " + grounded + "\n" + 
				" Planes still flying: " + flying + "\n"
				);
	}

	/**
	 * This method is called at every step of a Simulation in order to produce the longer report for the
	 * GUI; it shows information for every step of the Simulation, which could be used for a detailed
	 * analysis of the Simulation and to also find potential bugs.
	 * 
	 * @return detailed information for each step of the Simulation.
	 */
	public String longReport() {
		return ( 
		"       |       Total Planes: " + totalPlanes + 
	    "       |       Grounded: " + grounded + 
		"       |       Takeoffs: " + takeoffs +
		"       |       Flying: " + flying +
		"       |       Landings: " + landings +
		"       |       Crashes: " + crashes +
		"       |       Breakdowns: " + breakdowns
		);
	}

}