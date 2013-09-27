package mdye175.se206.contactsfor206;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ContactDataList extends ArrayAdapter<TextView> {

	private LayoutInflater fInflater;
	
	public ContactDataList(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		fInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView data = this.getItem(position);
		
		//If we can recycle the view, great, otherwise inflate the view
	    if (convertView == null) {
	    	convertView = fInflater.inflate(R.layout.contact_data_text, parent, false);
        }
	    
	    //Display the text for the field
	    TextView display = (TextView)convertView.findViewById(R.id.textView1);
	    if (display != null)
	    	display.setText(data.getText());
	    
	    return convertView;
	}
	
	public boolean contains(String data){
		for (int i=0;i<this.getCount();i++){
			if (this.getItem(i).getText() == data){
				return true;
			}
		}
		return false;
	}
}
