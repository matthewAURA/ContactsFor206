package mdye175.se206.contactsfor206.database;

import java.util.ArrayList;
import java.util.List;

import mdye175.se206.contactsfor206.contact.Contact;
import mdye175.se206.contactsfor206.contact.ContactDataValue;
import mdye175.se206.contactsfor206.contact.ContactDataValue.Parameter;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AddContactOperation implements DatabaseOperation<List> {
	
	private Contact newContact;
	
	public AddContactOperation(Contact contact){
		this.newContact = contact;
	}
	
	@Override
	public void doOperation(Database database){
	    ContentValues values = new ContentValues();
	    for (int i=1;i<ContactDataValue.Parameter.values().length;i++){
	    	values.put(Database.columns[i],newContact.getIndex(i).toString());
	    }
	    
	    
	    long insertId = database.insert(values);
	    
	    Cursor cursor = database.query(Database.columns, Database.ID_COLUMN + " = " + insertId, null, null, null, null);
	    cursor.moveToFirst();
	    Contact contact = cursorToContact(cursor);
	    cursor.close();
	    //return contact;
	}
	
	private Contact cursorToContact(Cursor cursor){
		Contact contact = new Contact();
		for (int i=0;i<ContactDataValue.Parameter.values().length;i++){
			contact.addParameter(cursor.getString(i),ContactDataValue.Parameter.values()[i]);
			Log.i("Contacts database load",cursor.getString(i) + " was null");
		}
		return contact;
		
	}

	@Override
	public List<Contact> getResults() {
		return null;
	}


}