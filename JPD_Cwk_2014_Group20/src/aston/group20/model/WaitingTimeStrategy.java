package aston.group20.model;

public class WaitingTimeStrategy extends Strategy {

	public int compare(Object o1, Object o2) {
		
		Aircraft a1 = (Aircraft) o1;
		Aircraft a2 = (Aircraft) o2;
		
	     return Integer.compare(a1.getWaitingTime(), a2.getWaitingTime());
	}
	
	public int schedule(IAircraft Incoming, IAircraft Outgoing) { // return 1 = land, return 2 = takeoff, return 3 = crashed, return 0 = nothing;
		
		if (Incoming != null) { // if there's a plane waiting to land
			return 1; // land
		} 
		else if (Outgoing != null) { // else, if there's a plane waiting to takeoff, then let it takeoff
			return 2; // takeoff
		}
		else {
			return 0;
		}
}
	
}
