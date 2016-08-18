package com.udacity.firebase.shoppinglistplusplus.ui.familyInstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Instagram;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

/**
 * Created by SteveAndrewWong on 7/27/16.
 */
public class InstagramFragment extends Fragment {
    ImageView mImageView;
    private ListView mListView;
    FirebaseListAdapter<Instagram> mInstagramFirebaseListAdapter;
    private String mUserSignUpName;


//    Button mButtomTakePicture;
//    Button mButtomGetPicture;
//    private static final int REQUEST_CODE_TO_TAKEPICTURE = 1;
//    private static final int REQUEST_CODE_TO_GETPICTURE = 2;
//    private Bitmap bitmap;

    public InstagramFragment(){

    }

    public static InstagramFragment newInstance(String mUserSignUpName){
        InstagramFragment fragment = new InstagramFragment();
        // the arguments supplied here will be retained across fragment destroy and creation.
        Bundle args = new Bundle();
        args.putString("USERSIGNUPNAME", mUserSignUpName);
        fragment.setArguments(args);
        /* @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: fragment = InstagramFragment.newInstance()
                break; }
                return fragment;}
         */
        return fragment;
    }





    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserSignUpName = getArguments().getString("USERSIGNUPNAME");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_instagram, container, false);
        mImageView = (ImageView) rootView.findViewById(R.id.image_view_1);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(getActivity(), CameraActivity.class);
                startActivity(takePictureIntent);
            }


        });

        // TODO: initialize a list view to display pictures from Firebase
        mListView = (ListView) rootView.findViewById(R.id.list_view_instagram);



        return rootView;
    }




    @Override
    public void onResume() {
        super.onResume();

        Firebase instagramRef = new Firebase(Constants.FIREBASE_URL_INSTAGRAM);
        mInstagramFirebaseListAdapter = new InstagramFirebaseListAdapter(getActivity(), Instagram.class, R.layout.single_instagram, instagramRef, mUserSignUpName);
        mListView.setAdapter(mInstagramFirebaseListAdapter);







    }

    @Override
    public void onPause() {
        super.onPause();
    }






}
