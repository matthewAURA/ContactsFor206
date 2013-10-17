package mdye175.se206.contactsfor206.contact;


public class NameComparator implements ContactComparator {
	@Override
	public int compare(ContactView one, ContactView two) {
		return one.getContact().getById(ContactDataValue.Parameter.Name).getValue().compareTo(two.getContact().getById(ContactDataValue.Parameter.Name).getValue());
	}
	
	public String toString(){
		return "Sort by name";
	}

}
