package aston.group20.model;

import java.util.Iterator;

public abstract class Airport {
	
	private Air_Control_Tower ACT;
	private Runway runway;
	

	public Airport() {
		ACT = new Air_Control_Tower();
	}
	
	public void schedule() {
		// assuming the two queues are populated by a populate method 
		// here we iterate over all queues/aircraft, calling core method step()
		for(Iterator<Aircraft> it = ACT.getIncoming().iterator(); it.hasNext(); )
		{
			it.next().step();
		}
		for(Iterator<Aircraft> it = ACT.getOutgoing().iterator(); it.hasNext(); )
		{
			it.next().step();
		}
		for(Iterator<Aircraft> it = ACT.getBrokenDown().iterator(); it.hasNext(); )
		{
			it.next().step();
		}
		
		
		
		
		// method should call the schedule method of the aircraft
		// only the head of each queue is needed each turn,
		// unless the fuel is low - need to work out how to implement this
		// every other aircraft can just have it's time incremented, and the chance
		// of breaking down IF on the ground
		if(runway.isAvailable()) {
		ACT.getIncoming().peek().step();
		ACT.getOutgoing().peek().step();
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
