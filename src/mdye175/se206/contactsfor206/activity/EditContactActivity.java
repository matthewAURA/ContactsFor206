package mdye175.se206.contactsfor206.activity;

import java.io.Serializable;

import mdye175.se206.contactsfor206.EditTextArrayAdapter;
import mdye175.se206.contactsfor206.EditTextParameter;
import mdye175.se206.contactsfor206.R;
import mdye175.se206.contactsfor206.R.id;
import mdye175.se206.contactsfor206.R.layout;
import mdye175.se206.contactsfor206.R.menu;
import mdye175.se206.contactsfor206.contact.Contact;
import mdye175.se206.contactsfor206.contact.ContactDataValue;
import mdye175.se206.contactsfor206.contact.ContactDataValue.Parameter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class EditContactActivity extends Activity {
	private Contact contact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
		
		//Get the contact we are displaying from bundle
		Serializable b = getIntent().getSerializableExtra("contact");
		this.contact = (Contact) b;
		
		ListView fieldsList = (ListView)this.findViewById(R.id.fieldsListView);
		//EditTextArrayAdapter editors = new EditTextArrayAdapter(this, android.R.layout.simple_list_item_1,contact);
		//fieldsList.setAdapter(editors);
		
		ArrayAdapter<View> listViews = new ArrayAdapter<View>(this,android.R.layout.simple_list_item_1);
		fieldsList.setAdapter(listViews);
		fieldsList.setFocusable(false);
		fieldsList.setItemsCanFocus(true);
		
		LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		
		//Add in fields
		for (ContactDataValue.Parameter p : ContactDataValue.Parameter.values()){
			View v = inflater.inflate(R.layout.edit_contact_textfield, null);
			listViews.add(v);
			EditText edit = (EditText) v.findViewById(R.id.fieldEditText);
			TextView textView = (TextView) v.findViewById(R.id.fieldTextName);
			
			textView.setText(p.toString());
			
			if (contact.getById(p) != null)
				edit.setText(contact.getById(ContactDataValue.Parameter.FirstName).getValue());
			listViews.notifyDataSetChanged();
		}
	
		//Set up save button and callback
		Button saveButton = (Button)findViewById(R.id.saveButton);
		saveButton.setText("Save");
	
		saveButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				Log.i("contacts onclick",contact.getById(ContactDataValue.Parameter.FirstName).getValue());
			
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
