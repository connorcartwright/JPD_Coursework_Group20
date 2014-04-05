package aston.group20.model;

public class Airport { // //// would it be better to have this as abstract

	private AirControlTower ACT;
	private Runway runway;
	private Hangar hangar;
	private Strategy strategy;

	public Airport() {
		ACT = new AirControlTower(strategy);
		runway = new Runway();
		hangar = new Hangar();
	}

	public void schedule() {
		ACT.step();
		
		if (runway.isAvailable()) {
			IAircraft Incoming = ACT.getIncoming().peek(); // Variable Incoming = the first Incoming Aircraft
			IAircraft Outgoing = ACT.getOutgoing().peek(); // Variable Outgoing = the first Outgoing aircraft 
			
			switch(strategy.schedule(Incoming, Outgoing)) { // 1 for landing, 2 for takeoff, 3 for crash
			
			case 1: land(Incoming);
			break;
				
			case 2: takeOff(Outgoing);
			break;
			
			}
			
		} 
		else {
			runway.incrementTime();
		}
	}

	private void takeOff(IAircraft a) {
		ACT.getCounter().incrementTakeoffs();
		ACT.getCounter().incrementWaitingTime(a.getWaitingTime());
		runway.setAvailable(false);
		runway.setOccupiedTime(a.getTakeoffTime());
		ACT.getOutgoing().remove(a);
		if (a instanceof Glider) {
			LightAircraft light = new LightAircraft();
			light.setWaitingTime(a.getWaitingTime());
			ACT.getIncoming().add(light);
			ACT.getCounter().incrementTotalPlanes();
		}
	}

	private void land(IAircraft a) {
		ACT.getCounter().incrementLandings();
		runway.setAvailable(false);
		runway.setOccupiedTime(a.getLandingTime());
		ACT.getIncoming().remove(a);
	}


	public AirControlTower getACT() {
		return ACT;
	}
	
	public Hangar getHangar() {
		return hangar;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	
}