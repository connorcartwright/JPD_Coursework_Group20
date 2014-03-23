package aston.group20.model;

import java.util.Iterator;

public class Airport { ////// would it be better to have this as abstract

	private Air_Control_Tower ACT;
	private Runway runway;
	private boolean defaultScheduling;

	public Airport() {
		ACT = new Air_Control_Tower();
		defaultScheduling = true;
	}

	public void schedule() {
		// assuming the two queues are populated by a populate method
		// here we iterate over all queues/aircraft, calling core method step()
		for (Iterator<Aircraft> it = ACT.getIncoming().iterator(); it.hasNext();) {
			it.next().step();
		}
		for (Iterator<Aircraft> it = ACT.getOutgoing().iterator(); it.hasNext();) {
			it.next().step();
		}
		for (Iterator<Aircraft> it = ACT.getBrokenDown().iterator(); it.hasNext();) {
			it.next().step();
		}

		if (runway.isAvailable()) {
			if (defaultScheduling) {

				if (ACT.getIncoming().peek() != null) { // if there's a plane waiting to land let it land
					land(ACT.getIncoming().peek()); 
				} 
				else if (ACT.getOutgoing().peek() != null) { // else if theres a plane waiting to take off let it takeoff
						takeOff(ACT.getOutgoing().peek());
					}
			}
			else {
				Aircraft Incoming = ACT.getIncoming().peek();
				Aircraft Outgoing = ACT.getOutgoing().peek();
				int incomingTimeLeft = Incoming.getFuelFlyingTime() - Incoming.getLandingTime();
				if (incomingTimeLeft < 0) {
					ACT.removeIncoming(Incoming);
					ACT.addCrashed(Incoming);
				} 
				else  if (Outgoing.getTakeoffTime() < incomingTimeLeft) { // if the plane can takeoff without causing the highest priority landing to crash
					takeOff(Outgoing);
				} 
				else {
					land(Incoming);
				}
			}
		} 
		else {
			runway.incrementTime();
		}

	}

	private void takeOff(Aircraft a) {
		ACT.removeOutgoing(a);
		ACT.addOutgoingSummary(a);
		runway.setAvailable(false);
		runway.setOccupiedTime(a.getTakeoffTime());
	}

	private void land(Aircraft a) {
		ACT.removeIncoming(a);
		ACT.addIncomingSummary(a);
		runway.setAvailable(false);
		runway.setOccupiedTime(a.getLandingTime());
	}

	public Air_Control_Tower getAirControlTower() {
		return ACT;
	}

}