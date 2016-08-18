package com.udacity.firebase.shoppinglistplusplus.ui.activeLists;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingList;
import com.udacity.firebase.shoppinglistplusplus.ui.activeListDetails.ActiveListDetailsActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;


/**
 * A simple {@link Fragment} subclass that shows a list of all shopping lists a user can see.
 *
 * 1. public constructor -> SectionPagerAdapter extends FragmentStatePagerAdapter; -> Fragment getItem(int position)
 * 2. Inflat layout to View: inflater.inflate(R.layout.fragment_shopping_lists, container, false)
 * 3. Click Listener: mListView.setOnItemClickListener -> ShoppingList selectedList = mActiveListAdapter.getItem(position); -> String listId = mActiveListAdapter.getRef(position).getKey();
 * 4. Instantiate Adapter: mActiveListAdapter = new ActiveListAdapter(getActivity(), ShoppingList.class, R.layout.single_active_list, orderedActiveUserListsRef, mEncodedEmail);
 * 5. Set adapter to list view: mListView.setAdapter(mActiveListAdapter);
 *
 */
public class ShoppingListsFragment extends Fragment {
    private String mEncodedEmail;
    private ActiveListAdapter mActiveListAdapter;
    private ListView mListView;





    public ShoppingListsFragment() {
        /* Required empty public constructor */
    }










    /**
     * Create a new instance of fragment and pass bundle with data as fragment's arguments
     * Right now there are not arguments...but eventually there will be. -> creating a new shopping list with encoded email as key
     */
    public static ShoppingListsFragment newInstance(String encodedEmail) {
        ShoppingListsFragment fragment = new ShoppingListsFragment();
        Bundle args = new Bundle();
        args.putString(Constants.KEY_ENCODED_EMAIL, encodedEmail); // "ENCODED_EMAIL"
        fragment.setArguments(args);
        return fragment;
    }









    /**
     * LoginActivty -> mSharedPrefEditor.putString(Constants.KEY_ENCODED_EMAIL, mEncodedEmail).apply();
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mEncodedEmail = getArguments().getString(Constants.KEY_ENCODED_EMAIL);
        }
    }








    // onCreateView returns fragment's root ui view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /**
         * Initialize UI elements -> either this method or setContentView()
         * i guess use inflater instead of setContentView -> fragment, when tab clicked -> onCreateView
         * if intent to an Activity -> setContentView()
         */
        View rootView = inflater.inflate(R.layout.fragment_shopping_lists, container, false); // standard: load GUI layout from XML
        initializeScreen(rootView); // mListView = (ListView) rootView.findViewById(R.id.list_view_active_lists);


        /* ++++++++++++++++++++++++++++++++++++++++++++++++LIKEBUTTON++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        // click logic

        mButtonLike = (Button) findViewById(R.id.button_like);
        mButtonLike.setOnItemClickListener(new AdapterView.OnItemClickListener() { @Override  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Firebase mUserListsLikeCountRef = new Firebase(Constants.FIREBASE_URL_USER_LISTS).child(mEncodedEmail/listId/likeCount)
        muserListsLikeCountRef.addChildEventListener(new ChildEventListener() {@Override public void onDataChange(DataSnapshot dataSnapshot) {
        String likeCount = dataSnapshot.getValue();

        int result = Integer.parseInt(likeCount);
        result += 1;
        String likeCount = Integer.toString(result);
        mUserListsLikeCountRef.setValue(likeCount);




        ++++++++++++++++++++++++++++++++++++++++++++++++LIKEBUTTON++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */




        /**
         * Set interactive bits, such as click events and adapters
         * Fragment -> all click events happen in java not xml
         */
        // new AdapterView...onItemClick()... -> syntatic diarhea: annoymous innerclass object to pass as parameter (BY MARTY)
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // position is the position of the item that got clicked
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                // Adapter build each list item layout -> takes in raw data and creates layout for them
                // ?? there is no getItem() or getRef() + why does it return a model ShoppingList
                // FirebaseListAdapter<ShoppingList>
                ShoppingList selectedList = mActiveListAdapter.getItem(position);

                if (selectedList != null) {


                    // ShoppingListFragment -> ActiveListDetailsActivity
                    Intent intent = new Intent(getActivity(), ActiveListDetailsActivity.class);
                    /* Get the list ID using the adapter's get ref method to get the Firebase
                     * ref and then grab the key.
                     */
                    String listId = mActiveListAdapter.getRef(position).getKey();
                    intent.putExtra(Constants.KEY_LIST_ID, listId);


                    /* Starts an active showing the details for the selected list */
                    startActivity(intent);
                }
            }
        });
        return rootView;
    }










    /**
     * Updates the order of mListView onResume to handle sortOrderChanges properly
     *
     * Called when activity is first created + loaded -> other ongoing actions that should only run when activity is visible on screen
     */
    @Override
    public void onResume() {
        super.onResume();

        // SettingActivity
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortOrder = sharedPref.getString(Constants.KEY_PREF_SORT_ORDER_LISTS, Constants.ORDER_BY_KEY);




        Query orderedActiveUserListsRef;
        Firebase activeListsRef = new Firebase(Constants.FIREBASE_URL_USER_LISTS)
                .child(mEncodedEmail);
        /**
         * Sort active lists by "date created"
         * if it's been selected in the SettingsActivity
         */
        if (sortOrder.equals(Constants.ORDER_BY_KEY)) {
            // Firebase.orderByKey()
            orderedActiveUserListsRef = activeListsRef.orderByKey();
        } else {

            /**
             * Sort active by lists by name or datelastChanged. Otherwise
             * depending on what's been selected in SettingsActivity
             */

            orderedActiveUserListsRef = activeListsRef.orderByChild(sortOrder);
        }









        /**
         * Create the adapter with selected sort order
         */
        mActiveListAdapter = new ActiveListAdapter(getActivity(), ShoppingList.class,
                R.layout.single_active_list, orderedActiveUserListsRef,
                mEncodedEmail);

        /**
         * Set the adapter to the mListView
         */
        mListView.setAdapter(mActiveListAdapter);
    }













    /**
     * Cleanup the adapter when activity is paused.
     */
    @Override
    public void onPause() {
        super.onPause();
        mActiveListAdapter.cleanup();
    }

    /**
     * Link list view from XML
     */
    private void initializeScreen(View rootView) {
        mListView = (ListView) rootView.findViewById(R.id.list_view_active_lists);
    }
}
