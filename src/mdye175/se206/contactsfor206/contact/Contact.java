package mdye175.se206.contactsfor206.contact;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import mdye175.se206.contactsfor206.ContactDataValueComparator;


public class Contact implements Serializable,Iterable<ContactDataValue> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4531245335070638404L;

	private List<ContactDataValue> dataValues;
	private int id;
	
	public Contact(String name,String number){
		//Set up list to store the data associated with this contact (name, phonenumber etc)
		this.dataValues = new ArrayList<ContactDataValue>();
		this.dataValues.add(new ContactDataValue(name,ContactDataValue.Parameter.Name));
		
		//Only add the phone number if it is valid (i.e. not an empty string)
		if (number.length() > 0){
			this.dataValues.add(new ContactDataValue(number,ContactDataValue.Parameter.PhoneNumber));
		}
		this.sortData();
		this.id = this.hashCode();
	}
	
	public Contact(String name,String number,String email){
		this(name,number);
		if (email.length() > 0){
			this.dataValues.add(new ContactDataValue(email,ContactDataValue.Parameter.Email));
		}
		this.sortData();
	}
	
	public Contact(String name,String number,String email,String address){
		this(name,number,email);
		if (address.length() > 0){
			this.dataValues.add(new ContactDataValue(email,ContactDataValue.Parameter.Address));
		}
		this.sortData();
	}
	
	public Contact() {
		// TODO Auto-generated constructor stub
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
	
	public void addParameter(String value,ContactDataValue.Parameter p){
		this.dataValues.add(new ContactDataValue(value, p));
		this.sortData();
		
	}
	
	public void sortData(){
		Collections.sort(dataValues,new ContactDataValueComparator());
	}
	
	@Override
	public Iterator<ContactDataValue> iterator() {
		return dataValues.iterator();
	}
	
	public boolean equals(Object other){
		if (other instanceof Contact){
			return this.id==((Contact)other).id;
		}else{
			return false;
		}
	}
}
