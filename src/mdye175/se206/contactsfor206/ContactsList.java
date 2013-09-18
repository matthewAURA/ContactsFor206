package mdye175.se206.contactsfor206;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ContactsList extends ArrayAdapter<Contact>{

	public ContactsList(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}
	
	public void add(Contact newContact){
		super.add(newContact);
		super.sort(new ContactComparator());
	}
	@Override
	public View getView(int position,View convertView, ViewGroup parent){
		return parent;
		
	}


}
