package mdye175.se206.contactsfor206;

import java.io.Serializable;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditContact extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
		
		Log.i("contacts","activity start");
		
		//Get the id that we are displaying
		Serializable b = getIntent().getSerializableExtra("contact");
		final Contact contact = (Contact) b;
		
		//Set up the data on the page
		final EditText name = (EditText)findViewById(R.id.nameEditText);
		final EditText number = (EditText)findViewById(R.id.numberEditText);
		final EditText email = (EditText)findViewById(R.id.emailEditText);
		
		Button saveButton = (Button)findViewById(R.id.saveButton);
		saveButton.setText("Save");
		name.setText(contact.getName());
		number.setText(contact.getNumber());
		email.setText(contact.getEmail());
		
		saveButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				contact.setName(name.getText().toString());
				contact.setNumber(number.getText().toString());
				contact.setEmail(email.getText().toString());
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
