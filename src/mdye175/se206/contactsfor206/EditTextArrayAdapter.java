package mdye175.se206.contactsfor206;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class EditTextArrayAdapter extends ArrayAdapter<EditText> {

	private LayoutInflater inflater;
	
	public EditTextArrayAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EditText data = this.getItem(position);
		
	    if (convertView == null) {
	    	convertView = inflater.inflate(R.layout.edit_contact_textfield, parent, false);
        }
	    
	    EditText display = (EditText)convertView.findViewById(R.id.editText1);
	    if (display != null)
	    	display.setText(data.getText());
	    
	    return convertView;
	}
	

}
