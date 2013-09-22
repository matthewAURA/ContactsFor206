package mdye175.se206.contactsfor206;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ContactsList extends ArrayAdapter<Contact>{

	protected LayoutInflater fInflater;
    
    
	public ContactsList(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
        fInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@SuppressWarnings("unchecked")
	public void add(Contact newContact){
		super.add(newContact);
		super.sort(new ContactComparator());
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Contact contact = this.getItem(position);
		
	    if (convertView == null) {
	    	convertView = fInflater.inflate(R.layout.contact_view, parent, false);
        }
	    
	    final LayoutParams params = convertView.getLayoutParams();
        if (params == null) { 
        	convertView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, contact.getDrawHeight())); 
        }else { 
        	params.height = contact.getDrawHeight();
        }
        contact.populateCompact(convertView);

	    
	    return convertView;
	}
	
	


}
