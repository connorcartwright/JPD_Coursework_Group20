package aston.group20.model;

public abstract class Airport {
	
	private Air_Control_Tower ACT;
	private Runway runway;
	

	public Airport() {
		ACT = new Air_Control_Tower();
	}
	
	public void schedule() {
		// method should call the schedule method of the aircraft
		// only the head of each queue is needed each turn,
		// unless the fuel is low - need to work out how to implement this
		// every other aircraft can just have it's time incremented, and the chance
		// of breaking down IF on the ground
		if(runway.isAvailable()) {
		Aircraft Incoming = ACT.getIncoming().peek();
		Aircraft Outgoing = ACT.getOutgoing().peek();
		if (Outgoing.getTakeoffTime() < Incoming.getFuelFlyingTime() - Incoming.getLandingTime()) {
			takeOff(Outgoing);
		}
		else {
			land(Incoming);
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
