package mdye175.se206.contactsfor206.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

public class ContactDataBase extends AsyncTask<DatabaseOperation,Integer,Long>{
	
	private Database contactDB;

	public ContactDataBase(Context context, String name, CursorFactory factory, int version){
		this.contactDB = new Database(context,name,factory,version);
		contactDB.open();
	}

	
	@Override
	protected Long doInBackground(DatabaseOperation... params) {
		for (DatabaseOperation o: params){
			o.doOperation(contactDB);
		}
		return (long) params.length;
		
	}
	

	
	protected void onPostExecute(Long result) {}


}
