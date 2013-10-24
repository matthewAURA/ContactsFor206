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
	    Cursor cursor = db.query(null,null, null, null, null, null);
	    cursor.moveToFirst();

	    while (!cursor.isAfterLast()) {
	        newContact = cursorToContact(cursor);
	        contacts.add(newContact);
	        cursor.moveToNext();
	      }
	      // make sure to close the cursor
	      cursor.close();
		  Log.i("Contacts list length",String.valueOf(contacts.size()));
	}
	
	private Contact cursorToContact(Cursor cursor){
		
		Contact contact = new Contact(Integer.parseInt(cursor.getString(0)));
		for (int i=0;i<ContactDataValue.Parameter.values().length;i++){
			contact.addParameter(cursor.getString(i+1),ContactDataValue.Parameter.values()[i]);
		}
		contact.setImageLocation(cursor.getString(cursor.getColumnCount()-1));
		return contact;
		
	}

	@Override
	public List<Contact> getResults() {
		
		return contacts;
	}


}
