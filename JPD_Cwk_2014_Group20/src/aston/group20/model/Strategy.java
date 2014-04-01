package aston.group20.model;
import java.util.Comparator;

public abstract class Strategy implements Comparator<Object> {

	public abstract int compare(Object o1, Object o2);
	
}
