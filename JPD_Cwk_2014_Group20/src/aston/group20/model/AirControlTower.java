package aston.group20.model;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * The Air Control Tower class manages the queues containing the active aircraft;
 * it ensures that the aircraft within the queues are sorted correctly based on the
 * strategy comparator that was passed through from the Airport class.
 * 
 * @author Group_20
 * @version 1.0, March 2014
 * 
 */
public class AirControlTower {

	private Random gen = new Random(42);
	private PriorityQueue<IAircraft> incoming; // the queue for incoming Aircraft
	private PriorityQueue<IAircraft> outgoing; // the queue for Outgoing Aircraft
	private PriorityQueue<IAircraft> brokenDown; // the queue for Aircraft that have broken down
	private Counter counter;

    /**
     * Creating a new <code>AirControlTower</code>, which will initialise the queues
     * and set the strategy for how to manage the Incoming aircraft.
     * 
     * @param strategy determines how the Incoming Aircraft will be sorted.
     * @see Strategy and concrete subclasses to see the different ways of 
     * sorting incoming aircraft.
     */
	public AirControlTower(Strategy strategy) {
		incoming = new PriorityQueue<IAircraft>(500, strategy);
		outgoing = new PriorityQueue<IAircraft>();
		brokenDown = new PriorityQueue<IAircraft>();
		counter = new Counter();
	}
	
	/**
	 * This method is called from the Airport class; it iterates through the queues, 
	 * calling the step method of each individual Aircraft; depending on the state of 
	 * the Aircraft it may move Aircraft from the outgoing queue to the broken down 
	 * queue and vice versa. It may also remove Aircraft from the Incoming queue if
	 * the plane does not have enough fuel left to land, that is if they have crashed.
	 * It will perform/call the appropriate methods in all cases.
	 * 
	 * 
	 * @see Aircraft#step()
	 */
	public void step() {
		for (IAircraft a : brokenDown.toArray(new IAircraft[brokenDown.size()])) { // call the step method for all brokenDown planes;
			a.step();                                                              // this will cause their maintenance to continue
			if (a.isBrokeDown() == false) { // if the aircraft has been fixed
				outgoing.add(a); // add it to the outgoing queue
				brokenDown.remove(a); // and remove it from the broken down queue
			}
			
		}
		for (IAircraft a : incoming.toArray(new IAircraft[incoming.size()])) {
			a.step();
			
			if(a.isCrashed()) {
				incoming.remove(a);
				counter.incrementCrashes();
				counter.incrementWaitingTime(a.getWaitingTime());
			}

		}
		for (IAircraft a : outgoing.toArray(new IAircraft[outgoing.size()])) {
			a.step();
			
			if (a.isBrokeDown() == false) {
				if (a.isFlying() == false && gen.nextDouble() < 0.0001) { // if the next double meets the breakdown criteria
					a.setBrokeDown(true); // then the plane has broken down;
					outgoing.remove(a); // move it from the outgoing queue
					brokenDown.add(a); // into the broken down queue
					counter.incrementBreakdowns(); // and increment the number of breakdowns
				}
			}
		}
		counter.setGrounded(outgoing.size() + brokenDown.size());
		counter.setFlying(incoming.size());
	}
	
	/**
	 * This method is called from the Simulator class after the Simulation has finished.
	 * The method ensures that all of the planes still in the queues are accounted for in
	 * the end report through the counters increment/set methods.
	 * 
	 * @see Counter#incrementWaitingTime(int)
	 */
	public void finish() {
		for(IAircraft a : incoming) {
			counter.incrementWaitingTime(a.getWaitingTime());
		}
		for(IAircraft a : outgoing) {
			if (a instanceof Glider) { // if the planes was a glider, account for the light Aircraft towing it
				counter.incrementWaitingTime(a.getWaitingTime());
				counter.incrementWaitingTime(a.getWaitingTime());
			}
			else { // else if it wasn't a glider then just do a single Aircraft
			counter.incrementWaitingTime(a.getWaitingTime());
			}
		}
		for(IAircraft a : brokenDown) {
			counter.incrementWaitingTime(a.getWaitingTime());
		}
	}
	
	/**
	 * Accessor method to return the queue containing the Incoming Aircraft.
	 * @return the queue of Incoming Aircraft.
	 */
	public PriorityQueue<IAircraft> getIncoming() {
		return incoming;
	}
	
	/**
	 * Accessor method to return the queue containing the Outgoing Aircraft.
	 * @return the queue of Outgoing Aircraft.
	 */
	public PriorityQueue<IAircraft> getOutgoing() {
		return outgoing;
	}

	/**
	 * Accessor method to return the queue containing the Broken Down Aircraft.
	 * @return the queue of Broken Down Aircraft
	 */
	public PriorityQueue<IAircraft> getBrokenDown() {
		return brokenDown;
	}
	
	/**
	 * Accessor method to return the Counter which holds the statistics and is 
	 * used to produce the end report.
	 * @return the Counter.
	 */
	public Counter getCounter() {
		return counter;
	}

	/**
	 * This method is used to clear the queues and the counter; it is called from
	 * the Simulator class when we want to reset the Simulation.
	 */
	public void clear() {
		incoming.clear();
		outgoing.clear();
		brokenDown.clear();
		counter.clear();
	}
	
	/**
	 * This method is used to set the seed of the random number generator, so that the
	 * Simulation results are reproducible.
	 * @param seed the seed used for the random number generator (gen).
	 */
	public void setSeed(int seed) {
		gen.setSeed(seed);
	}
	
}