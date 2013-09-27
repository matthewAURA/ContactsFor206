package mdye175.se206.contactsfor206;

import android.content.Context;
import android.widget.ArrayAdapter;


//Simple Adapter to create a list of sorting methods for a ListView
public class SortMethodList extends ArrayAdapter<ContactComparator> {

	public SortMethodList(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

}
