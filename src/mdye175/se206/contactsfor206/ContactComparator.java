package mdye175.se206.contactsfor206;

import java.util.Comparator;

public class ContactComparator implements Comparator {

	@Override
	public int compare(Object arg0, Object arg1) {
		Contact one,two;
		one = (Contact)arg0;
		two = (Contact)arg1;
		return one.compareTo(two);
	}

}
