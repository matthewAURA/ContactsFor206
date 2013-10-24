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

public class EditContactOperation implements DatabaseOperation<List> {
	
	private List<Contact> contacts;
	private Contact contact;
	private Contact target;
	
	public EditContactOperation(Contact contact,Contact target){
		this.contacts = new ArrayList<Contact>();
		this.contact = contact;
		this.target = target;
	}
	
	@Override
	public void doOperation(Database db){
		String selectionString = db.ID_COLUMN + " = " + target.getId() ;
	    ContentValues values = new ContentValues();
	    for (int i=0;i<ContactDataValue.Parameter.values().length;i++){
	    	values.put(Database.columns[i],contact.getIndex(i).getValue());
	    }
	    
	    
	    long insertId = db.insert(values);
	    db.update(values, selectionString, null);

	}
	


	@Override
	public List<Contact> getResults() {
		return null;
	}


}
