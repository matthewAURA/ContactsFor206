package mdye175.se206.contactsfor206;

import java.io.Serializable;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.TextView;

<<<<<<< HEAD
public class Contact extends View implements Serializable {

=======
public class Contact implements Serializable {
	//Dummy data, TODO
	private String name = "";
	private String number = "";
	private String email = "";
>>>>>>> master
	
	public static enum Heights{
		small(100),big(250);
		
	    private final int id;
	    Heights(int id) { this.id = id; }
	    public int getValue() { return id; }
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String number= "";
	private String email = "";
	private boolean isExpanded;
	private int drawHeight = Contact.Heights.small.getValue();
    

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

	public int calcHeight(ViewParent parent){
		Rect r = new Rect();
		parent.getChildVisibleRect(this, r, null);
		return this.drawHeight = r.height();
	}
	
	public void populateCompact(View view){
	    TextView name = (TextView)view.findViewById(R.id.nameText);
	    name.setText(this.name);
	    TextView number = (TextView)view.findViewById(R.id.numberText);
	    number.setText(this.number);
	}
	
	public String toString(){
		return (this.name + "\t" + this.number);
	}
	
	int compareTo(Contact other){
		return this.name.compareTo(other.name);
	}
<<<<<<< HEAD

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

	public boolean isExpanded() {
		return isExpanded;
	}

	public void toggleExpanded() {
		this.isExpanded = !this.isExpanded;
	}
	
	public int getDrawHeight() {

		return drawHeight;
	}

	public void setDrawHeight(int drawHeight) {
		this.drawHeight = drawHeight;
=======
	
	
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
>>>>>>> master
	}
	

	
}
