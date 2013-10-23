package mdye175.se206.contactsfor206.database;

import java.util.ArrayList;
import java.util.List;

import mdye175.se206.contactsfor206.contact.Contact;
import mdye175.se206.contactsfor206.contact.ContactDataValue;
import mdye175.se206.contactsfor206.contact.ContactDataValue.Parameter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
	        newContact = cursorToContact(cursor);
	        contacts.add(newContact);
	        cursor.moveToNext();
	      }
	      // make sure to close the cursor
	      cursor.close();
	}
	
	private Contact cursorToContact(Cursor cursor){
		Contact contact = new Contact();
		for (int i=1;i<ContactDataValue.Parameter.values().length;i++){
			contact.addParameter(cursor.getString(i),ContactDataValue.Parameter.values()[i]);
			Log.i("Contacts database load",cursor.getString(i));
		}
		return contact;
		
	}

	@Override
	public List<Contact> getResults() {
		return contacts;
	}


}
