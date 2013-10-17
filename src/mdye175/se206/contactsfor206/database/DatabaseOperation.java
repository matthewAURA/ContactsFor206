package mdye175.se206.contactsfor206.database;

import java.util.List;

import android.database.Cursor;

public interface DatabaseOperation <T extends List> {
	public String getSelection();
	public String [] getSelectionArgs();
	public String getGroupBy();
	public String getHaving();
	public String getOrderBy();
	public void doOperation(Cursor input);
	public T getResults();
}
