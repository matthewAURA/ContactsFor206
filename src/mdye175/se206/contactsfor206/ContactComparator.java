package mdye175.se206.contactsfor206;

import java.util.Comparator;

public interface ContactComparator extends Comparator<ContactView> {

	@Override
	public int compare(ContactView one, ContactView two);

}
