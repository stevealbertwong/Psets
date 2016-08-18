package com.udacity.firebase.shoppinglistplusplus.ui.activeListDetails;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingList;
import com.udacity.firebase.shoppinglistplusplus.model.User;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.HashMap;

/**
 * Base class for {@link DialogFragment}s involved with editing a shopping list.
 *
 * 1. Create a bundle with all shoppinglist info need to change
 * 2. mSharedWith = (HashMap) getArgument().getSerizable(Constants.KEY_SHARED_WITH_USERS) convert Bundle into Instance
 * 3. Get keyboard + Build AlertDialog + inflate the view + get mEditTextForList
 * 4. creates Dialog -> when user type done/okay, call AddListItemDialogFragment to add new item to Firebase ref current shopping list through POJO
 *
 */

public abstract class EditListDialogFragment extends DialogFragment {
    String mListId, mOwner, mEncodedEmail;
    EditText mEditTextForList;
    int mResource;
    HashMap mSharedWith;









    /**
     * Helper method that creates a basic bundle of all of the information needed to change
     * values in a shopping list. -> pass it into EditListNameDialogFragment
     *
     * @param shoppingList The shopping list that the dialog is editing
     * @param resource The xml layout file associated with the dialog
     * @param listId The id of the shopping list the dialog is editing
     * @param encodedEmail The encoded email of the current user
     * @param sharedWithUsers The HashMap containing all users that the current shopping list
     *                        is shared with
     * @return The bundle containing all the arguments.
     */
    protected static Bundle newInstanceHelper(ShoppingList shoppingList, int resource, String listId,
                                              String encodedEmail, HashMap<String, User> sharedWithUsers) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_SHARED_WITH_USERS, sharedWithUsers);
        bundle.putString(Constants.KEY_LIST_ID, listId);
        bundle.putInt(Constants.KEY_LAYOUT_RESOURCE, resource);
        bundle.putString(Constants.KEY_LIST_OWNER, shoppingList.getOwner());
        bundle.putString(Constants.KEY_ENCODED_EMAIL, encodedEmail);
        return bundle;
    }









    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedWith = (HashMap) getArguments().getSerializable(Constants.KEY_SHARED_WITH_USERS);
        mListId = getArguments().getString(Constants.KEY_LIST_ID);
        mResource = getArguments().getInt(Constants.KEY_LAYOUT_RESOURCE);
        mOwner = getArguments().getString(Constants.KEY_LIST_OWNER);
        mEncodedEmail = getArguments().getString(Constants.KEY_ENCODED_EMAIL);
    }















    /**
     * Open Keyboard
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Open the keyboard automatically when the dialog fragment is opened
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }








    protected Dialog createDialogHelper(int stringResourceForPositiveButton) {


        /**
         * Build AlertDialog + inflate the view + get mEditTextForList
         *  */


        // AlertDialog.Builder build AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);

        /* Get the layout inflater -> Inflate the layout, set root ViewGroup to null */
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // mResource = getArguments().getInt(Constants.KEY_LAYOUT_RESOURCE);
        View rootView = inflater.inflate(mResource, null);

        mEditTextForList = (EditText) rootView.findViewById(R.id.edit_text_list_dialog);








        /**
         * Call doListEdit() when user taps "Done" on mEditTextForList
         */
        mEditTextForList.setOnEditorActionListener(new TextView.OnEditorActionListener() {


            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {


                // when user taps "Done"
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    // EditListItemNameDialogFragment -> Adds new item to Firebase ref current shopping list through POJO
                    doListEdit();

                    /**
                     * Close the dialog fragment when done
                     */
                    EditListDialogFragment.this.getDialog().cancel();
                }
                return true;
            }
        });










        /* Inflate and set the layout for the dialog + Pass null as the parent view because its going in the dialog layout */

        // AlertDialog.builder
        builder.setView(rootView)


                /* Add action buttons + Positive Click and Negative Click */
                .setPositiveButton(stringResourceForPositiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // EditListItemNameDialogFragment -> Adds new item to Firebase ref current shopping list through POJO
                        doListEdit();

                        /**
                         * Close the dialog fragment
                         */
                        EditListDialogFragment.this.getDialog().cancel();
                    }
                })
                .setNegativeButton(R.string.negative_button_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        /**
                         * Close the dialog fragment
                         */
                        EditListDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }











    /**
     * Set the EditText text to be the inputted text
     * and put the pointer at the end of the input
     *
     * @param defaultText
     */
    protected void helpSetDefaultValueEditText(String defaultText) {
        mEditTextForList.setText(defaultText);
        mEditTextForList.setSelection(defaultText.length());
    }






    /**
     * Method to be overriden with whatever edit is supposed to happen to the list
     */
    protected abstract void doListEdit();
}
