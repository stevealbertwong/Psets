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
public class HairstyleGridViewAdapter extends BaseAdapter {

    private Context context;
    private final String[] mobileValues;

    public HairstyleGridViewAdapter(Context context, String[] mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;
    }





    // populates view
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;



        gridView = new View(context);

        // get layout from mobile.xml
        gridView = inflater.inflate(R.layout.single_hairstyle, null);

        // set value into textview
        TextView textViewName = (TextView) gridView
                .findViewById(R.id.haricut_style);
        textViewName.setText(mobileValues[position]);

        // set image based on selected text
        ImageView imageView = (ImageView) gridView
                .findViewById(R.id.image_circle);

        String mobile = mobileValues[position];




        if (mobile.equals("波波頭")) {
            imageView.setImageResource(R.drawable.hairstyle_ballball);
        } else if (mobile.equals("染髪")) {
            imageView.setImageResource(R.drawable.image6);
        } else if (mobile.equals("小童")) {
            imageView.setImageResource(R.drawable.image8);
        } else if (mobile.equals("譚詠麟")) {
            imageView.setImageResource(R.drawable.alan1);
        } else if (mobile.equals("許冠傑")) {
            imageView.setImageResource(R.drawable.hui);
        } else if (mobile.equals("陳百強")) {
            imageView.setImageResource(R.drawable.danny);
        } else if (mobile.equals("鄧麗君")) {
            imageView.setImageResource(R.drawable.tang);
        } else if (mobile.equals("梅艷芳")) {
            imageView.setImageResource(R.drawable.mui);
        } else if (mobile.equals("張曼玉")) {
            imageView.setImageResource(R.drawable.maggie);
        } else if (mobile.equals("BillGates,SteveJobs")) {
            imageView.setImageResource(R.drawable.tech);
        } else if (mobile.equals("女生長髪")) {
            imageView.setImageResource(R.drawable.longhair);
        } else if (mobile.equals("女生短髪")) {
            imageView.setImageResource(R.drawable.shorthair);
        } else if (mobile.equals("出街頭")) {
            imageView.setImageResource(R.drawable.image1);
        } else if (mobile.equals("curly")) {
            imageView.setImageResource(R.drawable.hairstyle_curly);
        } else if (mobile.equals("新娘")) {
            imageView.setImageResource(R.drawable.hairstyle_bride);
        } else if (mobile.equals("男生Eason")) {
            imageView.setImageResource(R.drawable.eason1);
        } else if (mobile.equals("男生Eason ")) {
            imageView.setImageResource(R.drawable.eason3);
        } else if (mobile.equals("男生Andy")) {
            imageView.setImageResource(R.drawable.andy3);
        } else if (mobile.equals("Braid")) {
            imageView.setImageResource(R.drawable.hairstyle_braid);
        } else if (mobile.equals("Dope")) {
            imageView.setImageResource(R.drawable.image3);
        } else if (mobile.equals("返工")) {
            imageView.setImageResource(R.drawable.andy3);
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
