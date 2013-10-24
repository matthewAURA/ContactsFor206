package mdye175.se206.contactsfor206.contact.comparator;

import mdye175.se206.contactsfor206.contact.ContactDataValue;
import mdye175.se206.contactsfor206.contact.ContactView;
import mdye175.se206.contactsfor206.contact.ContactDataValue.Parameter;


public class FirstNameComparator implements ContactComparator {
	@Override
	public int compare(ContactView one, ContactView two) {
		try{
			return one.getContact().getById(ContactDataValue.Parameter.FirstName).getValue().compareTo(two.getContact().getById(ContactDataValue.Parameter.FirstName).getValue());
		}catch (NullPointerException e){
			return 0;
		}
		}
	
	public String toString(){
		return "Sort by First Name";
	}

}
