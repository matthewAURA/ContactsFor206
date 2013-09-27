package mdye175.se206.contactsfor206;


import java.util.Comparator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;

public class ContactsArrayAdapter extends ArrayAdapter<ContactView>{

	private LayoutInflater inflater;
    private Comparator<? super ContactView> compare;
    
	public ContactsArrayAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void add(ContactView newContact){
		super.add(newContact);
		if (this.compare != null){
			super.sort(compare);
		}
	}

	public void sortMethod(ContactComparator comp){
		this.compare = comp;
		this.sort(compare);
	}
	
	public void sort(){
		this.sort(this.compare);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ContactView contact = this.getItem(position);
		
	    if (convertView == null) {
	    	convertView = inflater.inflate(R.layout.contact_view, parent, false);
        }
	    
	    
	    //getView must manually request the height of ContactView objects, as they can be animated and have 
	    //Changing heights
	    final LayoutParams params = convertView.getLayoutParams();
        if (params == null) { 
        	convertView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, contact.getDrawHeight())); 
        }else { 
        	params.height = contact.getDrawHeight();
        }
        contact.populateContact(convertView);

	    
	    return convertView;
	}
	
	public void remove(Contact contact){
		for (int i=0;i<this.getCount();i++){
			if (this.getItem(i).getContact().equals(contact)){
				this.remove(this.getItem(i));
			}
		}
	}
	


}
