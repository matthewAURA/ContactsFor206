package mdye175.se206.contactsfor206;

import java.util.Comparator;

public class DataValueComparator implements Comparator<ContactDataValue>{


	
	private int getPriority(ContactDataValue.Parameter p){
		switch (p){
			case Name:
				return 1;
			case PhoneNumber:
				return 2;
			case Email:
				return 3;
			case Address:
				return 4;
			case Other:
				return 5;
			default:
				return 6;
		}
	}
	
	@Override
	public int compare(ContactDataValue one, ContactDataValue two) {
		ContactDataValue.Parameter p0 = one.getID();
		ContactDataValue.Parameter p1 = two.getID();
		int pOne = getPriority(p0);
		int pTwo = getPriority(p1);
		return pOne > pTwo? pOne : pTwo;
				
	}


}
