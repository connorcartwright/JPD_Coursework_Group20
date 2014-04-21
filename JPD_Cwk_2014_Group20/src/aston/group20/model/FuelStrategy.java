package aston.group20.model;

public class FuelStrategy extends Strategy {

	public int compare(Object o1, Object o2) {
		
		Aircraft a1 = (Aircraft) o1;
		Aircraft a2 = (Aircraft) o2;
		
	     return Integer.compare(a1.getFuelLevel(), a2.getFuelLevel());
	}
	
	public int schedule(IAircraft Incoming, IAircraft Outgoing) { // return 1 = land, return 2 = takeoff, return 0 = nothing;
		if (Incoming == null) {
			if (Outgoing != null) {
				return 2; //takeOff(Outgoing);
			}		 
		}
		else if (Outgoing != null && Outgoing.getWaitingTime() > Incoming.getWaitingTime() &&
				  Outgoing.getTakeoffTime() < Incoming.getFuelLevel() - Incoming.getLandingTime()) {
			return 2; // takeOff(Outgoing);
	    }
		else {
			return 1; // land(Incoming);
				}
		return 0;
		}
}
