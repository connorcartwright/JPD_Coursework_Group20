package aston.group20.model;

import java.util.Iterator;

public class Airport { // //// would it be better to have this as abstract

	private Air_Control_Tower ACT;
	private Runway runway;
	private boolean defaultScheduling;

	public Airport() {
		ACT = new Air_Control_Tower();
		defaultScheduling = true;
		runway = new Runway();
	}

	public void schedule() {
		for (Iterator<Aircraft> it = ACT.getIncoming().iterator(); it.hasNext();) {
			it.next().step();
		}
		
		for(int i = 0; i < ACT.getOutgoing().toArray().length; i++) {
			((Aircraft) ACT.getOutgoing().toArray()[i]).step();
			if (((Aircraft) ACT.getOutgoing().toArray()[i]).isBrokedown()) {
				brokeDown(((Aircraft) ACT.getOutgoing().toArray()[i]));
			} 
			
		}
		
//		for (Iterator<Aircraft> it = ACT.getOutgoing().iterator(); it.hasNext();) {
//			it.next().step();
//		}
		
		for (Iterator<Aircraft> it = ACT.getBrokenDown().iterator(); it.hasNext();) {
			it.next().step();
		}
		
		Aircraft Incoming = ACT.getIncoming().peek();
		Aircraft Outgoing = ACT.getOutgoing().peek();

		if (runway.isAvailable()) {
			if (defaultScheduling) {
				if (Incoming != null) {
					if (Incoming.getFuelFlyingTime() - Incoming.getLandingTime() >= 0) {
						land(Incoming);
					}
					else {
						crashed(Incoming);
					}
				} 
				else if (Outgoing != null) {
					takeOff(Outgoing);
				}
			}
			else {
				if (Incoming.getFuelFlyingTime() - Incoming.getLandingTime() > 0) {
					if (Outgoing.getTakeoffTime() < Incoming.getFuelFlyingTime() - Incoming.getLandingTime()) {
					takeOff(Outgoing);
					}
					else {
						land(Incoming);
					}
				} 
				else {
					crashed(Incoming);
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

	public void crashed(Aircraft a) {
		ACT.removeIncoming(a);
		ACT.addCrashed(a);
	}
	
	public void brokeDown(Aircraft a) {
		ACT.removeOutgoing(a);
		ACT.addBrokenDown(a);
	}

	public Air_Control_Tower getAirControlTower() {
		return ACT;
	}

}