package mdye175.se206.contactsfor206;

import android.R.integer;
import android.content.Context;
import android.view.View;
import java.lang.Integer;

public abstract class ContactProperty extends View implements Comparable<ContactProperty>  {

	protected String data;
	protected String id;
	private int priority;
	
	protected ContactProperty(Context context){
		super(context);
	}
	
	public ContactProperty(Context context, String data,String id,int priority) {
		this(context);
		this.data = data;
		this.id = id;
		this.priority = priority;
	}
	
	public String toString(){
		return data;
	}
	
	public int compareTo(ContactProperty other){
		return this.priority < other.priority? 1 : this.priority==other.priority? 0 : -1;
	}
	
	

}
