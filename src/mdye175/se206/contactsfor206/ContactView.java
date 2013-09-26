package mdye175.se206.contactsfor206;

import java.io.Serializable;

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
	
	
	
	public void populateContact(View view){
		dataList = (ListView)view.findViewById(R.id.data_list);
		dataList.setAdapter(contactData);
		nameText = ((TextView)view.findViewById(R.id.nameText));
		nameText.setText(contact.getById(ContactDataValue.Parameter.Name).getValue());
		contactData = new ContactDataList(this.getContext(),android.R.layout.simple_list_item_1);
	    ImageView image = (ImageView)view.findViewById(R.id.imageView1);
    	dataList.setFocusable(false);
		
	    if (this.isExpanded()){	
	    	image.setVisibility(VISIBLE);
	    	for (ContactDataValue i:contact){
		    		if (i.getID() != ContactDataValue.Parameter.Name){
		    		TextView newText = new TextView(dataList.getContext());
		    		newText.setText(i.getValue());
		    		newText.setClickable(false);
		    		newText.setFocusable(false);
		    		contactData.add(newText);
		    	}
	    	}
	    }else{
	    	image.setVisibility(INVISIBLE);
	    	if (contact.getCount() > 1){
	    		TextView newText = new TextView(dataList.getContext());
	    		newText.setText(contact.getIndex(1).getValue());
	    		newText.setClickable(false);
	    		newText.setFocusable(false);
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
	
	public void invalidate(){
		nameText.invalidate();
		dataList.invalidate();
		super.invalidate();
	}

}
