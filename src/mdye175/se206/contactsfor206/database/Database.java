package mdye175.se206.contactsfor206.database;

import mdye175.se206.contactsfor206.database.ContactDataBase.Column;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class Database extends SQLiteOpenHelper{
		//Database Name

		private SQLiteDatabase db;

		//Table info
		//TODO: Change this to not be hard coded
		public static final String table_name = "Contacts";
		
		//TODO: Change this to generic columns
		public static final String [] columns = {Column.id.toString(),Column.name.toString(),Column.phone.toString(),Column.email.toString(),Column.address.toString()};
		
		//Strings for creating and deleting tables
		public String create_table;
		public static final String delete_table = "DROP TABLE " + table_name;

		public static final String ID_COLUMN = "_id";
		
		public Database(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			create_table = "create table " + table_name + " (" + ID_COLUMN + "integer primary key autoincrement, ";
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
		
		public Cursor query(String [] columns, String selection,String [] selectionArgs,String groupBy,String having, String orderBy){
			return db.query(table_name, columns, selection, selectionArgs, groupBy, having, orderBy);
		}
		
		public long insert(ContentValues values){
			return db.insert(table_name, null, values);
		}
	}