package mdye175.se206.contactsfor206.database;


import java.util.List;
import mdye175.se206.contactsfor206.contact.Contact;
import mdye175.se206.contactsfor206.contact.ContactDataValue;
import android.content.ContentValues;


public class WriteContactOperation implements DatabaseOperation<List> {
	
	private Contact newContact;
	
	public WriteContactOperation(Contact contact){
		this.newContact = contact;
	}
	
	@Override
	public void doOperation(Database database){
	    ContentValues values = new ContentValues();
	    for (int i=0;i<ContactDataValue.Parameter.values().length;i++){
	    	values.put(Database.columns[i],newContact.getIndex(i).getValue());
	    }
	    
	    
	    database.insert(values);
	    
	    /*Cursor cursor = database.query(Database.columns, Database.ID_COLUMN + " = " + insertId, null, null, null, null);
	    cursor.moveToFirst();
	    Contact contact = cursorToContact(cursor);
	    cursor.close();
	    //return contact;*/
	}


	@Override
	public List<Contact> getResults() {
		return null;
	}


}
