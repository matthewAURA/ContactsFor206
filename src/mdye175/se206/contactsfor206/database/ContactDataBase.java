package mdye175.se206.contactsfor206.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

public class ContactDataBase extends AsyncTask<String,Integer,Long>{
	
	private Database db;
	
	private class Database extends SQLiteOpenHelper{
		//Database Name
		public static final String dbName = "ContactsDataBase.db";
		
		//Database columns
		public static final String contact_id = "id";
		public static final String contact_name = "name";
		public static final String contact_phone = "phone";
		public static final String contact_email = "email";
		public static final String contact_address = "address";
		
		//Strings for creating and deleting tables
		public static final String create_table = "CREATE TABLE Contacts (ID int,Name varchar(30),Phone varchar(30),Email varchar(30),Address varchar(30)";
		public static final String delete_table = "DROP TABLE Contacts";
		
		public Database(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}
	
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(Database.create_table);
			
		}
	
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(Database.delete_table);
			this.onCreate(db);
		}
	}
	
	public ContactDataBase(Context context, String name, CursorFactory factory, int version){
		this.db = new Database(context,name,factory,version);
		
	}
	
	@Override
	protected Long doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

}
