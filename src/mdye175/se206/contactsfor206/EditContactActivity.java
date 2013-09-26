package mdye175.se206.contactsfor206;

import java.io.Serializable;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class EditContactActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
		
		ListView fieldsList = (ListView)this.findViewById(R.id.fieldsListView);
		EditTextArrayAdapter editors = new EditTextArrayAdapter(this, android.R.layout.simple_list_item_1);
		fieldsList.setAdapter(editors);
		
		
		//Add in default fields
		EditText nameField = new EditText(fieldsList.getContext());
		EditText phoneNumberField = new EditText(fieldsList.getContext());
		EditText emailField = new EditText(fieldsList.getContext());
		EditText addressField = new EditText(fieldsList.getContext());
		nameField.setClickable(true);
		nameField.setFocusable(true);
		editors.add(nameField);
		editors.add(phoneNumberField);
		editors.add(emailField);
		editors.add(addressField);
		
		fieldsList.setFocusable(false);
		fieldsList.setOnItemClickListener(new OnItemClickListener(){
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.i("contacts","ListView Call "  + arg0.toString());	
				
			}
			
		});
		//Get the id that we are displaying
		Serializable b = getIntent().getSerializableExtra("contact");
		final Contact contact = (Contact) b;
		
		//Set up the data on the page
		
		if (contact.getById(ContactDataValue.Parameter.Name) != null)
			nameField.setText(contact.getById(ContactDataValue.Parameter.Name).getValue());
		if (contact.getById(ContactDataValue.Parameter.PhoneNumber) != null)
			phoneNumberField.setText(contact.getById(ContactDataValue.Parameter.PhoneNumber).getValue());
		if (contact.getById(ContactDataValue.Parameter.Email) != null)
			emailField.setText(contact.getById(ContactDataValue.Parameter.Email).getValue());
		if (contact.getById(ContactDataValue.Parameter.Address) != null)
			addressField.setText(contact.getById(ContactDataValue.Parameter.Address).getValue());
		
	
		
		Button saveButton = (Button)findViewById(R.id.saveButton);
		saveButton.setText("Save");
	
		saveButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				Bundle b = new Bundle();
				b.putSerializable("contact", contact);
				intent.putExtras(b);
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
	

}
