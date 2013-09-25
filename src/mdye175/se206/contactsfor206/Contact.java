package mdye175.se206.contactsfor206;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class Contact extends View implements Serializable {

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
	private NameProperty name;
	private PhoneNumberProperty number;
	private EmailProperty email;
	private boolean isExpanded;
	private int drawHeight = Contact.Heights.small.getValue();
    

	public Contact(Context context) {
		super(context);
	}

	public Contact(Context context, NameProperty name,PhoneNumberProperty number){
		this(context);
		this.name = name;
		this.number = number;
	}
	
	public Contact(Context context, NameProperty name,PhoneNumberProperty number,EmailProperty email){
		this(context,name,number);
		this.email = email;
	}


	public void populateCompact(View view){
	    TextView name = (TextView)view.findViewById(R.id.nameText);
	    ListView details = (ListView)view.findViewById(R.id.listView1);
	    ImageView image = (ImageView)view.findViewById(R.id.imageView1);
	    //Always display name as contacts must have a name
	    name.setText(this.getName());


	    //Add fields to listview if not null
	    if (this.getName().length() > 0){
	    	details.addView(name);
	    }
	    if (this.getEmail().length() > 0){
	    	details.addView(this.number);
	    }
	    
	    
	    if (this.isExpanded){
	    	image.setVisibility(View.VISIBLE);
	    }else{
	    	image.setVisibility(View.GONE);
	    }
	    
	}
	
	public String toString(){
		return (this.name + "\t" + this.number);
	}
	
	int compareTo(Contact other){
		return this.name.compareTo(other.name);
	}


	public String getName() {
		return name.toString();
	}

	public void setName(String name) {
		this.name = new NameProperty(this.getContext(), name, "name");
	}

	public String getNumber() {
		return number.toString();
	}

	public void setNumber(String number) {
		this.number = new PhoneNumberProperty(this.getContext(), number, "number");
	}

	public String getEmail() {
		return email.toString();
	}

	public void setEmail(String email) {
		this.email = new EmailProperty(this.getContext(), email, "email");
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
	}
	

	
}
