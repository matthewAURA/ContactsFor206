package mdye175.se206.contactsfor206;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactView extends View {

	private Contact contact;
	
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
	    TextView nameTextView = (TextView)view.findViewById(R.id.nameText);
	    nameTextView.setText(contact.getName());
	    TextView numberTextView = (TextView)view.findViewById(R.id.numberText);
	    numberTextView.setText(contact.getNumber());
	    TextView emailTextView = (TextView)view.findViewById(R.id.emailText);
	    ImageView image = (ImageView)view.findViewById(R.id.imageView1);
	    Button button1 = (Button)view.findViewById(R.id.button1);
	    Button button2 = (Button)view.findViewById(R.id.button2);
	    button1.setFocusable(false);
    	button2.setFocusable(false);
    	
    	//Set up callbacks for edit button
    	button1.setText("Edit");
    	button1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(ContactView.this.getContext(), EditContact.class);
				Bundle b = new Bundle();
				b.putSerializable("contact",contact);
				intent.putExtras(b);
				ContactView.this.getContext().startActivity(intent);
			}
    		
    	});
    	

	    
	    emailTextView.setText(contact.getEmail());
	    numberTextView.setVisibility(View.VISIBLE);
	    
	    if (contact.getEmail().length() < 1){
	    	emailTextView.setVisibility(View.GONE);
	    }
	    if (contact.getNumber().length() < 1){
	    	numberTextView.setVisibility(View.GONE);
	    }
	    
	    if (contact.isExpanded()){	
	    	emailTextView.setVisibility(View.VISIBLE);
	    	image.setVisibility(View.VISIBLE);
	    	button1.setVisibility(View.VISIBLE);
	    	button2.setVisibility(View.VISIBLE);
	    }else{
	    	button1.setVisibility(View.INVISIBLE);
	    	button2.setVisibility(View.INVISIBLE);
	    	emailTextView.setVisibility(View.GONE);
	    	image.setVisibility(View.GONE);
	    }
	    
	}

	public Contact getContact(){
		return contact;
	}

}
