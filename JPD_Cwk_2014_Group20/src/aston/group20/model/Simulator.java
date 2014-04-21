package aston.group20.model;
import java.util.Random;
import aston.group20.GUIview.GUI;

public class Simulator {

	private Airport airport;

	private int step;
	private static int numSteps = 100000; // the number of steps to run for
	private final Random rand = new Random();
	private Strategy[] strategy = {
			new WaitingTimeStrategy(),
			new FuelStrategy() // can add more strategies here whenever needed		
	};
	private int chosenStrategy;
	private StringBuilder longReport;

	public static void main(String[] args) {
		if (args.length >= 1) {
			numSteps = Integer.parseInt(args[0]);
		}
		if (numSteps <= 0) {
			numSteps = 1;
		}
		Simulator sim = new Simulator();
		GUI airportSim = new GUI(sim);
	}

	public Simulator() {
		airport = new Airport();
		longReport = new StringBuilder();
		reset();
	}

	public void simulate(int numSteps) {
		airport.setStrategy(strategy[chosenStrategy]);
		for (step = 1; step <= numSteps; step++) {
			simulateOneStep();
		}
		airport.getACT().finish();
		System.out.println(getResults());
	}

	public void simulateOneStep() {
		generateAircraft();
		airport.schedule();
		longReport.append(printLongReport());
		System.out.println(printLongReport());
	}

	private void generateAircraft() {
		IAircraft aircraft = airport.getHangar().generateAircraft(rand); // has a chance of generating an aircraft
		if (aircraft != null) { // if an aircraft was generated
			airport.getACT().getCounter().incrementTotalPlanes(); // increment the total no. of planes
			if (rand.nextDouble() < 0.5 && !(aircraft instanceof Glider)) { 
				airport.getACT().getIncoming().add(aircraft); // 50% chance to by flying
				aircraft.setIsFlying(true);
			} 
			else {
				airport.getACT().getOutgoing().add(aircraft); // 50% chance to be grounded
			}
		}
	}

	public void reset() {
		step = 0;
		airport.getACT().clear();
		longReport.setLength(0);
	}
	
	public String getResults() {
		return airport.getACT().getCounter().toString();
	}
	
	public void setProbabilities(double commercial, double glider, double light) {
		airport.getHangar().setProbabilities(commercial, glider, light);
	}
	
	public Strategy[] getStrategies() {
		return strategy;
	}
	
	public void setStrategy(int chosenStrategy) {
		this.chosenStrategy = chosenStrategy;
	}
	
	public void setSeed(int seed) {
		rand.setSeed(seed);
	}
	
	public String printLongReport() {
		return (" Step: " + step + airport.getACT().getCounter().longReport() + "\n");	
	}
	
	public StringBuilder getLongReport() {
		return longReport;
	}
	
}
