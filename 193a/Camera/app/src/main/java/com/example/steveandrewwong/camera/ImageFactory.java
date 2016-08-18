package com.example.steveandrewwong.camera;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class ImageFactory {

    /**
     * Creates a new jpg file (with UNIQUE NAME and to store an image)
     * File imageFile  = File.createTempFile (uniqueTimeStampedFileName, .jpg, directoryToStored)
     *
     * @return null if the file couldn't be created.
     */

    public static File newFile() {

        // Create an unique image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        // external public directory or private directory
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        // Create the storage directory if it does not exist
        if (!storageDir.exists()){
            storageDir.mkdirs();
        }





        File imageFile;
        String mCurrentPhotoPath;
        try {
            imageFile = File.createTempFile(

                    imageFileName,  /* prefix */

                    ".jpg",         /* suffix */

                    storageDir      /* directory */

            );

            /*
            * "file: path" -> use for ACTION_VIEW intents
            *
            *  File f = new File(mCurrentPhotoPath);
            *  Uri contentUri = Uri.fromFile(f);
            *
            *
            *
            *     //Decode the image file into a Bitmap sized to fill the View
            *     bmOptions.inJustDecodeBounds = false;
            *     bmOptions.inSampleSize = scaleFactor;
            *  Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
            *  mImageView.setImageBitmap(bitmap);
                       */


            mCurrentPhotoPath = "file" + imageFile.getAbsolutePath();

        } catch (IOException e) {

            return null;

        }

        return imageFile;

    }

}