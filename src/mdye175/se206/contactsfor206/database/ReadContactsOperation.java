package mdye175.se206.contactsfor206.database;

import java.util.ArrayList;
import java.util.List;

import mdye175.se206.contactsfor206.contact.Contact;
import android.database.Cursor;

public class ReadContactsOperation implements DatabaseOperation<List> {
	
	private List<Contact> contacts;
	
	public ReadContactsOperation(){
		this.contacts = new ArrayList<Contact>();
	}
	
	@Override
	public String getSelection() {
		return null;
	}

	@Override
	public String[] getSelectionArgs() {
		return null;
	}

	@Override
	public String getGroupBy() {
		return null;
	}

	@Override
	public String getHaving() {
		return null;
	}

	@Override
	public String getOrderBy() {
		return null;
	}
	
	public void doOperation(Cursor input){
		//Code to take the cursor object goes here
		Contact newContact;
		for (int i=0;i<input.getCount();i++){
			newContact = new Contact(input.getString(input.getColumnIndex(ContactDataBase.Column.name.toString())),input.getString(input.getColumnIndex(ContactDataBase.Column.phone.toString())));
			contacts.add(newContact);
			input.moveToNext();
		}
	}

	@Override
	public List<Contact> getResults() {
		return contacts;
	}


}
