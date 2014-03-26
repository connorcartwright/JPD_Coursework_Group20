package aston.group20.model;
import java.util.PriorityQueue;

/**
 * Controls the queues containing the active aircrafts
 * 
 * @author Group_20
 * @version 1.0, March 2014
 * 
 */
public class Air_Control_Tower {

	private PriorityQueue<Aircraft> incoming;
	private PriorityQueue<Aircraft> outgoing;
	private PriorityQueue<Aircraft> brokenDown;

	public Air_Control_Tower() {
		incoming = new PriorityQueue<Aircraft>();
		outgoing = new PriorityQueue<Aircraft>();
		brokenDown = new PriorityQueue<Aircraft>();
	}
	
	public PriorityQueue<Aircraft> getIncoming() {
		return incoming;
	}

	public PriorityQueue<Aircraft> getOutgoing() {
		return outgoing;
	}

	public PriorityQueue<Aircraft> getBrokenDown() {
		return brokenDown;
	}

	public void clear() {
		incoming.clear();
		outgoing.clear();
		brokenDown.clear();
	}
	
}