package com.udacity.firebase.shoppinglistplusplus.ui.activeLists;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.models.ShoppingList;


/**
 * A simple {@link Fragment} subclass that shows a list of all shopping lists a user can see.
 * Use the {@link ShoppingListsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingListsFragment extends Fragment {
    // private since every activity is likely to have a ListView
    private ListView mListView;
    // declare here since it is used in private void initialize screen
    private TextView mTextViewListName;
    private TextView mTextViewOwnerName;

    public ShoppingListsFragment() {
        /* Required empty public constructor */
    }

    /**
     * Create fragment and pass bundle with data as it's arguments
     * Right now there are not arguments...but eventually there will be.
     */
    public static ShoppingListsFragment newInstance() {
        ShoppingListsFragment fragment = new ShoppingListsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * Initalize UI elements
         */
        View rootView = inflater.inflate(R.layout.fragment_shopping_lists, container, false);
        initializeScreen(rootView);

        // TODO: attach listener to ref to get data stored at listName child node of root node
        // TODO: x constant ping + asyn adapter + asyn taks thread request respond model -> datasnapshot + listener: firebase pings phones w connected database
        // listName child node reference
        Firebase refActiveList = new Firebase("https://shoppingplusplus-f6a38.firebaseio.com/").child("ActiveList");

        // see Marty's example: addChildEventListener
        // attach listener to a specific node
        // if attach to a parent node, any changes in child node will trigger listener code -> send parent node + all its children
        // Firebase SDK deals with all threading and asychonizity
        // ValueEventListener methods are called on the main or UI thread -> dont put expensive operations, otherwise slow
        refActiveList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // dataSnapshot: listName child node, a read-only copy
                // listNameValue is the String user enter in AddListDialogFragment
//                String listNameValue = (String) dataSnapshot.getValue();
//                mTextViewListName.setText(listNameValue);

                // deserialize datasnapshot data into shoppinglist
                // getValue in ShoppingList.class format
                ShoppingList shoppingList = dataSnapshot.getValue(ShoppingList.class);

                if (shoppingList != null){
                    mTextViewOwnerName.setText(shoppingList.getOwner());
                    mTextViewListName.setText(shoppingList.getListName());

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        /**
         * Set interactive bits, such as click events and adapters
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * TODO: Link layout elements from XML
     */
    private void initializeScreen(View rootView) {
        // rootView: inflater.inflate(R.layout.fragment_shopping_lists, container, false)
        // since fragment_shopping_lists.xml includes single_active_list.xml <include layout="@layout/single_active_list" />
        mListView = (ListView) rootView.findViewById(R.id.list_view_active_lists);
        mTextViewListName = (TextView) rootView.findViewById(R.id.text_view_list_name);
        mTextViewOwnerName = (TextView) rootView.findViewById(R.id.created_by);



    }
}
