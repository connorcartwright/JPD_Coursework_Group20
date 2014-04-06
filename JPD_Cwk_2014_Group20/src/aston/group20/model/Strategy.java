package aston.group20.model;
import java.util.Comparator;

/**
* The Strategy class is used to define how aircrafts should be sorted 
* as well as how they should land and take off 
*
* @author Group_20
* @version 1.0, March 2014
*
*/

public abstract class Strategy implements Comparator<Object> {

	public abstract int compare(Object o1, Object o2);

	
	public abstract int schedule(IAircraft Incoming, IAircraft Outgoing);
}
