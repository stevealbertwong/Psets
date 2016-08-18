package com.udacity.firebase.shoppinglistplusplus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firebase.client.ServerValue;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.HashMap;

/**
 * Defines the data structure for both Active and Archived ShoppingList objects.
 *
 *
 *
 * AddListDialogFragment -> Utils.updateMapForAllWithValue(null, listId, mEncodedEmail, updateShoppingListData, "", shoppingListMap);
 */

public class ShoppingList {
    private String listName;
    private String owner;
    private HashMap<String, Object> timestampLastChanged;
    private HashMap<String, Object> timestampCreated;
    private HashMap<String, Object> timestampLastChangedReverse;
    private HashMap<String, User> usersShopping;

    /* ++++++++++++++++++++++++++++++++++++++++++++++++LIKEBUTTON++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     private String likeCount;
    ++++++++++++++++++++++++++++++++++++++++++++++++LIKEBUTTON++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */



    /**
     * Required public constructor
     */
    public ShoppingList() {
    }




    /**
     * Use this constructor to create new ShoppingLists.
     * Takes shopping list listName and owner. Set's the last
     * changed time to what is stored in ServerValue.TIMESTAMP
     *
     * @param listName
     * @param owner
     */

    // takes 3 parameters -> store 6 instances -> their value from somewhere else
    // AddListDialogFragment -> ShoppingList newShoppingList = new ShoppingList(userEnteredName, mEncodedEmail, timestampCreated);

    public ShoppingList(String listName, String owner, HashMap<String, Object> timestampCreated /* +++LIKEBUTTON+++ int likeCount */) {
        this.listName = listName;
        this.owner = owner;


        this.timestampCreated = timestampCreated;

        /* ++++++++++++++++++++++++++++++++++++++++++++++++LIKEBUTTON++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        this.likeCount = likeCount;
        ++++++++++++++++++++++++++++++++++++++++++++++++LIKEBUTTON++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */


        // Whenever you're trying to set the "current time" on Firebase you should pass Firebase the Map constant ServerValue.TIMESTAMP
        HashMap<String, Object> timestampNowObject = new HashMap<String, Object>();
        // Firebase changes ServerValue.TIMESTAMP Map constant to a long value representing the current time the data was recieved and saved in Firebase
        // the client side timestamp can be pretty inaccurate in relation to when the data was actually recorded in the server
        timestampNowObject.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampLastChanged = timestampNowObject;

        this.timestampLastChangedReverse = null;
        this.usersShopping = new HashMap<>();
    }






    // ++++++++LIKEBUTTON public String getLikeCount() { return likeCount; }


    public String getListName() {
        return listName;
    }

    public String getOwner() {
        return owner;
    }

    public HashMap<String, Object> getTimestampLastChanged() {
        return timestampLastChanged;
    }

    public HashMap<String, Object> getTimestampCreated() {
        return timestampCreated;
    }

    public HashMap<String, Object> getTimestampLastChangedReverse() {
        return timestampLastChangedReverse;
    }




    @JsonIgnore
    public long getTimestampLastChangedLong() {

        return (long) timestampLastChanged.get(Constants.FIREBASE_PROPERTY_TIMESTAMP); // "timestamp"
    }



    @JsonIgnore
    public long getTimestampCreatedLong() {
        return (long) timestampLastChanged.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
    }

    @JsonIgnore
    public long getTimestampLastChangedReverseLong() {

        return (long) timestampLastChangedReverse.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
    }

    public HashMap getUsersShopping() {
        return usersShopping;
    }





    public void setTimestampLastChangedToNow() {
        HashMap<String, Object> timestampNowObject = new HashMap<String, Object>();
        timestampNowObject.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampLastChanged = timestampNowObject;
    }


}

