package mdye175.se206.contactsfor206.contact;

import java.io.Serializable;

import mdye175.se206.contactsfor206.R;
import mdye175.se206.contactsfor206.R.id;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class ContactView extends View implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3961310794759469719L;
	private Contact contact;
	private int drawHeight = ContactView.Heights.small.getValue();
	private boolean isExpanded;
	private ListView dataList;
	private ContactDataList contactData;
	private TextView nameText;
	//Ugh - simple hack to make animations nice - hopefully this would be smarter soon, using MATCH_PARENT
	//to nicely fit to view, but this causes some derpyness with animations
	public static enum Heights{
		small(100),big(250);
		
	    private final int id;
	    Heights(int id) { this.id = id; }
	    public int getValue() { return id; }
	}
	
	public ContactView(Context context,Contact contact) {
		super(context);
		this.contact = contact;
	}
	
	
	//Long onibus method - again a little messy, 
	//Populates a view with the data from the assigned contact to this object.
	//Potentially link this class to an interface to have different ways of drawing one contact.
	public void populateContact(View view){
		dataList = (ListView)view.findViewById(R.id.data_list);
		dataList.setAdapter(contactData);
		nameText = ((TextView)view.findViewById(R.id.nameText));
		nameText.setText(contact.getById(ContactDataValue.Parameter.Name).getValue());
		contactData = new ContactDataList(this.getContext(),android.R.layout.simple_list_item_1);
	    ImageView image = (ImageView)view.findViewById(R.id.imageView1);
	    
	    //I can't for the life of me make the listview elements not clickable - they keep eating the onTouch() objects.
	    //there is a way, just TODO
    	dataList.setFocusable(false);
		
    	
    	//Making things invisible when not expanded, potentially a performance hit - something to look at later.
	    if (this.isExpanded()){	
	    	image.setVisibility(VISIBLE);
	    	for (ContactDataValue i:contact){
		    		if (i.getID() != ContactDataValue.Parameter.Name){
		    		TextView newText = new TextView(dataList.getContext());
		    		newText.setText(i.getValue());
		    		contactData.add(newText);
		    	}
	    	}
	    }else{
	    	image.setVisibility(INVISIBLE);
	    	if (contact.getCount() > 1){
	    		TextView newText = new TextView(dataList.getContext());
	    		newText.setText(contact.getIndex(1).getValue());
	    		contactData.add(newText);
	    	}
	    	
	    }	    
	}
	
	public boolean equals(Object other){
		if (other instanceof Contact){
			return this.contact.equals(other);
		}else{
			return false;
		}
	}
	

	public Contact getContact(){
		return contact;
	}
	
	public void setContact(Contact contact){
		this.contact = contact;
	}

	
	public int getDrawHeight() {
		return drawHeight;
	}

	public void setDrawHeight(int drawHeight) {
		this.drawHeight = drawHeight;
	}
	
	public boolean isExpanded() {
		return isExpanded;
	}

	public void toggleExpanded() {
		this.isExpanded = !this.isExpanded;
	}
	
	
	//Some issues earlier with views not being correctly drawn when contact data updated, tried to invalidate all children.
	public void invalidate(){
		nameText.invalidate();
		dataList.invalidate();
		super.invalidate();
	}

}
