package mdye175.se206.contactsfor206.contact;

import android.content.Context;

public class ContactViewFactory {

	private Context context;
	
	public ContactViewFactory(Context context){
		this.context = context;
	}
	
	public ContactView createFromContact(Contact contact){
		return new ContactView(this.context, contact);
	}
}
