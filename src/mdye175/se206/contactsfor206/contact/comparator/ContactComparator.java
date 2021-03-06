package mdye175.se206.contactsfor206.contact.comparator;

import java.util.Comparator;

import mdye175.se206.contactsfor206.contact.ContactView;

/**
 *Standard Interface to unify comparators of contacts objects, so they can be sorted in different ways 
 * 
 * @author Matthew
 *
 */
public interface ContactComparator extends Comparator<ContactView> {

	@Override
	public int compare(ContactView one, ContactView two);

}
