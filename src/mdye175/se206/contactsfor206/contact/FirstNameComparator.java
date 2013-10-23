package mdye175.se206.contactsfor206.contact;


public class FirstNameComparator implements ContactComparator {
	@Override
	public int compare(ContactView one, ContactView two) {
		try{
			return one.getContact().getById(ContactDataValue.Parameter.LastName).getValue().compareTo(two.getContact().getById(ContactDataValue.Parameter.LastName).getValue());
		}catch (NullPointerException e){
			return 0;
		}
		}
	
	public String toString(){
		return "Sort by name";
	}

}
