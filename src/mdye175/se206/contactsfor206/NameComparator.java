package mdye175.se206.contactsfor206;

public class NameComparator implements ContactComparator {
	@Override
	public int compare(Contact one, Contact two) {
		return one.getName().compareTo(two.getName());
	}
	
	public String toString(){
		return "Sort by name";
	}

}
