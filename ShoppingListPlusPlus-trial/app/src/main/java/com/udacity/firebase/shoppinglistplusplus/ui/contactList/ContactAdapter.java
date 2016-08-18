package com.udacity.firebase.shoppinglistplusplus.ui.contactList;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.User;

/**
 * Created by SteveAndrewWong on 8/2/16.
 */
public class ContactAdapter extends FirebaseListAdapter<User> {

    public ContactAdapter(Activity activity, Class<User> modelClass, int modelLayout, Query ref) {
        super(activity, modelClass, modelLayout, ref);
    }

    // View = inflate(R.id.single_user_contact)
    @Override
    protected void populateView(View v, User model) {
        super.populateView(v, model);
        String userName = model.getName();
        String userEmail = model.getEmail();

        TextView textViewUserInfo = (TextView) v.findViewById(R.id.user_info);
        TextView textViewUserEmail = (TextView) v.findViewById(R.id.user_email);

        textViewUserInfo.setText(userName);
        textViewUserEmail.setText("電郵" + userEmail);


    }
}
