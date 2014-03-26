package aston.group20.model;

public class Airport { // //// would it be better to have this as abstract

	private Air_Control_Tower ACT;
	private Runway runway;
	private boolean defaultScheduling;
	private Counter counter;

	public Airport() {
		ACT = new Air_Control_Tower();
		defaultScheduling = true;
		runway = new Runway();
		counter = new Counter();
	}

	public void schedule() {
		step();
		
		Aircraft Incoming = ACT.getIncoming().peek(); // Variable Incoming = the first Incoming Aircraft
		Aircraft Outgoing = ACT.getOutgoing().peek(); // Variable Outgoing = the first Outgoing aircraft

		if (runway.isAvailable()) { // if the runway is available
			if (defaultScheduling) { // if we are prioritising landings
				if (Incoming != null) { // if there is a plane waiting to land
					if (Incoming.getFuelFlyingTime() - Incoming.getLandingTime() >= 0) { // if it can land before it crashes
						land(Incoming); // let it land
					}
					else {
						crashed(Incoming); // it is going to crash
					}
				} 
				else if (Outgoing != null) { // else if there's a plane waiting to takeoff
					takeOff(Outgoing); // let it takeoff
				}
			}
			
			else { // if we are prioritising landings by fuel time, but also letting planes take off if they can do so without causing the Incoming to crash
				if (Incoming != null) { // if there is a plane waiting to land
				if (Incoming.getFuelFlyingTime() - Incoming.getLandingTime() > 0) { // if it can land before it crashes
					if (Outgoing.getTakeoffTime() < Incoming.getFuelFlyingTime() - Incoming.getLandingTime()) {
					takeOff(Outgoing); // if the head of the takeoff queue can takeoff without causing the head of the incoming queue to crash
					}                 // then let it take off
					else {
						land(Incoming); // else let the head of the incoming queue land
					}
				} 
				else {
					crashed(Incoming); // else the plane is going to crash
				}
				}
				else if (Outgoing != null) {
					takeOff(Outgoing);
				}
			}
		} 
		else {
			runway.incrementTime();
		}
		}
	
	private void step() {
		for (Aircraft a : (ACT.getBrokenDown().toArray(new Aircraft[ACT.getBrokenDown().size()]))) { // call the step method for all brokenDown planes;
			a.step();                                                                             // this will cause their maintenance to continue
			if (! a.isBrokedown()) { // if the aircraft has been fixed
				ACT.getOutgoing().add(a); // add it to the outgoing queue
				ACT.getBrokenDown().remove(a); // and remove it from the broken down queue
			}
			
		}
		
		for (Aircraft a : (ACT.getIncoming().toArray(new Aircraft[ACT.getIncoming().size()]))) {
			a.step();
			if (a.getFuelFlyingTime() - a.getLandingTime() < 0) { // if the plane has fuel enough to land still
				crashed(a);
			} 
		}
		
		for (Aircraft a : (ACT.getOutgoing().toArray(new Aircraft[ACT.getOutgoing().size()]))) {
			a.step();
			if(a.isBrokedown()) {
				brokeDown(a); // if the aircraft is brokedown call the brokeDown method
			}
		}
		counter.setGrounded(ACT.getOutgoing().size());
		counter.setFlying(ACT.getIncoming().size());
	}
	

	private void takeOff(Aircraft a) {
		counter.incrementTakeoffs();
		counter.incrementWaitingTime(a.getWaitingTime());
		runway.setAvailable(false);
		runway.setOccupiedTime(a.getTakeoffTime());
		ACT.getOutgoing().remove(a);
		if (a instanceof Glider) {
			Light_Aircraft light = new Light_Aircraft();
			light.setWaitingTime(a.getWaitingTime());
			ACT.getIncoming().add(light);
		}
	}

	private void land(Aircraft a) {
		counter.incrementLandings();
		runway.setAvailable(false);
		runway.setOccupiedTime(a.getLandingTime());
		ACT.getIncoming().remove(a);
	}

	public void crashed(Aircraft a) {
		ACT.getIncoming().remove(a);
		counter.incrementCrashes();
		counter.incrementWaitingTime(a.getWaitingTime());
	}

	public void brokeDown(Aircraft a) {
		ACT.getOutgoing().remove(a);
		ACT.getBrokenDown().add(a);
		counter.incrementBreakdowns();
	}

	public Air_Control_Tower getAirControlTower() {
		return ACT;
	}
	
	public Counter getCounter() {
		return counter;
	}

}