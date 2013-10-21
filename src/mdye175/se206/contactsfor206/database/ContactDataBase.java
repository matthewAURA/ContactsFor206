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
	
	
	
	private class Database extends SQLiteOpenHelper{
		//Database Name

		private SQLiteDatabase db;

		//Table info
		public static final String table_name = "Contacts";
		private final String [] columns = {Column.id.toString(),Column.name.toString(),Column.phone.toString(),Column.email.toString(),Column.address.toString()};
		
		//Strings for creating and deleting tables
		public String create_table;
		public static final String delete_table = "DROP TABLE " + table_name;
		
		public Database(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			create_table = "create table " + table_name + " (";
			for (String i: columns){
				create_table += " ";
				create_table += i;
				create_table += " text";
			}
			create_table += " );";
			
		}
	
		@Override
		public void onCreate(SQLiteDatabase db) {
			//TODO: Move this method to a create db method
			db = this.getWritableDatabase();
			db.execSQL(create_table);	
		}
	
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(Database.delete_table);
			this.onCreate(db);
		}
		
		public Cursor execute(DatabaseOperation o){
			return db.query(this.table_name, this.columns, o.getSelection(), o.getSelectionArgs(), o.getGroupBy(), o.getHaving(),o.getOrderBy());
		}
	}
	
	public ContactDataBase(Context context, String name, CursorFactory factory, int version){
		this.contactDB = new Database(context,name,factory,version);
		
	}
	
	@Override
	protected Long doInBackground(DatabaseOperation... params) {
		Cursor c;
		for (DatabaseOperation o: params){
			c = this.contactDB.execute(o);
			o.doOperation(c);
		}
		
		return null;
	}

}
