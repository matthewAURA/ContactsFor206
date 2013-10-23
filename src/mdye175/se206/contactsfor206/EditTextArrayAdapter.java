package mdye175.se206.contactsfor206;

import mdye175.se206.contactsfor206.contact.Contact;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class EditTextArrayAdapter extends ArrayAdapter<EditTextParameter> {

	private LayoutInflater inflater;
	private Contact contact;
	
	public EditTextArrayAdapter(Context context, int textViewResourceId,Contact contact) {
		super(context, textViewResourceId);
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.contact = contact;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final EditTextParameter data = this.getItem(position);
	    if (convertView == null) {
	    	convertView = inflater.inflate(R.layout.edit_contact_textfield, parent, false);
        }
	    
	    final EditText display = (EditText)convertView.findViewById(R.id.fieldEditText);
	    
	    if (display != null && contact != null){
	    	display.setText(data.getEditText().getText());	    
	    
        //Because of the fact that the editText objects are in a listview, we need to constantly be setting their value
	    //based on the changes in text - otherwise the android recycler will delete the object so that not too many objects
	    //are consuming memory in the listview
        display.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				if (contact.getById(data.getParameter()) != null){
					contact.getById(data.getParameter()).setValue(arg0.toString());
				}else{
					contact.addParameter(arg0.toString(), data.getParameter());
				}
			}
        	
        	
        });
	    }
	    return convertView;
	}
	

}
