package mdye175.se206.contactsfor206.database;

import java.util.ArrayList;
import java.util.List;

import mdye175.se206.contactsfor206.contact.Contact;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ReadContactsOperation implements DatabaseOperation<List> {
	
	private List<Contact> contacts;
	
	public ReadContactsOperation(){
		this.contacts = new ArrayList<Contact>();
	}
	
	@Override
	public void doOperation(Database db){
		//Code to take the cursor object goes here
		
		Contact newContact;
	    Cursor cursor = db.query(Database.columns,null, null, null, null, null);
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	        Comment comment = cursorToComment(cursor);
	        comments.add(comment);
	        cursor.moveToNext();
	      }
	      // make sure to close the cursor
	      cursor.close();
	      return comments;
	}

	@Override
	public List<Contact> getResults() {
		return contacts;
	}


}
