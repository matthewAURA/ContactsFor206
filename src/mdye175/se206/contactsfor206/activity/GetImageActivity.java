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
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class GetImageActivity extends Activity {

	
	private Bitmap bitmap;
	private static int LOAD_IMAGE_CODE = 1;
	private static int TAKE_IMAGE_CODE = 2;
	private ImageView image;
	private Uri file;
	public static String JPEG_FILE_PREFIX = "ContactsFor206";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_image);
		Button galleryButton = (Button) findViewById(R.id.button1);
		Button cameraButton = (Button) findViewById(R.id.button2);
		Button saveButton = (Button) findViewById(R.id.button3);
		image = (ImageView) findViewById(R.id.imageView1);
		
		galleryButton.setText("Load Image From Gallery");
		cameraButton.setText("Take Photo");
		saveButton.setText("Save");
		galleryButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				loadGalleryImage();
			}
			
		});
		
		cameraButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				takePhotoImage();
			}
			
		});
		
		saveButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				saveAndFinish();
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_image, menu);
		return true;
	}



	  private File getDir() {
	    File sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
	    return new File(sdDir, "ContactsFor206");
	  }
	  
	  public void loadGalleryImage(){
		    Intent intent = new Intent();
		    intent.setType("image/*");
		    intent.setAction(Intent.ACTION_GET_CONTENT);
		    intent.addCategory(Intent.CATEGORY_OPENABLE);
		    startActivityForResult(intent,LOAD_IMAGE_CODE);
	  }
	  
	  
	  private void takePhotoImage() {
		    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		    startActivityForResult(takePictureIntent, TAKE_IMAGE_CODE );
		}
	  
	  private void saveAndFinish(){
		  	Intent intent = new Intent();
			intent.setData(file);
			setResult(Activity.RESULT_OK, intent);
			finish();
	  }
	  
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		    InputStream stream = null;
		    if (requestCode == LOAD_IMAGE_CODE && resultCode == Activity.RESULT_OK){
		        // recyle unused bitmaps
		        if (bitmap != null) {
		          bitmap.recycle();
		        }
		        try {
		        	file = data.getData();
		        	Log.i("Image loading",file.getPath());
					stream = getContentResolver().openInputStream(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
		        bitmap = BitmapFactory.decodeStream(stream);
		        image.setImageBitmap(bitmap);
		        		
		    }else if(requestCode == TAKE_IMAGE_CODE){
		        Bundle extras = data.getExtras();
		        bitmap = (Bitmap) extras.get("data");
		        image.setImageBitmap(bitmap);
		        
		     // Create an image file name
			    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			    String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_.jpg";
			    File image = null;
				try {
					image = File.createTempFile(imageFileName,".jpg",getDir());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//Save image
			    file = Uri.fromFile(image);
			    Intent takePictureIntent = new Intent();
			    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,file);
			    //Save to gallery
			    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
			    mediaScanIntent.setData(file);
			    this.sendBroadcast(mediaScanIntent);

			    
		    }
	}
}
