package mdye175.se206.contactsfor206;

import mdye175.se206.contactsfor206.contact.ContactDataValue;
import mdye175.se206.contactsfor206.contact.ContactDataValue.Parameter;
import android.widget.EditText;

//Wrapper class to tie together a preset parameter and an edittext instance
public class EditTextParameter {

	private EditText editText;
	private ContactDataValue.Parameter param;
	
	public EditTextParameter(EditText edit,ContactDataValue.Parameter p){
		this.editText = edit;
		this.param = p;
	}
	
	public EditText getEditText(){
		return editText;
	}
	
	public ContactDataValue.Parameter getParameter(){
		return this.param;
	}
	
	
}
