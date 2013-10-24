package mdye175.se206.contactsfor206.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import mdye175.se206.contactsfor206.R;
import mdye175.se206.contactsfor206.R.layout;
import mdye175.se206.contactsfor206.R.menu;
import mdye175.se206.contactsfor206.contact.Contact;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class GetImageActivity extends Activity {

	
	private Context context;
	private Contact contact;
	private Bitmap bitmap;
	private static int IMAGE_CODE = 1;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_image);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_image, menu);
		return true;
	}


	public TakePhotoManager(Context context,Contact contact) {
		this.context = context;
		this.contact = contact;
	}

	  @Override
	  public void onPictureTaken(byte[] data, Camera camera) {

	    File pictureFileDir = getDir();

	    if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
	      return;
	    }

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
	    String date = dateFormat.format(new Date());
	    String photoFile = "Picture_" + date + ".jpg";

	    String fileName = pictureFileDir.getPath() + File.separator + photoFile;
	    this.contact.setImageLocation(fileName);
	    File pictureFile = new File(fileName);

	      FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(pictureFile);
			fileOutputStream.write(data);
		    fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	     
	  }

	  private File getDir() {
	    File sdDir = Environment
	      .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
	    return new File(sdDir, "ContactsFor206");
	  }
	  
	  public void setImageCallBack(ImageView image,final Activity activity){
			image.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
				    Intent intent = new Intent();
				    intent.setType("image/*");
				    intent.setAction(Intent.ACTION_GET_CONTENT);
				    intent.addCategory(Intent.CATEGORY_OPENABLE);
				    activity.startActivityForResult(intent,IMAGE_CODE);
				}
				
			});
	  }
	  
	  
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		    InputStream stream = null;
		    if (requestCode == IMAGE_CODE && resultCode == Activity.RESULT_OK)
		        // recyle unused bitmaps
		        if (bitmap != null) {
		          bitmap.recycle();
		        }
		        try {
					stream = getContentResolver().openInputStream(data.getData());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
		        bitmap = BitmapFactory.decodeStream(stream);
		        image.setImageBitmap(bitmap);

	}
}
