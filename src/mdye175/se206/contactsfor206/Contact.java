package mdye175.se206.contactsfor206;

import java.io.Serializable;

import android.widget.TextView;

public class Contact implements Serializable {
	//Dummy data, TODO
	private String name = "";
	private String number = "";
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
	
	
	/**
	 * Takes a ViewContact activity and populates it's display with the data relevant to this contact.
	 * 
	 * @param contact
	 * @return
	 */
	public void populateContact(ViewContact contact){
		TextView nameText = (TextView)contact.findViewById(R.id.nameText);
		TextView numberText = (TextView)contact.findViewById(R.id.numberText);
		TextView emailText = (TextView)contact.findViewById(R.id.emailText);
		
		nameText.setText(name);
		numberText.setText(number);
		emailText.setText(email);
	}
}
