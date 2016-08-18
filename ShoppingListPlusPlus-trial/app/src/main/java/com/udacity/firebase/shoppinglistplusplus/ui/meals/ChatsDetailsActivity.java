package com.udacity.firebase.shoppinglistplusplus.ui.meals;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.Firebase;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Chat;
import com.udacity.firebase.shoppinglistplusplus.ui.BaseActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SteveAndrewWong on 8/5/16.
 */
public class ChatsDetailsActivity extends BaseActivity {

    private EditText mEditTextChatMessages;
    private ChatDetailsAdapter chatDetailsAdapter;
    private ListView mListViewChatMessages;
    private Firebase mChatsEncodedEmailRef;
    private String mSelectedUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = this.getIntent();
        mSelectedUserEmail = intent.getStringExtra(Constants.KEY_SELECTED_USER_EMAIL);
        if (mSelectedUserEmail == null) {
            /* No point in continuing without a valid ID. */
            finish();
            return;
        }

        setContentView(R.layout.activity_send_message);


        mEditTextChatMessages = (EditText) findViewById(R.id.edit_text_chat);
        mListViewChatMessages = (ListView) findViewById(R.id.list_view_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        /* Add back button to the action bar */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        // TODO: write to Firebase
        mEditTextChatMessages.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN){

                    String chatMessage = mEditTextChatMessages.getText().toString();
                    if (!chatMessage.equals("")){

                        // get list id
                        mChatsEncodedEmailRef = new Firebase(Constants.FIREBASE_URL_CHATS).child(mEncodedEmail);
                        Firebase newPushRef = mChatsEncodedEmailRef.push();
                        String listId = newPushRef.getKey();

                        HashMap<String, Object> updateChildrenNode = new HashMap<>();

                        Chat mChatPOJO = new Chat(chatMessage, mUserSignUpName);
                        HashMap<String, Object> mChatObject = (HashMap<String, Object>)
                                new ObjectMapper().convertValue(mChatPOJO, Map.class);

                        // multiple write
                        updateChildrenNode.put("/" + Constants.FIREBASE_LOCATION_CHATS + "/" + mEncodedEmail + "/" + listId, mChatObject);
                        updateChildrenNode.put("/" + Constants.FIREBASE_LOCATION_CHATS + "/" + mSelectedUserEmail + "/" + listId, mChatObject);

                        final Firebase firebaseRef = new Firebase(Constants.FIREBASE_URL);
                        firebaseRef.updateChildren(updateChildrenNode);

                    }
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);

                    mEditTextChatMessages.setText("");
                }

                return true;
            }

        });




        // TODO: create list view adapter + initialize
        Firebase chatRef = new Firebase(Constants.FIREBASE_URL_CHATS).child(mEncodedEmail);

        chatDetailsAdapter = new ChatDetailsAdapter(ChatsDetailsActivity.this, Chat.class, R.layout.single_chat, chatRef);
        mListViewChatMessages.setAdapter(chatDetailsAdapter);





//        mEditTextAddFriendEmail.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                /* Get the input after every textChanged event and transform it to lowercase */
//                mInput = mEditTextAddFriendEmail.getText().toString().toLowerCase();
//
//            /* Clean up the old adapter */
//                if (mFriendsAutocompleteAdapter != null) mFriendsAutocompleteAdapter.cleanup();
//            /* Nullify the adapter data if the input length is less than 2 characters */
//                if (mInput.equals("") || mInput.length() < 2) {
//                    mListViewAutocomplete.setAdapter(null);
//
//            /* Define and set the adapter otherwise. */
//                } else {
//                    mFriendsAutocompleteAdapter = new AutocompleteFriendAdapter(ChatsDetailsActivity.this, User.class,
//                            R.layout.single_autocomplete_item, mUsersRef.orderByChild(Constants.FIREBASE_PROPERTY_EMAIL)
//                            .startAt(mInput).endAt(mInput + "~").limitToFirst(5), mEncodedEmail);
//
//                    mListViewAutocomplete.setAdapter(mFriendsAutocompleteAdapter);
//                }
//
//            }
//        });

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (mFriendsAutocompleteAdapter != null) {
//            mFriendsAutocompleteAdapter.cleanup();
//        }
    }



}

