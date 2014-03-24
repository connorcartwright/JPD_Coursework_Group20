package aston.group20.model;
import java.util.Random;

public class Simulator {
	
	private static final double COMMERCIAL_CREATION_PROBABILITY = 0.1; /// ------------------------- p IMPORTANT
	private static final double LIGHT_CREATION_PROBABILITY = 0.005;
	private static final double GLIDER_CREATION_PROBABILITY = 0.002;
	
	private Airport airport;
	
	private int step;
	private static int numSteps = 50000; // by default run the simulation for 1 step
	
	private static final int SEED = 17;
	private static final Random rand = new Random(SEED);
	
	public static void main(String[] args) {
		if(args.length >= 1) {
			numSteps = Integer.parseInt(args[0]);
		}
		
		if (numSteps <= 0) {
			numSteps = 1;
		}

		Simulator sim = new Simulator();
		sim.simulate(numSteps);
		
		
	}

	public Simulator() {
		this.airport = new Airport();
		reset();
	}
	
	public void simulate(int numSteps) {
		for(int step = 1; step <= numSteps; step++) {
			simulateOneStep();
		}
		System.out.println(airport.getAirControlTower().summary());
	}
		
		public void simulateOneStep() {
			generateAircraft();
			airport.schedule();
			step++;
		}
	
	private void generateAircraft() {
		
			if (rand.nextDouble() <= GLIDER_CREATION_PROBABILITY) {
				Glider glider = new Glider();
				if (rand.nextDouble() < 0.5) {
				airport.getAirControlTower().addIncoming(glider);
				}
				else {
					airport.getAirControlTower().addOutgoing(glider);
				}
			} 
			else if (rand.nextDouble() <= LIGHT_CREATION_PROBABILITY) {
				Light_Aircraft light = new Light_Aircraft();
				if (rand.nextDouble() < 0.5) {
				airport.getAirControlTower().addIncoming(light);
				}
				else {
					airport.getAirControlTower().addOutgoing(light);
				}
			}
			else if (rand.nextDouble() <= COMMERCIAL_CREATION_PROBABILITY) {
				Commercial_Aircraft commercial = new Commercial_Aircraft();
				if (rand.nextDouble() < 0.5) {
				airport.getAirControlTower().addIncoming(commercial);
				}
				else {
					airport.getAirControlTower().addOutgoing(commercial);
				}
				}
		}
	
	public void reset() {
		step = 0;
		airport.getAirControlTower().clear();
		//generateAircraft();
	}
	}
