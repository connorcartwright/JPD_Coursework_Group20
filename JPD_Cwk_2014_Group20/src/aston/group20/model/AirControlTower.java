package aston.group20.model;
import java.util.PriorityQueue;

/**
 * Controls the queues containing the active aircrafts
 * 
 * @author Group_20
 * @version 1.0, March 2014
 * 
 */
public class AirControlTower {

	private PriorityQueue<IAircraft> incoming;
	private PriorityQueue<IAircraft> outgoing;
	private PriorityQueue<IAircraft> brokenDown;

	public AirControlTower() {
		incoming = new PriorityQueue<IAircraft>();
		outgoing = new PriorityQueue<IAircraft>();
		brokenDown = new PriorityQueue<IAircraft>();
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

	public void clear() {
		incoming.clear();
		outgoing.clear();
		brokenDown.clear();
	}
	
}