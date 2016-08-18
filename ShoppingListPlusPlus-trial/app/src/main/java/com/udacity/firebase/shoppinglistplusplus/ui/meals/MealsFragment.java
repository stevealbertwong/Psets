package com.udacity.firebase.shoppinglistplusplus.ui.meals;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.User;
import com.udacity.firebase.shoppinglistplusplus.ui.contactList.ContactAdapter;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;


/**
 * A simple {@link Fragment} subclass which shows all of the meals in the Firebase database
 * Use the {@link MealsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealsFragment extends Fragment {
    private ListView mListView;
    private String mEncodedEmail;
    ContactAdapter mContactListAdapter;

    /**
     * Create fragment and pass bundle with data as its' arguments
     */
    public static MealsFragment newInstance(String mEncodedEmail) {
        MealsFragment fragment = new MealsFragment();
        Bundle args = new Bundle();
        args.putString(Constants.KEY_ENCODED_EMAIL, mEncodedEmail);
        fragment.setArguments(args);
        return fragment;
    }

    public MealsFragment() {
        /* Required empty public constructor*/
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {super.onActivityCreated(savedInstanceState);}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Return the arguments supplied to setArguments(Bundle), if any.
        mEncodedEmail = getArguments().getString(Constants.KEY_ENCODED_EMAIL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contact_lists, container, false);
        mListView = (ListView) rootView.findViewById(R.id.list_view_user_contact);


        /**
         * Set interactive bits, such as click events/adapters
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                User selectedUser = mContactListAdapter.getItem(position);
                if (selectedUser != null) {
                    Intent intent = new Intent(getActivity(), ChatsDetailsActivity.class);
                    String selectedUserEmail = mContactListAdapter.getRef(position).getKey();
                    intent.putExtra(Constants.KEY_SELECTED_USER_EMAIL, selectedUserEmail);
                    startActivity(intent);
                }

            }
        });

        return rootView;
    }





    @Override
    public void onResume() {

        super.onResume();
        Firebase contactListRef = new Firebase(Constants.FIREBASE_URL_USERS);

        //TODO: create adapter + listView.setView
        // getActivity() in a Fragment returns the Activity the Fragment is currently associated
        mContactListAdapter = new ContactAdapter(getActivity(), User.class,
                R.layout.single_user_contact, contactListRef);
        mListView.setAdapter(mContactListAdapter);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

//    private void initializeScreen(View rootView) {
//        mListView = (ListView) rootView.findViewById(R.id.list_view_meals_list);
//        View footer = getActivity().getLayoutInflater().inflate(R.layout.footer_empty, null);
//        mListView.addFooterView(footer);
//    }
}
