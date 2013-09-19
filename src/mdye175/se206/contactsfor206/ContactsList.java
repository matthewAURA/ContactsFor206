package mdye175.se206.contactsfor206;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactsList extends ArrayAdapter<Contact>{
	
	private int resource;
	
	public ContactsList(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		this.resource = textViewResourceId;
	}
	
	public void add(Contact newContact){
		super.add(newContact);
		super.sort(new ContactComparator());
	}

	/*public View getView(int position,View convertView, ViewGroup parent){
		View view;
        //Get the current alert object
        Contact contact = getItem(position);
         
        //Inflate the view
        if(convertView==null)
        {
            view = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, view, true);
        }
        else
        {
            view = (LinearLayout) convertView;
        }
        //Get the text boxes from the listitem.xml file
        TextView nameText =(TextView)view.findViewById(R.id.nameText);
        TextView numberText =(TextView)view.findViewById(R.id.numberText);
         
        //Assign the appropriate data from our alert object above
        nameText.setText("test");
        //numberText.setText(contact.toString());
         
        return view;
		
	}*/
	
	
	

	@Override
	public View getView(int itempos, View convertView, ViewGroup parent) {
	//Check if the convertview is null, if it is null it probably means that this //is the first time the view has been displayed
	if (convertView == null)
	{
		convertView = View.inflate (getContext(),R.layout.contact_view, null);
	}
	//Get the current Contact
	Contact contact = this.getItem(itempos);
	//If it is not null, you can just reuse it from the recycler
	TextView txtcontent = (TextView)convertView.findViewById(R.id.nameText);
	txtcontent.setText (contact.toString());
	// return the view for a single item in the listview
	return convertView;
	}


}
