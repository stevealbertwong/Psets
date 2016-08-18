package com.example.steveandrewwong.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    public static final int REQUEST_CODE_TO_TAKEPICTURE = 1;
    private static final int REQUEST_CODE_TO_GETPICTURE = 2;
    public Bitmap bitmap;
    public Uri fileUri;
    private ImageView mThumbnailPreview;
    private Uri mCurrentPhotoUri;
    public PhotoTaker mPhotoTaker;
    public Firebase firebase;
    String mCurrentPhotoPath;

    /*
     * 1. AndroidManifest.xml -> decided external public storage or internal file to write to
     * 2. create a collision-resistant file name using a date-time stamp + save path in member variable
     *
     *      File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
     *      File imageFile  = File.createTempFile (uniqueTimeStampedFileName, .jpg, directoryToStored)
     *
     * 3. get the Uri of that File + store the picture into that File's location with Uri
     *
     *      mCurrentPhotoUri = Uri.fromFile(placeholderFile);
     *      takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCurrentPhotoUri);
     *
    * */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://stevencamera.firebaseio.com/");

//        if (!hasCamera()) {
//            button.setEnabled(false);
//        }

        imageView = (ImageView) findViewById(R.id.image_view_1);


        previewStoredFirebaseImage();





    }


    public void takePictureClick(View view) {


        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // File imageFile  = File.createTempFile (uniqueTimeStampedFileName, .jpg, directoryToStored)
        File placeholderFile = ImageFactory.newFile();
        // take picture and stored in the Uri of newly created File
        mCurrentPhotoUri = Uri.fromFile(placeholderFile);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCurrentPhotoUri);

        startActivityForResult(takePictureIntent, REQUEST_CODE_TO_TAKEPICTURE);

    }





//    private void takePhoto() {
//
////        Toast.makeText(getApplicationContext(),
////
////                "Sorry! Couldn't create a new image file", Toast.LENGTH_SHORT)
////
////                .show();
//
//
//        // ImageFactory -> File imageFile  = File.createTempFile (uniqueTimeStampedFileName, .jpg, directoryToStored)
//        File placeholderFile = ImageFactory.newFile();
//
//        mCurrentPhotoUri = Uri.fromFile(placeholderFile);
//
//        if (!mPhotoTaker.takePhoto(placeholderFile)) {
//
//            Toast.makeText(getApplicationContext(),
//
//                    "Sorry! Couldn't create a new image file", Toast.LENGTH_SHORT)
//
//                    .show();
//
//        }
//
//    }








    // reading from Firebase
    private void previewStoredFirebaseImage() {
        firebase.child("pic").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String base64Image = (String) snapshot.getValue();

                if (base64Image != null ) {
                    byte[] imageAsBytes = Base64.decode(base64Image.getBytes(), Base64.DEFAULT);

                    imageView.setImageBitmap(
                            BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
                    );

                    System.out.println("Downloaded image with length: " + imageAsBytes.length);
                }
            }

            @Override

            public void onCancelled(FirebaseError error) {}

        });

    }





    public void getPictureClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE_TO_GETPICTURE);
    }








    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


//        // take picture activity is finished -> 1. get bitmap data 2. set imageView
//        if (requestCode == REQUEST_CODE_TO_TAKEPICTURE) {
//
//            // Image captured and saved to fileUri specified in the Intent
//            Toast.makeText(this, "Image saved to:\n" +
//                    data.getData(), Toast.LENGTH_LONG).show();
//
//
//
//            // taken photo is encoded in the return Intent delivered to onActivityResult
//            // taken photo is encoded as a small bitmap stored in Extra under the key "data"
//            bitmap = (Bitmap) data.getExtras().get("data");
//            imageView = (ImageView) findViewById(R.id.image_view_1);
//            imageView.setImageBitmap(bitmap);
//        }



        if (requestCode == REQUEST_CODE_TO_TAKEPICTURE) {

            if (resultCode == RESULT_OK) {

                previewCapturedImage();

                storeImageToFirebase();

            } else if (resultCode == RESULT_CANCELED) {

                Toast.makeText(getApplicationContext(),

                        "User cancelled image capture", Toast.LENGTH_SHORT)

                        .show();

            } else {

                Toast.makeText(getApplicationContext(),

                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)

                        .show();

            }

        }







        if (requestCode == REQUEST_CODE_TO_GETPICTURE) {
            try {
                InputStream stream = null;
                // recyle unused bitmaps
                if (bitmap != null) {
                    bitmap.recycle();
                }

                // get image from Intent/data as input stream from content folder/Environment + decode the stream into bitmap
                // Stream -> preconnected input and output communication channels[1] between a computer program and its environment
                stream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);

                //imageView.setImageBitmap(bitmap);



                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // compress(Bitmap.CompressFormat format, int quality, OutputStream stream)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                byte[] bytes = baos.toByteArray();

                // we finally have our base64 string version of the image -> every 2 bytes become an alphabet
                // Byte Array -> String
                String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);
                firebase.child("pic").setValue(base64Image);

                System.out.println("Stored image with length: " + bytes.length);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }




    // viewing it from PhotoUri.getPath()
    private void previewCapturedImage() {

        BitmapFactory.Options options = new BitmapFactory.Options();

        // downsizing image as it throws OutOfMemory Exception for larger images
        options.inSampleSize = 8;
        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoUri.getPath(), options);
        imageView.setImageBitmap(bitmap);
    }




    private void storeImageToFirebase() {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8; // shrink it down otherwise we will use stupid amounts of memory
        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoUri.getPath(), options);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] bytes = baos.toByteArray();

        String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);

        // we finally have our base64 string version of the image, save it.




        firebase.child("pic").setValue(base64Image);

        System.out.println("Stored image with length: " + bytes.length);

    }







//    // check if device has camera
//    public boolean hasCamera() {
//        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
//
//    }







}

