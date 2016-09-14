package com.example.steveandrewwong.hairbobo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by SteveAndrewWong on 8/11/16.
 */
public class PostGridViewAdapter extends BaseAdapter {

    private Context context;
    private final String[] mobileValues;
    private final String[] likeHashtag;

    public PostGridViewAdapter(Context context, String[] mobileValues, String[] likeHashtag) {
        this.context = context;
        this.mobileValues = mobileValues;
        this.likeHashtag = likeHashtag;
    }








    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;



        gridView = new View(context);

        // get layout from mobile.xml
        gridView = inflater.inflate(R.layout.single_post, null);

        // set value into textview
        TextView textViewName = (TextView) gridView
                .findViewById(R.id.user_name_location);
        textViewName.setText(mobileValues[position]);


        TextView textViewLike = (TextView) gridView
                .findViewById(R.id.like_hashtag_date);
        textViewLike.setText(likeHashtag[position]);

        // set image based on selected text
        ImageView imageView = (ImageView) gridView
                .findViewById(R.id.image_haircut);




        String mobile = mobileValues[position];

        if (mobile.equals("philip, 旺角")) {
            imageView.setImageResource(R.drawable.beckham);
        } else if (mobile.equals("philip, 旺角 ")) {
            imageView.setImageResource(R.drawable.image2);
        } else if (mobile.equals("winnie, 銅鑼灣")) {
            imageView.setImageResource(R.drawable.andy2);
        } else if (mobile.equals("winnie, 銅鑼灣 ")) {
            imageView.setImageResource(R.drawable.image_guy);
        } else if (mobile.equals("阿明, 旺角")) {
            imageView.setImageResource(R.drawable.hairstyle_bride);
        } else if (mobile.equals("BB, 銅鑼灣")) {
            imageView.setImageResource(R.drawable.image9);
        } else if (mobile.equals("琪哥, 旺角")) {
            imageView.setImageResource(R.drawable.image5);
        } else if (mobile.equals("琪哥, 旺角 ")) {
            imageView.setImageResource(R.drawable.image6);
        }




        return gridView;
    }

    @Override
    public int getCount() {
        return mobileValues.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }
}
