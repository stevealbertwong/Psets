package com.udacity.firebase.shoppinglistplusplus.ui.familyInstagram;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.firebase.shoppinglistplusplus.R;

/**
 * Created by SteveAndrewWong on 8/4/16.
 */

// ViewHolder is just an empty constructor that is initialized in onCreatedViewHolder in RecyclerView.Adapter
public class InstagramViewHolder extends RecyclerView.ViewHolder {
    private ImageView mInstagramView;
    private TextView mInstagramTextView;


    // itemView is inflate(layout) of item -> new ViewHolder(inflat(layout))
    public InstagramViewHolder(View itemView) {
        super(itemView);
        mInstagramView = (ImageView) itemView.findViewById(R.id.image_view_instagram);
        mInstagramTextView = (TextView) itemView.findViewById(R.id.text_view_instagram);

    }


}
