package aston.group20.model;

/**
 * The Airport class is used to model an Airport; it has a Runway that planes can land on/takeoff from,
 * it has a Hangar where Aircrafts are stored(/created in this Simulation) and an Air Control Tower which holds/
 * does the more autonomous management of the Aircraft queues.
 * 
 * The Airport class manages the front-end actions of the Aircraft, including how to schedule them, as well as
 * when they should takeoff/land; it makes use of the @Strategy class in order to decide how to do this.
 * takes a Strategy as to how to handle the Incoming/Outgoing Aircraft.
 * 
 * @author Group_20
 * @version 1.0, March 2014
 * 
 */
public class Airport { 

	private AirControlTower ACT; // the Airports AirControlTower which holds the queues
	private Runway runway; // the Airports runway which allow planes to takeoff/land
	private Hangar hangar; // the Airports hangar which creates Aircraft
	private Strategy strategy; // the Airports strategy which decides how to deal with Aircraft

    /**
     * Creating a new <code>Airport</code>, which will initialise the AirControlTower, Runway, and Hangar.
     * All of these things will be used throughout the Airports lifetime in order to manage/control the Aircraft.
     * and set the strategy for how to manage the Incoming aircraft.
     */
	public Airport() {
		ACT = new AirControlTower(strategy);
		runway = new Runway();
		hangar = new Hangar();
	}

	/**
	 * This method is the primary method that is called from the Simulator class; it calls the AirControlTower 
	 * 'step' method that will iterate through the queues and ensure that the Aircraft's states are all 
	 * up to date. After doing this, the method will check whether the Runway is available for a landing or
	 * takeoff; if it is the strategy 'schedule' method will be called to decide which action should be taken - 
	 * whether the plane should @takeOff or @land .
	 * If the Runway wasn't available, the method will increment the Runway's time.
	 * 
	 * @see AirControlTower#step()
	 * @see Strategy#schedule(IAircraft, IAircraft)
	 */
	public void schedule() {
		ACT.step();
		
		if (runway.isAvailable()) {
			IAircraft Incoming = ACT.getIncoming().peek(); // Variable Incoming = the first Incoming Aircraft
			IAircraft Outgoing = ACT.getOutgoing().peek(); // Variable Outgoing = the first Outgoing aircraft 
			
			switch(strategy.schedule(Incoming, Outgoing)) { // 1 for landing, 2 for takeoff
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

	/**
	 * This method is used when a plane is ready to takeoff; it is called from the schedule method if the strategy
	 * has decided that a plane should takeoff. The method increments the appropriate fields in the AirControlTower's
	 * Counter and will ensure that the Runway's state is updated so that it is shown to be occupied.
	 * The method also has a chance to create a new Aircraft, if the parameter Aircraft is an instance of Glider.
	 * 
	 * @see #schedule()
	 * @param a the Aircraft that will be taking off.
	 */
	private void takeOff(IAircraft a) {
		ACT.getCounter().incrementTakeoffs();
		ACT.getCounter().incrementWaitingTime(a.getWaitingTime());
		runway.setAvailable(false);
		runway.setOccupiedTime(a.getTakeoffTime());
		ACT.getOutgoing().remove(a);
		if (a instanceof Glider) {
			IAircraft light = hangar.generateLightAircraft();
			light.setWaitingTime(a.getWaitingTime());
			ACT.getIncoming().add(light);
			ACT.getCounter().incrementTotalPlanes();
		}
	}

	/**
	 * This method is used when a plane is ready to land; it is called from the schedule method if the strategy 
	 * has decided that a plane should land. The method increments the appropriate fields in the AirControlTower's
	 * Counter and will ensure that the Runway's state is updated so that it is shown to be occupied.
	 * 
	 * @param a the Aircraft that will be landing.
	 */
	private void land(IAircraft a) {
		ACT.getCounter().incrementLandings();
		runway.setAvailable(false);
		runway.setOccupiedTime(a.getLandingTime());
		ACT.getIncoming().remove(a);
	}

	/**
	 * This method returns the AirControlTower belonging to this Airport.
	 * @return the AirControlTower belonging to this Airport.
	 */
	public AirControlTower getACT() {
		return ACT;
	}
	
	/**
	 * This method returns the Hangar belonging to this Airport.
	 * @return returns the Hangar belonging to this Airport.
	 */
	public Hangar getHangar() {
		return hangar;
	}

	/**
	 * This method is used to set the strategy that will be used to determine how the Aircraft should be handled.
	 * @param strategy the strategy that will determine how the Aircraft at this Airport should be handled.
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public void reset() {
		ACT.clear();
		runway.reset();
	}
	
}