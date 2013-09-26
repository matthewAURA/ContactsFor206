package mdye175.se206.contactsfor206;

public class NumberComparator implements ContactComparator {

	@Override
	public int compare(ContactView one, ContactView two) {
		return one.getContact().getById(ContactDataValue.Parameter.PhoneNumber).getValue().compareTo(two.getContact().getById(ContactDataValue.Parameter.PhoneNumber).getValue());
	}

	public String toString(){
		return "Sort by numer";
	}
	
}
