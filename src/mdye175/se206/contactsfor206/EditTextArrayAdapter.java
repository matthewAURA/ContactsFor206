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
	    final TextView text = (TextView)convertView.findViewById(R.id.fieldTextName);
	    
	    if (display != null && contact != null){
	    	display.setText(data.getEditText().getText());	    
	    	text.setText(data.getTextView().getText());
	    }
	    
	    display.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
			        if (contact.getById(data.getParameter()) != null){
			                contact.getById(data.getParameter()).setValue(arg0.toString());
			        }else{
			                contact.addParameter(arg0.toString(), data.getParameter());
			        }
			}
	    	
	    });
	    
	    
	    return convertView;
	}
	

}
