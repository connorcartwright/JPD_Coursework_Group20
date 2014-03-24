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
		for (int i =0; i < ACT.getIncoming().toArray().length; i++) { 
			if (((Aircraft) ACT.getIncoming().toArray()[i]).getFuelFlyingTime() >= 0) { // if the plane has fuel left
				((Aircraft) ACT.getIncoming().toArray()[i]).step();  // call the step method for all of the incoming planes
			}
			else {
				crashed((Aircraft) ACT.getIncoming().toArray()[i]); 
			}
		}
		
		for(int i = 0; i < ACT.getOutgoing().toArray().length; i++) {        // go through the outgoing list and check if any of the planes
			if (((Aircraft) ACT.getOutgoing().toArray()[i]).isBrokedown()) { // have broken down; if they have we take them out of the outgoing
				brokeDown(((Aircraft) ACT.getOutgoing().toArray()[i]));      // queue and add them to the broke down queue
			} 
			else {
			((Aircraft) ACT.getOutgoing().toArray()[i]).step();  // else call the step method for functioning planes 
			}
		}
		
		for (Iterator<Aircraft> it = ACT.getBrokenDown().iterator(); it.hasNext();) { // call the step method for all brokenDown planes;
			it.next().step();                                                        // this will cause their maintenance to continue
		}
		
		Aircraft Incoming = ACT.getIncoming().peek(); // Variable Incoming = the first Incoming Aircraft
		Aircraft Outgoing = ACT.getOutgoing().peek(); // Variable Outgoing = the first Outgoing aircraft

		if (runway.isAvailable()) { // if the runway is available
			if (defaultScheduling) { // if we are prioritising landings
				if (Incoming != null) { // if there is a plane waiting to land
					if (Incoming.getFuelFlyingTime() - Incoming.getLandingTime() >= 0) { // if it can land before it crashes
						land(Incoming); // let it land
					}
					else if (Incoming instanceof Glider){ // if a glider do nothing for now
					}
					else {
						crashed(Incoming); // else the Aircraft is going to/has crashed
					}
				} 
				else if (Outgoing != null) { // else if there's a plane waiting to takeoff
					takeOff(Outgoing); // let it takeoff
				}
			}
			else { // if we are prioritising landings by fuel time, but also letting planes take off if they can do so without causing the Incoming to crash
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