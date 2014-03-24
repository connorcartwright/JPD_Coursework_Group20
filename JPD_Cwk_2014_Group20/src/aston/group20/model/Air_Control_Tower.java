package aston.group20.model;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Controls the queues containing the active aircrafts
 * as well as the lists used for reporting the statistics.
 * 
 * @author Group_20
 * @version 1.0, March 2014
 *
 */
public class Air_Control_Tower {

	private PriorityQueue<Aircraft> incoming;
	private PriorityQueue<Aircraft> outgoing;
	private PriorityQueue<Aircraft> brokenDown;
	private ArrayList<Aircraft> incomingSummary;
	private ArrayList<Aircraft> outgoingSummary;
	private ArrayList<Aircraft> crashed;

	public Air_Control_Tower() {
		incoming = new PriorityQueue<Aircraft>();
		outgoing = new PriorityQueue<Aircraft>();
		brokenDown = new PriorityQueue<Aircraft>();
		incomingSummary = new ArrayList<Aircraft>();
		outgoingSummary = new ArrayList<Aircraft>();
		crashed = new ArrayList<Aircraft>();
	}

	public void addIncoming(Aircraft a) {
		incoming.add(a);
	}

	public PriorityQueue<Aircraft> getIncoming() {
		return incoming;
	}

	public void removeIncoming(Aircraft a) {
		incoming.remove(a);

	}

	public void addOutgoing(Aircraft a) {
		outgoing.add(a);
	}

	public PriorityQueue<Aircraft> getOutgoing() {
		return outgoing;
	}

	public void removeOutgoing(Aircraft a) { // /////////////////////////////////// ----------------------------------
		outgoing.remove(a); // better to use the poll method?
	}

	public void addBrokenDown(Aircraft a) {
		brokenDown.add(a);
	}
	
	public PriorityQueue<Aircraft> getBrokenDown() {
		return brokenDown;
	}
	
	public void addCrashed(Aircraft a) {
		crashed.add(a);
	}

	public void addIncomingSummary(Aircraft a) {
		incomingSummary.add(a);
	}

	public void addOutgoingSummary(Aircraft a) {
		outgoingSummary.add(a);
	}
	
	public String summary() {
		
		System.out.println(outgoing.size());
		
		int totalPlanes = incomingSummary.size() + outgoingSummary.size() + crashed.size(); // need to add the planes still grounded/in the air
		int averageWaitingTime = 0;
		
		for(int i = 0; i < incomingSummary.size(); i++) {
			averageWaitingTime += incomingSummary.get(i).getWaitingTime();
		}
		for(int i = 0; i < outgoingSummary.size(); i++) {
			averageWaitingTime += outgoingSummary.get(i).getWaitingTime();
		}
		for(int i = 0; i < crashed.size(); i++) {
			averageWaitingTime += crashed.get(i).getWaitingTime();
		}
		
		averageWaitingTime /= totalPlanes;
		
		return  "Total planes: " + totalPlanes + "\n" +
				"Total landings: " + incomingSummary.size() + "\n" +
				"Total takeoffs: " + outgoingSummary.size() + "\n" +
				"Total crashes: " + crashed.size() + "\n" + 
				"Average Waiting Time: " + averageWaitingTime + "\n" +
				"Planes still grounded: " + outgoing.size() + "\n" +
				"Planes still flying: " + incoming.size();	
	}

	public void clear() {
		incoming.clear();
		outgoing.clear();
		brokenDown.clear();
		incomingSummary.clear();
		outgoingSummary.clear();
		crashed.clear();
	}
}