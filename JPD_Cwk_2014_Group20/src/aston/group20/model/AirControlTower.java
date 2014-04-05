package aston.group20.model;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Controls the queues containing the active aircrafts
 * 
 * @author Group_20
 * @version 1.0, March 2014
 * 
 */
public class AirControlTower {

	private PriorityQueue<IAircraft> incoming; // the queue for incoming Aircraft
	private PriorityQueue<IAircraft> outgoing; // the queue for Outgoing Aircraft
	private PriorityQueue<IAircraft> brokenDown; // the queue for Aircraft that have broken down
	private Counter counter;

	public AirControlTower(Strategy strategy) {
		strategy = new FuelStrategy();
		incoming = new PriorityQueue<IAircraft>(500, strategy);
		outgoing = new PriorityQueue<IAircraft>();
		brokenDown = new PriorityQueue<IAircraft>();
		counter = new Counter();
	}
	
	public void step() {
		for (IAircraft a : (brokenDown.toArray(new IAircraft[brokenDown.size()]))) { // call the step method for all brokenDown planes;
			a.step();                                                                             // this will cause their maintenance to continue
			if (! a.isBrokeDown()) { // if the aircraft has been fixed
				outgoing.add(a); // add it to the outgoing queue
				brokenDown.remove(a); // and remove it from the broken down queue
			}
			
		}
		
		for (IAircraft a : (incoming.toArray(new IAircraft[incoming.size()]))) {
			a.step();
			if (a.getFuelLevel() - a.getLandingTime() < 0) { // if the plane doesn't have enough fuel to land
				incoming.remove(a);
				counter.incrementCrashes();
				counter.incrementWaitingTime(a.getWaitingTime());
			} 
		}
		
		for (IAircraft a : (outgoing.toArray(new IAircraft[outgoing.size()]))) {
			a.step();
			if(a.isBrokeDown()) {
				outgoing.remove(a);
				brokenDown.add(a);
				counter.incrementBreakdowns();
			}
		}
	}
	
	public void finish() {
		for(IAircraft it : incoming) {
			counter.incrementWaitingTime(it.getWaitingTime());
		}
		for(IAircraft it : outgoing) {
			counter.incrementWaitingTime(it.getWaitingTime());
		}
		
		
		counter.setGrounded(outgoing.size());
		counter.setFlying(incoming.size());
	}
	
	public PriorityQueue<IAircraft> getIncoming() {
		return incoming;
	}
	
	public PriorityQueue<IAircraft> getOutgoing() {
		return outgoing;
	}

	public PriorityQueue<IAircraft> getBrokenDown() {
		return brokenDown;
	}
	
	public Counter getCounter() {
		return counter;
	}

	public void clear() {
		incoming.clear();
		outgoing.clear();
		brokenDown.clear();
	}
	
}