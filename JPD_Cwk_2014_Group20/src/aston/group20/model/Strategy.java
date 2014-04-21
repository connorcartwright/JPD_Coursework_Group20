package aston.group20.model;
import java.util.Comparator;

/**
 * The Strategy class is used to sort the Incoming Aircraft queue in the AirControlTower
 * class, as well as deciding how the Aircraft should be scheduled.
 * 
 * The concrete subclasses complete the method implementation, allowing for an endless
 * number of ways with which to sort the Incoming queue and to schedule the Aircraft;
 * it allows for easy future expansion.
 *
 * @author Group_20
 * @version 1.0, March 2014
 *
 */

public abstract class Strategy implements Comparator<Object> {

	/**
	 * This method is used to sort the Incoming Aircraft queue in the AirControlTower class based on a specified
	 * integer value, defined by the concrete subclasses, which will likely make use of the Integer.compare method 
	 * in order to shorten the process of comparing the two Aircraft objects.
	 * 
	 * @return a negative integer, zero, or a positive integer if the first Aircraft's chosen field is less than, equal to, or greater than the second Aircraft's chosen field.
	 */
	public abstract int compare(Object o1, Object o2);

	/**
	 * This method is called from the Airport.schedule method and is used to decide what action should be taken
	 * on the Aircraft held in the queues; the two parameters are the heads of the Incoming and Outgoing queues.
	 * The method returns an int which is used in a Switch statement which is used to pick the action that will be
	 * taken.
	 * 
	 * The method implementation is completed by the concrete subclasses, each of which decide how to deal with
	 * scheduling the Aircrafts.
	 * 
	 * @param Incoming the head of the Incoming queue from the AirControlTower class.
	 * @param Outgoing the head of the Outgoing queue from the AirControlTower class.
	 * @return 1 if the Incoming plane should land, 2 if the Outgoing plane should Takeoff, 0 for no action.
	 * 
	 */
	public abstract int schedule(IAircraft Incoming, IAircraft Outgoing);
}
