package aston.group20.model;

public class FuelStrategy extends Strategy {

	public int compare(Object o1, Object o2) {
		
		Aircraft a1 = (Aircraft) o1;
		Aircraft a2 = (Aircraft) o2;
		
	     return Integer.compare(a1.getFuelLevel(), a2.getFuelLevel());
	}
}
