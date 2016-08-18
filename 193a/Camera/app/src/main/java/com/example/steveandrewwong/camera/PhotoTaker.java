package com.example.steveandrewwong.camera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by SteveAndrewWong on 7/30/16.
 */
public class PhotoTaker {
    public static final int REQUEST_CODE_TO_TAKEPICTURE = 1;
    private final Activity mActivity;

    public PhotoTaker(Activity activity) {

        mActivity = activity;

    }

    public boolean takePhoto(File outputFile) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            if (outputFile != null) {
                // get the Unique Resources Identifier from file path -> save URI into Camera Activity
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputFile));
                mActivity.startActivityForResult(takePictureIntent, REQUEST_CODE_TO_TAKEPICTURE);

                return true;
            }
        }
        return false;
    }
}
