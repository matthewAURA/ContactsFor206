package mdye175.se206.contactsfor206;

import java.io.Serializable;

public class ContactDataValue implements Comparable<ContactDataValue>,Serializable {
	
	public static enum Parameter implements Serializable{
		Name("name"),PhoneNumber("phonenumber"),Email("email"),Address("address"),Other("other");
		
	    private final String id;
	    Parameter(String id) { this.id = id; }
		
		  public String toString() {
	        return id;
	    }
		  
		  public boolean equals(Parameter other){
			  return this.id.equals(other.id);
		  }
		
	}
	
	ContactDataValue(String value,Parameter id){
		this.value = value;
		this.id = id;
	}
	
	private String value;
	private Parameter id;

	public Parameter getID() {
		return id;
	}
	public void setID(Parameter id){
		this.id = id;
	}
	
	public void setValue(String data) {
		this.value = data;
	}
	public String getValue() {
		return this.value;
	}

	public boolean equals(ContactDataValue other){
		return true;
	}
	
	@Override
	public int compareTo(ContactDataValue another) {
		return this.id.compareTo(another.id);
	}
}
