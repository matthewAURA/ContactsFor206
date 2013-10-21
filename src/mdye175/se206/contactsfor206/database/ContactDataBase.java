package mdye175.se206.contactsfor206.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

public class ContactDataBase extends AsyncTask<DatabaseOperation,Integer,Long>{
	
	private Database contactDB;

	
	public static enum Column{
		id("contact_id"),name("contact_name"),phone("contact_phone"),email("contact_email"),address("contact_address");
		
		private final String string;
		Column(String id) { this.string = id; }
		
		  public String toString() {
	        return string;
	    }
	}
	
	
	public ContactDataBase(Context context, String name, CursorFactory factory, int version){
		this.contactDB = new Database(context,name,factory,version);
		
	}

	
	@Override
	protected Long doInBackground(DatabaseOperation... params) {
		for (DatabaseOperation o: params){
			o.doOperation(contactDB);
		}
		
		return null;
	}

}
