package mdye175.se206.contactsfor206;

public class NumberComparator implements ContactComparator {

	@Override
	public int compare(ContactView one, ContactView two) {
		return one.getContact().getNumber().compareTo(two.getContact().getNumber());
	}

	public String toString(){
		return "Sort by numer";
	}
	
}
