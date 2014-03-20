package aston.group20.model;

public abstract class Airport {
	
	private Air_Control_Tower ACT;
	private Runway runway;
	

	public Airport() {
		ACT = new Air_Control_Tower();
	}
	
	public void schedule() {
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
