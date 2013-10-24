package mdye175.se206.contactsfor206.contact.comparator;

import mdye175.se206.contactsfor206.contact.ContactDataValue;
import mdye175.se206.contactsfor206.contact.ContactView;
import mdye175.se206.contactsfor206.contact.ContactDataValue.Parameter;


public class MobileNumberComparator implements ContactComparator {

	@Override
	public int compare(ContactView one, ContactView two) {
		ContactDataValue oneData  = one.getContact().getById(ContactDataValue.Parameter.MobilePhoneNumber);
		ContactDataValue twoData = two.getContact().getById(ContactDataValue.Parameter.MobilePhoneNumber);
		
		if (oneData == null && twoData == null){
			return 0;
		}else if (oneData == null){
			return -1;
		}else if (twoData == null){
			return 1;
		}else{
			return oneData.getValue().compareTo(twoData.getValue());
		}
	}

	public String toString(){
		return "Sort by Mobile Phone Number";
	}
	
}
