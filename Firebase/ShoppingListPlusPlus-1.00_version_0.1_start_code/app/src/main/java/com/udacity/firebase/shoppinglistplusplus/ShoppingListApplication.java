package com.udacity.firebase.shoppinglistplusplus;

// TODO: Application is for Global setup, more global than Activity

import com.firebase.client.Firebase;

/**
 * Includes one-time initialization of Firebase related code
 */
public class ShoppingListApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // TODO: let Firebase library knows Android context, Application and Activity are both context
        Firebase.setAndroidContext(this);
    }

}