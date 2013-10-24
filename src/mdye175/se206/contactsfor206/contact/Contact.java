package mdye175.se206.contactsfor206.contact;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class Contact implements Serializable,Iterable<ContactDataValue> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4531245335070638404L;

	private List<ContactDataValue> dataValues;
	private int id;
	private String imageLocation;
	
		
	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public int getId() {
		return id;
	}

	public Contact(int id) {
		this.dataValues = new ArrayList<ContactDataValue>();
		this.id = id;
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
