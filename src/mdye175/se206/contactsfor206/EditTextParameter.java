package mdye175.se206.contactsfor206;

import mdye175.se206.contactsfor206.contact.ContactDataValue;
import mdye175.se206.contactsfor206.contact.ContactDataValue.Parameter;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//Wrapper class to tie together a preset parameter and an edittext instance
public class EditTextParameter extends View {

	private EditText editText;
	private ContactDataValue.Parameter param;
	private TextView textView;
	
	public EditTextParameter(Context context,String text,String editText,ContactDataValue.Parameter p){
		super(context);
		this.editText = new EditText(this.getContext());
		this.editText.setText(editText);
		this.param = p;
		this.textView = new TextView(this.getContext());
		this.textView.setText(text);
	}
	
	public EditText getEditText(){
		return editText;
	}
	
	public TextView getTextView(){
		return this.textView;
	}
	
	public ContactDataValue.Parameter getParameter(){
		return this.param;
	}
	
	
}
