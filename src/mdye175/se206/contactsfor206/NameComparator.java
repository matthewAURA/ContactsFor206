package mdye175.se206.contactsfor206;

public class NameComparator implements ContactComparator {
	@Override
	public int compare(ContactView one, ContactView two) {
		return one.getContact().getName().compareTo(two.getContact().getName());
	}
	
	public String toString(){
		return "Sort by name";
	}

}
