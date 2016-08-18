package com.udacity.firebase.shoppinglistplusplus.ui.sharing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingList;
import com.udacity.firebase.shoppinglistplusplus.model.User;
import com.udacity.firebase.shoppinglistplusplus.ui.BaseActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.HashMap;

/**
 * ShareWith -> means copy current list to other user's userLists + ShareWith index
 * reason write multiple lists: adapter take only ONE (Firebase Ref and Model/DataStructure) instantiate and populate listView
 *
 * Allows for you to check and un-check friends that you share the current list with
 *
 * 1. setContentView + getIntent()
 * 2. initializeScreen()
 *
 * 3. for loop to copy entire ShareWith node into mSharedWithUsers HashMap then updates it
 *      mFriendAdapter.setShoppingList(mShoppingList);
 *
 * 4. for (DataSnapshot currentUser : dataSnapshot.getChildren()) { mSharedWithUsers.put(currentUser.getKey(), currentUser.getValue(User.class));
 *      mFriendAdapter.setSharedWithUsers(mSharedWithUsers);
 *
 * 5. public void onAddFriendPressed(View view) new Intent(ShareListActivity.this, AddFriendActivity.class);
 *
 *
 * NOT checking SharedWith node to populateView for a user that has permission BUT ATOMIC WRITE
 * -> double loop: loop through every listId in SharedWith
 * for (DatasnapShot user = ref.getChildren) User.getKey() // return each SharedWith mEncodedEmail
 *
 *
 */
public class ShareListActivity extends BaseActivity {
    private static final String LOG_TAG = ShareListActivity.class.getSimpleName();
    private FriendAdapter mFriendAdapter;
    private ListView mListView;
    private ShoppingList mShoppingList;
    private String mListId;
    private Firebase mActiveListRef, mSharedWithRef;
    private ValueEventListener mActiveListRefListener, mSharedWithListener;
    private HashMap<String, User> mSharedWithUsers;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_list);



        /* Get the push ID from the extra passed by ActiveListDetailsActivity */
        Intent intent = this.getIntent();
        mListId = intent.getStringExtra(Constants.KEY_LIST_ID);

        if (mListId == null) {
            /* No point in continuing without a valid ID. */
            finish();
            return;
        }



        /**
         * Link layout elements from XML and setup the toolbar
         */
        initializeScreen();







        /**
         * Copy wongkwunkit@gmail.com/mListId to xxx.gmail.com/mListId
         */
        Firebase currentUserFriendsRef = new Firebase(Constants.FIREBASE_URL_USER_FRIENDS).child(mEncodedEmail);
        mActiveListRef = new Firebase(Constants.FIREBASE_URL_USER_LISTS).child(mEncodedEmail).child(mListId);
        mSharedWithRef = new Firebase (Constants.FIREBASE_URL_LISTS_SHARED_WITH).child(mListId);








        /**
         * Getting most updated value from ActiveList into FriendAdapter -> copy entire ShoppingList node into another user's mEncodedEmail
         *
         * Add ValueEventListeners to Firebase references
         * to control get data and control behavior and visibility of elements
         */

        mActiveListRefListener = mActiveListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ShoppingList shoppingList = dataSnapshot.getValue(ShoppingList.class);

                /**
                 * Saving the most recent version of current shopping list into mShoppingList and pass it to setShoppingList()
                 * finish() the activity otherwise
                 */
                if (shoppingList != null) {
                    mShoppingList = shoppingList;
                    mFriendAdapter.setShoppingList(mShoppingList);

                } else {
                    finish();
                }
            }







            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(LOG_TAG,
                        getString(R.string.log_error_the_read_failed) +
                                firebaseError.getMessage());
            }
        });





        // new Firebase (Constants.FIREBASE_URL_LISTS_SHARED_WITH).child(mListId);
        mSharedWithListener = mSharedWithRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {




                mSharedWithUsers = new HashMap<String, User>();

                // a for loop to copy entire ShareWith node into mSharedWithUsers HashMap then updates it
                // for every User in DataSnapshot
                // dataSnapshot.getChildren() -> get all the children of listId
                for (DataSnapshot currentUser : dataSnapshot.getChildren()) {
                    mSharedWithUsers.put(currentUser.getKey(), currentUser.getValue(User.class));
                }
                mFriendAdapter.setSharedWithUsers(mSharedWithUsers);


            }








            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(LOG_TAG,
                        getString(R.string.log_error_the_read_failed) +
                                firebaseError.getMessage());
            }
        });








        /**
         * Set interactive bits, such as click events/adapters
         */
        mFriendAdapter = new FriendAdapter(ShareListActivity.this, User.class,
                R.layout.single_user_item, currentUserFriendsRef, mListId);

        /* Set adapter for the listView */
        mListView.setAdapter(mFriendAdapter);
    }








    /**
     * Cleanup the adapter when activity is destroyed
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        /* Set adapter for the listView */
        mFriendAdapter.cleanup();
        mActiveListRef.removeEventListener(mActiveListRefListener);
        mSharedWithRef.removeEventListener(mSharedWithListener);
    }





    /**
     * Link layout elements from XML and setup the toolbar
     */
    public void initializeScreen() {
        mListView = (ListView) findViewById(R.id.list_view_friends_share);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        /* Add back button to the action bar */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }







    /**
     * Launch AddFriendActivity to find and add user to current user's friends list
     * when the button AddFriend is pressed
     */
    public void onAddFriendPressed(View view) {
        Intent intent = new Intent(ShareListActivity.this, AddFriendActivity.class);
        startActivity(intent);
    }
}
