package aston.group20.model;
import java.util.Random;
import aston.group20.GUIview.GUI;

public class Simulator {

	private Airport airport;

	private int step;
	private static int numSteps = 10000; // the number of steps to run for
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
		GUI airport = new GUI(sim);
		//sim.simulate(numSteps);
	}

	public Simulator() {
		airport = new Airport();
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
		System.out.println(step);
	}

	private void generateAircraft() {
		IAircraft aircraft = airport.getHangar().generateAircraft(rand); // has a chance of generating an aircraft
		if (aircraft != null) { // if an aircraft was generated
			airport.getCounter().incrementTotalPlanes(); // increment the total no. of planes
			if (rand.nextDouble() < 0.5 && !(aircraft instanceof Glider)) { 
				airport.getAirControlTower().getIncoming().add(aircraft); // 50% chance to by flying
			} 
			else {
				airport.getAirControlTower().getOutgoing().add(aircraft); // 50% chance to be grounded
			}
		}
	}

	public void reset() {
		step = 0;
		airport.getAirControlTower().clear();
		airport.getCounter().clear();
	}
}
