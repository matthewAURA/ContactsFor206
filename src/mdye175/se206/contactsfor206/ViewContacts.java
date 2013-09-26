package mdye175.se206.contactsfor206;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ViewContacts extends FragmentActivity implements
		ActionBar.OnNavigationListener, AnimatorUpdateListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	private ContactsList contacts;
	private ListView viewContacts;
	private AnimatorUpdateListener update = this;
	private SortMethodList sort;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		setContentView(R.layout.activity_view_contacts);
		viewContacts = (ListView)findViewById(R.id.listView1);
		sort = new SortMethodList(this,android.R.layout.simple_list_item_1);
		
		//Set up contacts objects
		contacts = new ContactsList(this, android.R.layout.simple_list_item_1);
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
				final Contact contact = ((Contact)arg0.getItemAtPosition(arg2));
				ObjectAnimator anim;
				System.out.println(contact.getDrawHeight());
				if (!contact.isExpanded()){
				//Animate the item to make it open
					anim = ObjectAnimator.ofInt(contact, "drawHeight", contact.getDrawHeight(), Contact.Heights.big.getValue());
					contact.toggleExpanded();
				}else{
					anim = ObjectAnimator.ofInt(contact, "drawHeight", contact.getDrawHeight(), Contact.Heights.small.getValue());
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
					int arg2, long id) {
				//Load the activity for the relevant contact.
				Intent intent = new Intent();
				intent.setClass(ViewContacts.this, EditContact.class);
				Bundle b = new Bundle();
				b.putSerializable("contact",contacts.getItem((int) id));
				intent.putExtras(b);
				ViewContacts.this.startActivity(intent);
				return false;
			}
			
		});
		
		// Set up the action bar to show a dropdown list.
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setTitle("Sort By...");
		// Set up the dropdown list navigation in the action bar.
		sort.add(new NameComparator());
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


	@Override
	public void onAnimationUpdate(ValueAnimator arg0) {
		viewContacts.invalidateViews();
		
	}

}
