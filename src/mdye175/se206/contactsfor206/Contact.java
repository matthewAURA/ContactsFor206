package mdye175.se206.contactsfor206;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;


public class Contact implements Serializable,Iterable<ContactDataValue> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4531245335070638404L;

	private List<ContactDataValue> dataValues;
	
	public Contact(String name,String number){
		this.dataValues = new ArrayList<ContactDataValue>();
		this.dataValues.add(new ContactDataValue(name,ContactDataValue.Parameter.Name));
		if (number.length() > 0){
			this.dataValues.add(new ContactDataValue(number,ContactDataValue.Parameter.PhoneNumber));
		}
		this.sortData();
	}
	
	public Contact(String name,String number,String email){
		this(name,number);
		if (email.length() > 0){
			this.dataValues.add(new ContactDataValue(email,ContactDataValue.Parameter.Email));
		}
		this.sortData();
	}
	
	public ContactDataValue getById(ContactDataValue.Parameter p){
		for (ContactDataValue i:this.dataValues){
			if (i.getID().equals(p)){
				return i;
			}
		}
		return null;
	}
	
	public int getCount(){
		return this.dataValues.size();
	}
	
	public ContactDataValue getIndex(int index){
		return this.dataValues.get(index);
	}
	
	public void sortData(){
		Collections.sort(dataValues,new DataValueComparator());
	}
	
	@Override
	public Iterator<ContactDataValue> iterator() {
		return dataValues.iterator();
	}
}
