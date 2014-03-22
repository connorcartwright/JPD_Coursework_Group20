package aston.group20.model;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Air_Control_Tower {

	private PriorityQueue<Aircraft> incoming;
	private PriorityQueue<Aircraft> outgoing;
	private PriorityQueue<Aircraft> brokenDown;
	private ArrayList<Aircraft> incomingSummary;
	private ArrayList<Aircraft> outgoingSummary;

	public Air_Control_Tower() {
		incoming = new PriorityQueue<Aircraft>();
		outgoing = new PriorityQueue<Aircraft>();
		brokenDown = new PriorityQueue<Aircraft>();
		incomingSummary = new ArrayList<Aircraft>();
		outgoingSummary = new ArrayList<Aircraft>();
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

	public void removeOutgoing(Aircraft a) { // /////////////////////////////////
												// ----------------------------------
		outgoing.remove(a); // better to use the poll method?
	}

	public void addBrokenDown(Aircraft a) {
		brokenDown.add(a);
	}
	
	public PriorityQueue<Aircraft> getBrokenDown() {
		return brokenDown;
	}

	// public void checkBrokenDown() {
	// Aircraft a = brokenDown.peek();
	// if(a.getMaintenanceTime() >= 120) {
	// a.setBrokedown(false);
	// addIncoming(a);
	// brokenDown.poll();
	// }
	// }

	public void addIncomingSummary(Aircraft a) {
		incomingSummary.add(a);
	}

	public void addOutgoingSummary(Aircraft a) {
		outgoingSummary.add(a);
	}

	public void summary() {
		// go through the finished arraylists
		// and find the average waiting times
		// etc.
	}

}
