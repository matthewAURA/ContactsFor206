package mdye175.se206.contactsfor206;

import java.io.Serializable;

import android.widget.TextView;

public class Contact implements Serializable {
	//Dummy data, TODO
	private String name;
	private String number;
	private String email;
	
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
	
	private void loadName(){
		
	}
	
	/**
	 * Takes a ViewContact activity and populates it's display with the data relevant to this contact.
	 * 
	 * @param contact
	 * @return
	 */
	public ViewContact populateContact(ViewContact contact){
		TextView nameText = (TextView)contact.findViewById(R.id.nameText);
		TextView numberText = (TextView)contact.findViewById(R.id.numberText);
		nameText.setText(name);
		numberText.setText(number);
		
		
		return null;
	}
}
