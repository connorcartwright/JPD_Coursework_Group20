package aston.group20.model;

public class Airport { // //// would it be better to have this as abstract

	private AirControlTower ACT;
	private Runway runway;
	private boolean defaultScheduling;
	private Counter counter;
	private Hangar hangar;

	public Airport() {
		ACT = new AirControlTower();
		defaultScheduling = true;
		runway = new Runway();
		counter = new Counter();
		hangar = new Hangar();
	}

	public void schedule() {
		step();
		
		IAircraft Incoming = ACT.getIncoming().peek(); // Variable Incoming = the first Incoming Aircraft
		IAircraft Outgoing = ACT.getOutgoing().peek(); // Variable Outgoing = the first Outgoing aircraft

		if (runway.isAvailable()) { // if the runway is available
			if (defaultScheduling) { // if we are prioritising landings
				if (Incoming != null) { // if there is a plane waiting to land
					if ((Incoming).getFuelLevel() - Incoming.getLandingTime() >= 0) { // if it can land before it crashes
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
				if (Incoming.getFuelLevel() - Incoming.getLandingTime() > 0) { // if it can land before it crashes
					if (Outgoing.getTakeoffTime() < Incoming.getFuelLevel() - Incoming.getLandingTime()) {
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
		for (IAircraft a : (ACT.getBrokenDown().toArray(new IAircraft[ACT.getBrokenDown().size()]))) { // call the step method for all brokenDown planes;
			a.step();                                                                             // this will cause their maintenance to continue
			if (! a.isBrokeDown()) { // if the aircraft has been fixed
				ACT.getOutgoing().add(a); // add it to the outgoing queue
				ACT.getBrokenDown().remove(a); // and remove it from the broken down queue
			}
			
		}
		
		for (IAircraft a : (ACT.getIncoming().toArray(new IAircraft[ACT.getIncoming().size()]))) {
			a.step();
			if (a.getFuelLevel() - a.getLandingTime() < 0) { // if the plane has fuel enough to land still
				crashed(a);
			} 
		}
		
		for (IAircraft a : (ACT.getOutgoing().toArray(new IAircraft[ACT.getOutgoing().size()]))) {
			a.step();
			if(a.isBrokeDown()) {
				brokeDown(a); // if the aircraft is brokedown call the brokeDown method
			}
		}
		counter.setGrounded(ACT.getOutgoing().size());
		counter.setFlying(ACT.getIncoming().size());
	}
	

	private void takeOff(IAircraft a) {
		counter.incrementTakeoffs();
		counter.incrementWaitingTime(a.getWaitingTime());
		runway.setAvailable(false);
		runway.setOccupiedTime(a.getTakeoffTime());
		ACT.getOutgoing().remove(a);
		if (a instanceof Glider) {
			LightAircraft light = new LightAircraft();
			light.setWaitingTime(a.getWaitingTime());
			ACT.getIncoming().add(light);
			counter.incrementTotalPlanes();
		}
	}

	private void land(IAircraft a) {
		counter.incrementLandings();
		runway.setAvailable(false);
		runway.setOccupiedTime(a.getLandingTime());
		ACT.getIncoming().remove(a);
	}

	private void crashed(IAircraft a) {
		ACT.getIncoming().remove(a);
		counter.incrementCrashes();
		counter.incrementWaitingTime(a.getWaitingTime());
	}

	private void brokeDown(IAircraft a) {
		ACT.getOutgoing().remove(a);
		ACT.getBrokenDown().add(a);
		counter.incrementBreakdowns();
	}

	public AirControlTower getAirControlTower() {
		return ACT;
	}
	
	public Counter getCounter() {
		return counter;
	}
	
	public Hangar getHangar() {
		return hangar;
	}

}