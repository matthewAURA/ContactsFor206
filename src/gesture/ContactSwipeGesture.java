package gesture;

import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.ListView;

public class ContactSwipeGesture extends SimpleOnGestureListener{
	
	private final int SWIPE_MAX_OFF_PATH = 100;
	private final int SWIPE_MIN_DISTANCE = 20;
	private final int SWIPE_THRESHOLD_VELOCITY = 1;
	private ListView listView;
	private ListGestureListener listener;
	
    public ContactSwipeGesture(ListView lv,ListGestureListener l){
    	this.listView = lv;
    	this.listener = l;
    }
	
	// Detect a single-click and call my own handler.
    @Override 
    public boolean onSingleTapUp(MotionEvent e){
        int pos = listView.pointToPosition((int)e.getX(), (int)e.getY());
//        /myOnItemClick(pos);
        return false;
    }

    @Override 
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) { 
        if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) 
            return false; 
        if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) { 
            //Do something here
        	int pos = listView.pointToPosition((int)e1.getX(), (int)e1.getY());
        	this.listener.onGesture(pos);
        	Log.i("test","testL");
        }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) { 
            //Do something here
        	int pos = listView.pointToPosition((int)e1.getX(), (int)e1.getY());
        	this.listener.onGesture(pos);
        	Log.i("test","testR");
        } 
        return false; 
    } 
}
