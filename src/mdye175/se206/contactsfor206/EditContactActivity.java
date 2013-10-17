package mdye175.se206.contactsfor206;

import java.io.Serializable;

import mdye175.se206.contactsfor206.contact.Contact;
import mdye175.se206.contactsfor206.contact.ContactDataValue;
import mdye175.se206.contactsfor206.contact.ContactDataValue.Parameter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class EditContactActivity extends Activity {

	private EditText nameField;
	private EditText phoneNumberField;
	private EditText emailField;
	private EditText addressField;
	private Contact contact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
		
		//Get the contact we are displaying from bundle
		Serializable b = getIntent().getSerializableExtra("contact");
		this.contact = (Contact) b;
		
		ListView fieldsList = (ListView)this.findViewById(R.id.fieldsListView);
		EditTextArrayAdapter editors = new EditTextArrayAdapter(this, android.R.layout.simple_list_item_1,contact);
		fieldsList.setAdapter(editors);
		
		fieldsList.setFocusable(false);
		fieldsList.setItemsCanFocus(true);
		
		//Add in default fields
		nameField = new EditText(fieldsList.getContext());
		phoneNumberField = new EditText(fieldsList.getContext());
		emailField = new EditText(fieldsList.getContext());
		addressField = new EditText(fieldsList.getContext());
		
		editors.add(new EditTextParameter(nameField,ContactDataValue.Parameter.Name));
		editors.add(new EditTextParameter(phoneNumberField,ContactDataValue.Parameter.PhoneNumber));
		editors.add(new EditTextParameter(emailField,ContactDataValue.Parameter.Email));
		editors.add(new EditTextParameter(addressField,ContactDataValue.Parameter.Address));
		
		
		//Set up the data on the page
		if (contact.getById(ContactDataValue.Parameter.Name) != null)
			nameField.setText(contact.getById(ContactDataValue.Parameter.Name).getValue());
		if (contact.getById(ContactDataValue.Parameter.PhoneNumber) != null)
			phoneNumberField.setText(contact.getById(ContactDataValue.Parameter.PhoneNumber).getValue());
		if (contact.getById(ContactDataValue.Parameter.Email) != null)
			emailField.setText(contact.getById(ContactDataValue.Parameter.Email).getValue());
		if (contact.getById(ContactDataValue.Parameter.Address) != null)
			addressField.setText(contact.getById(ContactDataValue.Parameter.Address).getValue());
		
		//Set up save button and callback
		Button saveButton = (Button)findViewById(R.id.saveButton);
		saveButton.setText("Save");
	
		saveButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				Log.i("contacts onclick",contact.getById(ContactDataValue.Parameter.Name).getValue());
			
				Intent intent = new Intent();
				Bundle b = new Bundle();
				b.putSerializable("contact", contact);
				intent.putExtras(b);
				intent.putExtra("delete", contact.hashCode());
				setResult(Activity.RESULT_OK, intent);
				finish();
			}
			
			
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_contact, menu);
		return true;
	}
	
	public void finish(){
		super.finish();
	}


}
