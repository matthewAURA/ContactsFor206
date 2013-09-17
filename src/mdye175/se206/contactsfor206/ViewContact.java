package mdye175.se206.contactsfor206;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ViewContact extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_contact);
		
		//Get the id that we are displaying
		Bundle b = getIntent().getExtras();
		System.out.println("cheese");
		Contact contact = (Contact) b.getSerializable("contact");
		
		contact.populateContact(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_contact, menu);
		return true;
	}

}
