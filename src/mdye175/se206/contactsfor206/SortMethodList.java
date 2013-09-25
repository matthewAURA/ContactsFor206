package mdye175.se206.contactsfor206;

import android.content.Context;
import android.widget.ArrayAdapter;

public class SortMethodList extends ArrayAdapter<ContactComparator> {

	public SortMethodList(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

}
