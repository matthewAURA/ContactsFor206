package mdye175.se206.contactsfor206;

import java.io.Serializable;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Contact extends View implements Serializable {

	//Dummy data, TODO
	private String name;
	private String number;
	private String email;
	
	
	public Contact(Context context) {
		super(context);
	}

	public Contact(Context context, String name,String number){
		this(context);
		this.name = name;
		this.number = number;
	}
	
	public Contact(Context context, String name,String number,String email){
		this(context,name,number);
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
	public EditContact populateContact(EditContact contact){
		TextView nameText = (TextView)contact.findViewById(R.id.nameText);
		TextView numberText = (TextView)contact.findViewById(R.id.numberText);
		nameText.setText(name);
		numberText.setText(number);
		
		
		return null;
	}
}
