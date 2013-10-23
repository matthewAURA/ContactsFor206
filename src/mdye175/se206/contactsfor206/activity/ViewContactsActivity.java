package mdye175.se206.contactsfor206.activity;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mdye175.se206.contactsfor206.ContactsArrayAdapter;
import mdye175.se206.contactsfor206.R;
import mdye175.se206.contactsfor206.SortMethodList;
import mdye175.se206.contactsfor206.contact.Contact;
import mdye175.se206.contactsfor206.contact.ContactDataValue;
import mdye175.se206.contactsfor206.contact.ContactView;
import mdye175.se206.contactsfor206.contact.FirstNameComparator;
import mdye175.se206.contactsfor206.contact.NumberComparator;
import mdye175.se206.contactsfor206.database.AddContactOperation;
import mdye175.se206.contactsfor206.database.ContactDataBase;
import mdye175.se206.contactsfor206.database.ReadContactsOperation;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;


public class ViewContactsActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener, AnimatorUpdateListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	public static final String dbName = "ContactsDataBase.db";
	private ContactsArrayAdapter contacts;
	private ListView viewContacts;
	private AnimatorUpdateListener update = this;
	private SortMethodList sort;
	private ContactView activeEditContact;
	private static final int STATIC_EDIT_CONTACT_IDENTIFIER = 32414;
	private ContactDataBase database;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		setContentView(R.layout.activity_view_contacts);
		viewContacts = (ListView)findViewById(R.id.listView1);
		sort = new SortMethodList(this,android.R.layout.simple_list_item_1);
		
		viewContacts.setItemsCanFocus(false);
		
		//Create Database
		this.database = new ContactDataBase(this.getApplicationContext(), dbName, null, 1);
		
		//Add a test contact to the db;
		Contact testContact = new Contact();
		for (ContactDataValue.Parameter p: ContactDataValue.Parameter.values()){
			testContact.addParameter(p.toString(),p);
		}
		AddContactOperation debugAdd = new AddContactOperation(testContact);
		database.execute(debugAdd);
		
		try {
			database.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//For now, just print out what is in the database to the console window
		ReadContactsOperation debug = new ReadContactsOperation();
		List<Contact> results;
		results = debug.getResults();
		for (Contact c: results){
			Log.i("contacts",c.toString());
		}
		
		//Set up contacts objects
		contacts = new ContactsArrayAdapter(this, android.R.layout.simple_list_item_1);
		viewContacts.setAdapter(contacts);
		contacts.add(new ContactView(viewContacts.getContext(),new Contact("Tom","34 2135 243534")));
		contacts.add(new ContactView(viewContacts.getContext(),new Contact("Bob","13243 31145")));
		contacts.add(new ContactView(viewContacts.getContext(),new Contact("Bill","1750 51351 51")));
		contacts.add(new ContactView(viewContacts.getContext(),new Contact("Alice","4141 565 675","alice@gmail.com")));
		contacts.add(new ContactView(viewContacts.getContext(),new Contact("Frank","51564742")));
		contacts.add(new ContactView(viewContacts.getContext(),new Contact("Joe","","joe@meat.com")));

			
		//Set up a listener to change view when a contact is selected
		viewContacts.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long id) {
				final ContactView contact = ((ContactView)arg0.getItemAtPosition(arg2));
				ObjectAnimator anim;
				if (!contact.isExpanded()){
				//Animate the item to make it open
					anim = ObjectAnimator.ofInt(contact, "drawHeight", contact.getDrawHeight(), ContactView.Heights.big.getValue());
					contact.toggleExpanded();
				}else{
					anim = ObjectAnimator.ofInt(contact, "drawHeight", contact.getDrawHeight(), ContactView.Heights.small.getValue());
					contact.toggleExpanded();
				}
				anim.setDuration(200);
				anim.addUpdateListener(update);
				anim.start();

			}		
		});
		
		viewContacts.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				final ContactView contact = ((ContactView)arg0.getItemAtPosition(arg2));
				activeEditContact = contact;
				launchEditActivity(contact.getContact());
				return false;
			}			
		
		});

		
		// Set up the action bar to show a dropdown list.
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setTitle("Sort By...");
		// Set up the dropdown list navigation in the action bar.
		sort.add(new FirstNameComparator());
		sort.add(new NumberComparator());
		actionBar.setListNavigationCallbacks(sort,this);

	}

	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_contacts, menu);
		
		MenuItem addNew = menu.findItem(R.id.item1);
		Intent intent = new Intent(this,EditContactActivity.class);
		Bundle b = new Bundle();
		b.putSerializable("contact", new Contact("","","",""));
		intent.putExtras(b);
		addNew.setIntent(intent);
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.
		contacts.sortMethod(sort.getItem(position));
		viewContacts.invalidateViews();
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem menu){
		if (menu.getItemId() == R.id.item1){
			this.activeEditContact = new ContactView(this, new Contact("","","",""));
			this.contacts.add(activeEditContact);
			this.startActivityForResult(menu.getIntent(), ViewContactsActivity.STATIC_EDIT_CONTACT_IDENTIFIER);
		}
		return true;
		
	}
	

	private void launchEditActivity(Contact contact){
		Intent intent = new Intent();
		intent.setClass(this, EditContactActivity.class);
		Bundle b = new Bundle();
		b.putSerializable("contact",contact);
		intent.putExtras(b);
		this.startActivityForResult(intent,ViewContactsActivity.STATIC_EDIT_CONTACT_IDENTIFIER);
	}

	@Override
	public void onAnimationUpdate(ValueAnimator arg0) {
		viewContacts.invalidateViews();
		
	}
	
	@Override 
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

	  super.onActivityResult(requestCode, resultCode, data); 
	  switch(requestCode) { 
	    case (ViewContactsActivity.STATIC_EDIT_CONTACT_IDENTIFIER) : { 
	      if (resultCode == Activity.RESULT_OK) { 
	    	  Serializable b = data.getSerializableExtra("contact");
	    	  final Contact contact = (Contact) b;
	    	  activeEditContact.setContact(contact);
	    	  viewContacts.invalidateViews();
	    	  contacts.sort();
	    	  Log.i("contacts","successfully handled incoming contact");
	      } 
	      break; 
	    } 
	  } 
	}

}
