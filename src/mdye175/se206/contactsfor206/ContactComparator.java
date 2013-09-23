package mdye175.se206.contactsfor206;

import java.util.Comparator;

public interface ContactComparator extends Comparator<Contact> {

	@Override
	public int compare(Contact one, Contact two);

}
