package aston.group20.model;
import java.util.Random;

/**
 * This class models a simplistic Airport simulation, based on an Airport that
 * is managing Incoming and Outgoing Aircraft.

 * @author Group_20
 * @version 1.0, March 2014
 */
public class Simulator {

	private Airport airport; // the airport to be used for the Simulation
	private int step; // the current step of the simulation
	private static int numSteps = 2880; // the number of steps to run for, set to default
	private int seed = 17;
	private final Random rand = new Random(seed);
	private Strategy[] strategy = { // array containing the different types of strategies
			new WaitingTimeStrategy(),
			new FuelStrategy() // can add more strategies here whenever needed		
	};
	private int chosenStrategy; // the strategy chosen, used in conjunction with the Array
	private StringBuilder longReport; // the StringBuilder contanining the step-by-step long report
	private static Simulator sim;

	/**
	 * Creates a GUI which will allow the user to run the Simulation for a specified number
	 * of steps, and also allow them to change the various Aircraft probabilities, as well
	 * as the Strategy/Seed used.
	 */
	public static void main(String[] args) {
		if (args.length >= 1) {
			numSteps = Integer.parseInt(args[0]);
		}
		if (numSteps <= 0) {
			numSteps = 1;
		}
		sim = new Simulator();
		sim.simulate(numSteps);
	}

	/**
	 * The constructor creates a Simulator object, which contains an Airport where the majority
	 * of the action will take place, as well as a StringBuilder for a longReport.
	 * The constructor also calls the reset method in order to ensure that everything is ready for a 
	 * new Simulation.
	 * 
	 * @see #reset()
	 */
	public Simulator() {
		airport = new Airport();
		longReport = new StringBuilder();
		reset();
	}

	/**
	 * Run the simulation from its current state for the given number of steps; the 
	 * simulateOneStep method will be called until the number of steps has been run, after which
	 * the state of the object will be finalised and the end report will be printed to the console.
	 * 
	 * @param numSteps the number of steps to run the simulation for
	 * @see #simulateOneStep()
	 */
	public void simulate(int numSteps) {
		airport.setStrategy(strategy[chosenStrategy]);
		for (step = 1; step <= numSteps; step++) {
			simulateOneStep();
		}
		airport.getACT().finish();
		System.out.println(getResults());
		
	}

	/**
	 * Run the simulation from its current state for a single step. This will call the 
	 * Airport.schedule method which will cause the Aircraft to take the appropriate actions if
	 * necessary.
	 * This method also has the chance to generate an Aircraft by calling the {@link #generateAircraft()} 
	 * method.
	 * 
	 * @see Airport#schedule()
	 */
	public void simulateOneStep() {
		generateAircraft();
		airport.schedule();
		longReport.append(printLongReport());
		System.out.println(printLongReport());
	}

	/**
	 * This method has the potential to add an Aircraft to either the Incoming or Outgoing queues of the
	 * Airports AirControlTower. It calls the Hangar's generateAircraft method which could return an Aircraft
	 * that would then be added to one of the queues.
	 * 
	 * @see Hangar#generateAircraft()
	 */
	private void generateAircraft() {
		IAircraft aircraft = airport.getHangar().generateAircraft(); // has a chance of generating an aircraft
		if (aircraft != null) { // if an aircraft was generated
			airport.getACT().getCounter().incrementTotalPlanes(); // increment the total no. of planes
			
			if(aircraft.isFlying()) {
				airport.getACT().getIncoming().add(aircraft); // 50% chance to by flying
			}
			else {
				airport.getACT().getOutgoing().add(aircraft); // 50% chance to be grounded
			}
		}
		// else do nothing if an Aircraft wasn't generated
	}

	/**
	 * Resets the simulation so that it is ready to run again if it is required.
	 */
	public void reset() {
		step = 0;
		airport.reset();
		rand.setSeed(seed);
		longReport.setLength(0);
	}
	
	/**
	 * This method returns the Strategy Array containing the different types of Strategies that could
	 * potentially be used in the Simulation.
	 * 
	 * @return the Strategy Array containing the different types of Strategies.
	 */
	public Strategy[] getStrategies() {
		return strategy;
	}
	
	/**
	 * This method sets the strategy by using the parameter integer; when the {@link #simulate(int)} method is
	 * called the Strategy is chosen through the strategies array and the chosenStrategy integer.
	 * 
	 * @param chosenStrategy the strategy that should be chosen from the strategies array.
	 */
	public void setStrategy(int chosenStrategy) {
		this.chosenStrategy = chosenStrategy;
	}
	
	
	/**
	 * This method returns a String that contains the shorter end results of the Simulation.
	 * @return a String containing the end results of the Simulation.
	 */
	public String getResults() {
		return airport.getACT().getCounter().toString();
	}
	
	/**
	 * This method returns a String that contains the longer report of the Simulation. It contains
	 * information for each step of the simulation, formatted for easy readability.
	 * 
	 * @return a String containing the longer report of the Simulation.
	 */
	public String printLongReport() {
		return (" Step: " + step + airport.getACT().getCounter().longReport() + "\n");	
	}
	
	/**
	 * This method returns the complete longReport of the Simulation. It contains detailed information
	 * from each and every step of the Simulation.
	 * 
	 * @return a StringBuilder which contains the complete longReport of the Simulation.
	 */
	public StringBuilder getLongReport() {
		return longReport;
	}

	/**
	 * This method returns the Airport that the Simulation is using,
	 * so that the the other classes can be accessed easily from the GUI.
	 * @return returns the Airport that the Simulation is using.
	 */
	public Airport getAirport() {
		return airport;
	}
	
}