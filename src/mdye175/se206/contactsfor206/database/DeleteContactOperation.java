package mdye175.se206.contactsfor206.database;

import java.util.ArrayList;
import java.util.List;

import mdye175.se206.contactsfor206.contact.Contact;
import mdye175.se206.contactsfor206.contact.ContactDataValue;
import mdye175.se206.contactsfor206.contact.ContactDataValue.Parameter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DeleteContactOperation implements DatabaseOperation<List> {
	
	private List<Contact> contacts;
	private Contact target;
	
	public DeleteContactOperation(Contact contact){
		this.contacts = new ArrayList<Contact>();
		this.target = contact;
	}
	
	@Override
	public void doOperation(Database db){
		//Code to take the cursor object goes here
		String selectionString = db.ID_COLUMN + " = " + target.getId() ;
	
	   db.delete(selectionString, null);

	}
	


	@Override
	public List<Contact> getResults() {
		return null;
	}


}
