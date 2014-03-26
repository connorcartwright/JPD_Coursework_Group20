package aston.group20.model;

import java.util.Random;

public class Simulator {

	private Airport airport;
	private Hangar hangar;

	private int step;
	private static int numSteps = 100000; // the number of steps to run for
	private static final int SEED = 17;
	private static final Random rand = new Random(SEED);

	public static void main(String[] args) {
		if (args.length >= 1) {
			numSteps = Integer.parseInt(args[0]);
		}
		if (numSteps <= 0) {
			numSteps = 1;
		}
		Simulator sim = new Simulator();
		sim.simulate(numSteps);
	}

	public Simulator() {
		airport = new Airport();
		hangar = new Hangar();
		reset();
	}

	public void simulate(int numSteps) {
		for (step = 1; step <= numSteps; step++) {
			simulateOneStep();
		}
		System.out.println(airport.getCounter().toString());
	}

	public void simulateOneStep() {
		generateAircraft();
		airport.schedule();
	}

	private void generateAircraft() {
		Aircraft aircraft = hangar.generateAircraft(rand); // has a chance of generating an aircraft
		if (aircraft != null) { // if an aircraft was generated
			airport.getCounter().incrementTotalPlanes(); // increment the total no. of planes
			if (rand.nextDouble() < 0.5) { 
				airport.getAirControlTower().addIncoming(aircraft); // 50% chance to by flying
			} 
			else {
				airport.getAirControlTower().addOutgoing(aircraft); // 50% chance to be grounded
			}
		}
	}

	public void reset() {
		step = 0;
		airport.getAirControlTower().clear();
		airport.getCounter().clear();
	}
}
