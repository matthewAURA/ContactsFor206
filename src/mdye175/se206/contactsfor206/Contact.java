package mdye175.se206.contactsfor206;

public class Contact {
	//Dummy data, TODO
	private String name;
	private String number;
	public Contact(String name,String number){
		this.name = name;
		this.number = number;
	}
	
	public String toString(){
		return (this.name + "\t" + this.number);
	}
	
	int compareTo(Contact other){
		return this.name.compareTo(other.name);
	}
	
	private void loadName(){
		
		
	}
}
