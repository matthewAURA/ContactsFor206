package mdye175.se206.contactsfor206;


import java.io.Serializable;


public class Contact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4531245335070638404L;



	/**
	 * 
	 */
	private String name;
	private String number= "";
	private String email = "";


	
	public Contact(String name,String number){
		this.name = name;
		this.number = number;
	}
	
	public Contact(String name,String number,String email){
		this(name,number);
		this.email = email;
	}

		
	public String toString(){
		return (this.name + "\t" + this.number);
	}
	
	int compareTo(Contact other){
		return this.name.compareTo(other.name);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	

	

	

	
}
