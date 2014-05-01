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
		strategy = new WaitingTimeStrategy();
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
		generateAircraft();
		ACT.step();
		
		if (runway.isAvailable()) {
			IAircraft Incoming = ACT.getIncoming().peek(); // Variable Incoming = the first Incoming Aircraft
			IAircraft Outgoing = ACT.getOutgoing().peek(); // Variable Outgoing = the first Outgoing aircraft 
			
			switch(strategy.schedule(Incoming, Outgoing)) { // 1 for landing, 2 for takeoff
			case 0: // do nothing
				break;
			
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
	 * This method has the potential to add an Aircraft to either the Incoming or Outgoing queues of the
	 * Airports AirControlTower. It calls the Hangar's generateAircraft method which could return an Aircraft
	 * that would then be added to one of the queues, or it could return null, depending on the random number
	 * generator.
	 * 
	 * @see Hangar#generateAircraft()
	 */
	private void generateAircraft() {
		IAircraft aircraft = hangar.generateAircraft(); // has a chance of generating an aircraft
		if (aircraft != null) { // if an aircraft was generated
			ACT.getCounter().incrementTotalPlanes(); // increment the total no. of planes
			
			if (aircraft instanceof CommercialAircraft) {
				ACT.getCounter().incrementCommercialAircraft(); // increment to show a Commercial Aircraft has been generated
			}
			else if (aircraft instanceof Glider) {
				ACT.getCounter().incrementGlider(); // increment to show a Glider has been generated
			}
			else if (aircraft instanceof LightAircraft) {
				ACT.getCounter().incrementLightAircraft(); // increment to show a Light Aircraft has been generated
			}
			else {
				// could add a new Aircraft type here
			}
			
			if(aircraft.isFlying()) {
				ACT.getIncoming().add(aircraft); // 50% chance to by flying
			}
			else {
				ACT.getOutgoing().add(aircraft); // 50% chance to be grounded
			}
		}
		// else do nothing if an Aircraft wasn't generated
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
	 * @param strategyNumber the number indicating the strategy that will determine how the Aircraft at this 
	 * Airport should be handled; see the GUI's combo box for ordering.
	 * 
	 * @see aston.group20.GUIview.GUI
	 */
	public void setStrategy(int strategyNumber) {
		switch(strategyNumber) {
		case 0: strategy = new WaitingTimeStrategy();
			break;
		case 1: strategy = new FuelStrategy();
			break;
		case 2: // new strategy can be implemented here and so on
			break;
		}
	}
	
	/**
	 * This method ensures that all the components of the Airport that need to be reset/cleared
	 * are reset/cleared. It is used in order to ensure consistency between Simulations, so that 
	 * there are no leftover values/settings between Simulations.
	 */
	public void reset() {
		ACT.clear();
		runway.reset();
	}
	
}