package com.udacity.firebase.shoppinglistplusplus.ui.activeLists;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingList;
import com.udacity.firebase.shoppinglistplusplus.model.User;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

/**
 * Populates the R.layout.list_view_active_lists inside R.layout.fragment_shopping_lists listView
 * All the logic is inside ShoppingListsFragment
 *
 * Populates View
 * 1. get all the textViews
 * 2. get all the firebase -> list.getOwner();
 * 3. textView.setText();
 *
 */
public class ActiveListAdapter extends FirebaseListAdapter<ShoppingList> {
    private String mEncodedEmail;



    /**
     * Public constructor that initializes private instance variables when adapter is created
     */
    // ShoppingListFragment
    // mActiveListAdapter = new ActiveListAdapter(getActivity(), ShoppingList.class, R.layout.single_active_list, orderedActiveUserListsRef, mEncodedEmail
    // Query orderedActiveUserListsRef = Firebase activeListsRef.orderByKey();
    // you could pass Firebase or Query as ref for actual database: e.g. a Firebase ref to FIREBASE_URL_ACTIVE_LISTS in sorted order
    public ActiveListAdapter(Activity activity, Class<ShoppingList> modelClass, int modelLayout,
                             Query ref, String encodedEmail) {
        super(activity, modelClass, modelLayout, ref);
        this.mEncodedEmail = encodedEmail;
        this.mActivity = activity;
    }








    /**
     * Protected method that populates the view attached to the adapter (list_view_active_lists)
     * with items inflated from single_active_list.xml
     * populateView also handles data changes and updates the listView accordingly
     *
     * 1. populates textViewUsersShopping
     * 2. populates textViewCreatedByUser
     *
     * Backing Android ListView with a Firebase Location -> View is an inflated version of layout
     *
     * Each time the data at the given Firebase location changes, this method will be called for each item that needs to be displayed
     * e.g.
     *
     *     DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
     *     ListAdapter adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class, android.R.layout.two_line_list_item, mRef)
     *     {
     *         protected void populateView(View view, ChatMessage chatMessage, int position)
     *         {
     *             ((TextView)view.findViewById(android.R.id.text1)).setText(chatMessage.getName());
     *             ((TextView)view.findViewById(android.R.id.text2)).setText(chatMessage.getMessage());
     *         }
     *     };
     *     listView.setListAdapter(adapter);
     *
     */
    @Override
    protected void populateView(View view, ShoppingList list) {
        // mActiveListAdapter = new ActiveListAdapter(getActivity(), ShoppingList.class, R.layout.single_active_list, orderedActiveUserListsRef, mEncodedEmail);
        // mListView.setAdapter(mActiveListAdapter);
        // ShoppingList selectedList = mActiveListAdapter.getItem(position);
        // String listId = mActiveListAdapter.getRef(position).getKey();
        TextView textViewListName = (TextView) view.findViewById(R.id.text_view_list_name);
        final TextView textViewCreatedByUser = (TextView) view.findViewById(R.id.text_view_created_by_user);
        final TextView textViewUsersShopping = (TextView) view.findViewById(R.id.text_view_people_shopping_count);

        /* ++++++++++++++++++++++++++++++++++++++++++++++++LIKEBUTTON++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        TextView textViewLikeCount = (TextView) view.findViewById(R.id.text_view_like_count);
        int likeCount = list.getCount();
        textViewLikeCount.setText(likeCount);

         ++++++++++++++++++++++++++++++++++++++++++++++++LIKEBUTTON++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

        // Firebase activeListsRef = new Firebase(Constants.FIREBASE_URL_USER_LISTS).child(mEncodedEmail);
        // owner = mEncodedEmail
        // AddListDialogFragment -> Utils.updateMapForAllWithValue(null, listId, mEncodedEmail,updateShoppingListData, "", shoppingListMap);
        String ownerEmail = list.getOwner();



        /* Set the list name and owner */
        textViewListName.setText(list.getListName());

        /**
         * Show "1 person is shopping" if one person is shopping
         * Show "N people shopping" if two or more users are shopping
         * Show nothing if nobody is shopping
         */
        if (list.getUsersShopping() != null) {
            int usersShopping = list.getUsersShopping().size();

            // R.string.person_shopping vs R.string.people_shopping ??
            // textView.setText (char[] text, int start, int length)

            // "%d person shopping"
            if (usersShopping == 1) {
                textViewUsersShopping.setText(String.format(
                        mActivity.getResources().getString(R.string.person_shopping),
                        usersShopping));
            } else {
                textViewUsersShopping.setText(String.format(
                        mActivity.getResources().getString(R.string.people_shopping),
                        usersShopping));
            }
        } else {
            /* otherwise show nothing */
            textViewUsersShopping.setText("");
        }









        /**
         * Set "Created by" text to "You" if current user is owner of the list
         * Set "Created by" text to userName if current user is NOT owner of the list
         */

        // shoppingList.getOwner()
        if (ownerEmail != null) {

            if (ownerEmail.equals(mEncodedEmail)) {
                textViewCreatedByUser.setText(mActivity.getResources().getString(R.string.text_you));

            } else {

                Firebase userRef = new Firebase(Constants.FIREBASE_URL_USERS).child(ownerEmail);

                /* From Firebase get the user's name */
                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // getValue(User.class) is to check if it is null VS user.getName() to actually get the value
                        User user = dataSnapshot.getValue(User.class);

                        if (user != null) {
                            textViewCreatedByUser.setText(user.getName());
                        }
                    }





                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Log.e(mActivity.getClass().getSimpleName(),
                                mActivity.getString(R.string.log_error_the_read_failed) +
                                        firebaseError.getMessage());
                    }
                });
            }
        }

    }
}
