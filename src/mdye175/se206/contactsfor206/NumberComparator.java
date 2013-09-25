package mdye175.se206.contactsfor206;

public class NumberComparator implements ContactComparator {

	@Override
	public int compare(Contact one, Contact two) {
		return one.getNumber().compareTo(two.getNumber());
	}

	public String toString(){
		return "Sort by numer";
	}
	
}
