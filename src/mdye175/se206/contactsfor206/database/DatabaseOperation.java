package mdye175.se206.contactsfor206.database;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public interface DatabaseOperation <T extends List> {
	public void doOperation(Database db);
	public T getResults();
}
