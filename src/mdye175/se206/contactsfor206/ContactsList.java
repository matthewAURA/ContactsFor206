package mdye175.se206.contactsfor206;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;

public class ContactsList extends ArrayAdapter<Contact>{

	protected LayoutInflater fInflater;
    private ContactComparator compare;
    
	public ContactsList(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
        fInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void add(Contact newContact){
		super.add(newContact);
		if (this.compare != null){
			super.sort(compare);
		}
	}

	public void sortMethod(ContactComparator comp){
		this.compare = comp;
		this.sort(this.compare);
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
