package aston.group20.model;

/**
 * The FuelStrategy class is used to sort the Incoming Aircraft queue in the 
 * AirControlTower class as well as deciding how the Aircraft should be scheduled; the
 * schedule method will be called from the Airport.schedule method.
 * The class will sort the Incoming Aircraft queue based on the fuel level of the Aircraft,
 * which could be seen as the safest option, however it may cause an increased number of crashes
 * due to the fact that it allows Aircraft to takeoff if they can do so without endangering the head of the
 * incoming queue, which is perhaps dangerous for the 2nd/3rd Aircraft in the Incoming queue.
 * 
 * @see Strategy
 *
 * @author Group_20
 * @version 1.0, March 2014
 *
 */
public class FuelStrategy extends Strategy {

	/**
	 * This method is used in order to fulfil the Inheritance of the Strategy superclass.
	 * It is used to sort the Incoming Aircraft queue in the AirControlTower class based on their fuel
	 * level. It makes use of the Integer.compare method in order to shorten the process of comparing
	 * the two Aircraft.
	 * 
	 * @return a negative integer, zero, or a positive integer if the first Aircraft's fuel level is less than, equal to, or greater than the second Aircraft's fuel level.
	 */
	public int compare(Object o1, Object o2) {
		Aircraft a1 = (Aircraft) o1;
		Aircraft a2 = (Aircraft) o2;
	     return Integer.compare(a1.getFuelLevel(), a2.getFuelLevel());
	}
	
	/**
	 * This method is called from the Airport.schedule method and is used to decide what action should be taken
	 * on the Aircraft held in the queues; the two parameters are the heads of the Incoming and Outgoing queues.
	 * The method returns an int which is used in a Switch statement which is used to pick the action that will be
	 * taken.
	 * 
	 * @param Incoming the head of the Incoming queue from the AirControlTower class.
	 * @param Outgoing the head of the Outgoing queue from the AirControlTower class.
	 * @return 1 if the Incoming plane should land, 2 if the Outgoing plane should Takeoff, 0 for no action.
	 * 
	 */
	public int schedule(IAircraft Incoming, IAircraft Outgoing) { // return 1 = land, return 2 = takeoff, return 0 = nothing;
		if (Incoming == null) { // if there isn't a plane waiting to land
			if (Outgoing != null) { // if there is a plane waiting to takeoff
				return 2; // the outgoing Aircraft should take off
			}		 
		}
		else if (Outgoing != null && Outgoing.getWaitingTime() > Incoming.getWaitingTime() && // if there both incoming & outgoing planes, and the outgoing plane has been waiting longer than the head of the incoming queue
				  Outgoing.getTakeoffTime() < Incoming.getFuelLevel() - Incoming.getLandingTime()) { // AND it can takeoff without causing the head of the incoming queue to crash, then let it takeoff
			return 2; // the outgoing Aircraft should take off
	    }
		else {
			return 1; // the incoming Aircraft should land
				}
		return 0; // no action should be taken
		}
}