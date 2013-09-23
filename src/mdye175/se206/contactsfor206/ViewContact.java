package mdye175.se206.contactsfor206;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ViewContact extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_view);
		
		//Get the id that we are displaying
		Bundle b = getIntent().getExtras();
		Contact contact = (Contact) b.getSerializable("contact");
		contact.populateContact(this);
	
		//TextView nameText = (TextView)this.findViewById(R.id.nameText);
		final TextView numberText = (TextView)this.findViewById(R.id.numberText);
		//TextView emailText = (TextView)this.findViewById(R.id.emailText);
	
		
		numberText.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				
				String url = "tel:"+numberText.getText();
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
				startActivity(intent);
				
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
