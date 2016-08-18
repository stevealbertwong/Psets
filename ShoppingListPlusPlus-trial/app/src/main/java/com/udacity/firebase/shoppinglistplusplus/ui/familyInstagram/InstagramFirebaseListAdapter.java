package com.udacity.firebase.shoppinglistplusplus.ui.familyInstagram;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Instagram;

/**
 * Created by SteveAndrewWong on 8/4/16.
 */
public class InstagramFirebaseListAdapter extends FirebaseListAdapter<Instagram> {
    private String mUserSignUpName;

    public InstagramFirebaseListAdapter(Activity activity, Class<Instagram> modelClass, int modelLayout, Query ref, String mUserSignUpName) {
        super(activity, modelClass, modelLayout, ref);
        this.mUserSignUpName = mUserSignUpName;
    }

    @Override
    protected void populateView(View v, Instagram model) {
        super.populateView(v, model);
        String ownerName = model.getOwnerName();
        String imageBase64 = model.getPicture();

        // String to Byte to Byte Array to Bitmap
        byte[] imageBitArray = Base64.decode(imageBase64.getBytes(), Base64.DEFAULT);
        Bitmap imageBitMap = BitmapFactory.decodeByteArray(imageBitArray, 0, imageBitArray.length);

        ImageView mImageView = (ImageView) v.findViewById(R.id.image_view_instagram);
        TextView textView = (TextView) v.findViewById(R.id.text_view_instagram);
        textView.setText("作者：" + ownerName);
        mImageView.setImageBitmap(imageBitMap);

    }
}
