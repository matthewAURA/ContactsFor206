package mdye175.se206.contactsfor206.activity;

import java.io.FileNotFoundException;
import java.io.InputStream;
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

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class EditContactActivity extends Activity {
	private Contact contact;
	private Activity thisActivity = this;
	private ImageView image;
	private static int STATIC_GET_IMAGE_ID = 1;
	private Bundle savedInstanceState;
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
		
		final ArrayAdapter<EditTextParameter> listViews = new EditTextArrayAdapter(this,android.R.layout.simple_list_item_1, contact);
		fieldsList.setAdapter(listViews);
		fieldsList.setFocusable(false);
		fieldsList.setItemsCanFocus(true);
	
		image = (ImageView) this.findViewById(R.id.imageView1);
		if (contact.getImageLocation() != null){
			setImage(Uri.parse(contact.getImageLocation()));
		}

		image.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(thisActivity, GetImageActivity.class);
				startActivityForResult(intent,EditContactActivity.STATIC_GET_IMAGE_ID);
				
			}
			
		});
		
		
		
		//Add in fields
		for (ContactDataValue.Parameter p : ContactDataValue.Parameter.values()){
			
			String editText = "";
			
			if (contact.getById(p) != null){
				editText = (contact.getById(p).getValue());
			}else{
				contact.addParameter("", p);
			}
			EditTextParameter editTextView = new EditTextParameter(listViews.getContext(), p.toString(), editText, p);
			listViews.add(editTextView);
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
				setResult(Activity.RESULT_OK, intent);
				finish();
			}
			
			
			
		});
		
	}

	private void setImage(Uri file){
		Log.i("Image loading",file.getPath());
		contact.setImageLocation(file.toString());
		 BitmapFactory.Options options = new BitmapFactory.Options();
		 options.inSampleSize = 4;
		 Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(file),null, options);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image.setImageBitmap(bitmap);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == STATIC_GET_IMAGE_ID && resultCode == Activity.RESULT_OK){
			Uri file = data.getData();
			setImage(file);
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_contact, menu);
		return true;
	}
	
	@Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        onCreate(savedInstanceState);
        super.onRestart();
    }


}
