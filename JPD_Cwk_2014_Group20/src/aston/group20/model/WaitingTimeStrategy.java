package aston.group20.model;

/**
 * The WaitingTimeStrategy class is used to sort the Incoming Aircraft queue in the 
 * AirControlTower class as well as deciding how the Aircraft should be scheduled; the
 * schedule method will be called from the Airport.schedule method.
 * The class will sort the Incoming Aircraft queue based on the waiting time of the Aircraft,
 * which could be seen as their Natural Ordering.
 * 
 * @see Strategy
 *
 * @author Group_20
 * @version 1.0, March 2014
 *
 */
public class WaitingTimeStrategy extends Strategy {

	/**
	 * This method is used in order to fulfil the Inheritance of the Strategy superclass.
	 * It is used to sort the Incoming Aircraft queue in the AirControlTower class based on their waiting
	 * time. It makes use of the Integer.compare method in order to shorten the process of comparing
	 * the two Aircraft.
	 * 
	 * @return a negative integer, zero, or a positive integer if the first Aircraft's waiting time is less than, equal to, or greater than the second Aircraft's waiting time.
	 */
	public int compare(Object o1, Object o2) {
		
		Aircraft a1 = (Aircraft) o1;
		Aircraft a2 = (Aircraft) o2;
		
	     return Integer.compare(a1.getWaitingTime(), a2.getWaitingTime());
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
		
		if (Incoming != null) { // if there's a plane waiting to land
			return 1; // let the Incoming plane land
		} 
		else if (Outgoing != null) { // else, if there's a plane waiting to takeoff, then let it takeoff
			return 2; // let the Outgoing plane takeoff
		}
		else { // if there are no planes waiting to land or takeoff, then do nothing
			return 0;
		}
}
	
}
