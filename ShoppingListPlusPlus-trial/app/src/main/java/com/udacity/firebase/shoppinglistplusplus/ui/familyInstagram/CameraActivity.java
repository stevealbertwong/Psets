package com.udacity.firebase.shoppinglistplusplus.ui.familyInstagram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Instagram;
import com.udacity.firebase.shoppinglistplusplus.ui.BaseActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SteveAndrewWong on 7/29/16.
 */
public class CameraActivity extends BaseActivity {
    private ImageView imageView;
    private static final int REQUEST_CODE_TO_TAKEPICTURE = 1;
    private static final int REQUEST_CODE_TO_GETPICTURE = 2;
    private Bitmap bitmap;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);


        imageView = (ImageView) findViewById(R.id.image_view_1);

        previewStoredFirebaseImage();

//        if (!hasCamera()) {
//            button.setEnabled(false);
//        }


    }


    public void takePictureClick(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(takePictureIntent, REQUEST_CODE_TO_TAKEPICTURE);
    }

    public void getPictureClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE_TO_GETPICTURE);
    }

    public void previewStoredFirebaseImage() {

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // IGNORE THIS************
        // take picture activity is finished -> 1. get bitmap data 2. set imageView
        if (requestCode == REQUEST_CODE_TO_TAKEPICTURE) {

            // Image captured and saved to fileUri specified in the Intent
            // Toast.makeText(this, "Image saved to:\n" + data.getData(), Toast.LENGTH_LONG).show();
            bitmap = (Bitmap) data.getExtras().get("data");
            imageView = (ImageView) findViewById(R.id.image_view_1);
            imageView.setImageBitmap(bitmap);
        }


        // START HERE*************
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


                // convert Bitmap to ByteArray
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); // (format, quality, outputstream)
                byte[] bytes = baos.toByteArray();

                // TODO: save to Firebase in String Base64Image + show progress bar + toast photo uploaded successfully
                String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);

                Firebase firebaseRef = new Firebase(Constants.FIREBASE_URL); // for updateChildren(path, object)
                Firebase mInstagramRef = new Firebase(Constants.FIREBASE_URL_INSTAGRAM); // for pushId

                Firebase newRef = mInstagramRef.push();
                String listId = newRef.getKey();

                Instagram instagramPOJO = new Instagram(mEncodedEmail, base64Image);
                HashMap<String, Object> instagramHashMap = (HashMap<String, Object>) new ObjectMapper().convertValue(instagramPOJO, Map.class);

                HashMap<String, Object> instagramUpdateChild = new HashMap<>();
                instagramUpdateChild.put("/" + Constants.FIREBASE_LOCATION_INSTAGRAM + "/" + listId, instagramHashMap);

                firebaseRef.updateChildren(instagramUpdateChild, new Firebase.CompletionListener() {
                            @Override
                            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                                imageView.setImageBitmap(bitmap);
                                Toast.makeText(getApplicationContext(), "相片上儲成功", Toast.LENGTH_SHORT).show();

                            }
                        });




            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }




    }




//    // check if device has camera
//    public boolean hasCamera() {
//        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
//
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }



}

// show pictures + jump back to home + take pictures