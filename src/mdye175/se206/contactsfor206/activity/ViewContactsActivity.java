package mdye175.se206.contactsfor206.activity;

import gesture.ContactSwipeGesture;
import gesture.ListGestureListener;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mdye175.se206.contactsfor206.ContactsArrayAdapter;
import mdye175.se206.contactsfor206.R;
import mdye175.se206.contactsfor206.SortMethodList;
import mdye175.se206.contactsfor206.contact.Contact;
import mdye175.se206.contactsfor206.contact.ContactDataValue;
import mdye175.se206.contactsfor206.contact.ContactView;
import mdye175.se206.contactsfor206.contact.ContactViewFactory;
import mdye175.se206.contactsfor206.contact.comparator.FirstNameComparator;
import mdye175.se206.contactsfor206.contact.comparator.HomeNumberComparator;
import mdye175.se206.contactsfor206.contact.comparator.LastNameComparator;
import mdye175.se206.contactsfor206.contact.comparator.MobileNumberComparator;
import mdye175.se206.contactsfor206.database.DeleteContactOperation;
import mdye175.se206.contactsfor206.database.EditContactOperation;
import mdye175.se206.contactsfor206.database.WriteContactOperation;
import mdye175.se206.contactsfor206.database.ContactDataBase;
import mdye175.se206.contactsfor206.database.ReadContactsOperation;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;


public class ViewContactsActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener, AnimatorUpdateListener,ListGestureListener {

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
	private ContactViewFactory factory;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		setContentView(R.layout.activity_view_contacts);
		viewContacts = (ListView)findViewById(R.id.listView1);
		sort = new SortMethodList(this,android.R.layout.simple_list_item_1);
		viewContacts.setItemsCanFocus(false);
		
		
		//Set up contacts arrays
		contacts = new ContactsArrayAdapter(this, android.R.layout.simple_list_item_1);
		viewContacts.setAdapter(contacts);

		//Set up view factory
		factory = new ContactViewFactory(contacts.getContext());
		
		
		//For now, just print out what is in the database to the console window
		ReadContactsOperation debug = new ReadContactsOperation();
		database = (ContactDataBase) new ContactDataBase(this.getApplicationContext(),dbName,null,1).execute(debug);
		
		try {
			database.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<Contact> results;
		results = debug.getResults();
		for (Contact c: results){
			contacts.add(factory.createFromContact(c));
		}
		
		//Set up gesture controls
		final GestureDetector gestureDetector = new GestureDetector(this,new ContactSwipeGesture(viewContacts,this));
	    OnTouchListener gestureListener = new OnTouchListener() {
	    public boolean onTouch(View v, MotionEvent event) {
	    	return gestureDetector.onTouchEvent(event); 
	    }};
	    viewContacts.setOnTouchListener(gestureListener);

	
			
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
		sort.add(new LastNameComparator());
		sort.add(new HomeNumberComparator());
		sort.add(new MobileNumberComparator());
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
		b.putSerializable("contact", new Contact(this.contacts.getCount()));
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
			this.activeEditContact = new ContactView(this, new Contact(this.contacts.getCount()));
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
	
	private void removeContactFromList(int index){
		removeContactFromDatabase(contacts.getItem(index).getContact());
		contacts.remove(contacts.getItem(index));
		contacts.notifyDataSetChanged();
	}
	
	private void removeContactFromDatabase(Contact contact){
		this.database = new ContactDataBase(this.getApplicationContext(), dbName, null, 1);
		DeleteContactOperation delete = new DeleteContactOperation(contact);
		database = (ContactDataBase) database.execute(delete);
		
	}
	
	private void saveContact(Contact contact){
		//Create Database
		this.database = new ContactDataBase(this.getApplicationContext(), dbName, null, 1);
		for (ContactDataValue.Parameter p: ContactDataValue.Parameter.values()){
			if (contact.getById(p) == null){
				contact.addParameter("",p);
			}
		}
		
		WriteContactOperation debugAdd = new WriteContactOperation(contact);
		database = (ContactDataBase) database.execute(debugAdd);
	}
	

	
	@Override 
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data); 
	  switch(requestCode) { 
	    case (ViewContactsActivity.STATIC_EDIT_CONTACT_IDENTIFIER) : { 
	      if (resultCode == Activity.RESULT_OK) { 
	    	  Serializable b = data.getSerializableExtra("contact");
	    	  final Contact contact = (Contact) b;
	    	  contacts.sort();
	    	  removeContactFromDatabase(contact);
	    	  saveContact(contact);
	    	  //Update data
	    	  for (int i =0;i<contacts.getCount();i++){
	    		  if (contacts.getItem(i).getContact().equals(contact)){
	    			  contacts.getItem(i).setContact(contact);
	    			  contacts.notifyDataSetChanged();
	    		  }
	    	  }
	    	  contacts.notifyDataSetChanged();
	    	  this.recreate();
	      } 
	      break; 
	    } 
	  } 
	}


	@Override
	public void onGesture(int index) {
		this.removeContactFromList(index);
	}

}
