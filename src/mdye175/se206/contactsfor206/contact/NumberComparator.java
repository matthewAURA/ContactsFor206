package mdye175.se206.contactsfor206.contact;


public class NumberComparator implements ContactComparator {

	@Override
	public int compare(ContactView one, ContactView two) {
		ContactDataValue oneData  = one.getContact().getById(ContactDataValue.Parameter.PhoneNumber);
		ContactDataValue twoData = two.getContact().getById(ContactDataValue.Parameter.PhoneNumber);
		
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
		return "Sort by number";
	}
	
}
