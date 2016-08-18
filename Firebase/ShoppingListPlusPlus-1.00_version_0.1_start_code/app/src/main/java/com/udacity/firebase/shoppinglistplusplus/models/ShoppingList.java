package com.udacity.firebase.shoppinglistplusplus.models;

/**
 * Created by SteveAndrewWong on 7/6/16.
 */

// TODO: POJO-> convert JavaObject into Firebase Database JSON -> then take back out JavaObject
// TODO: you could also use HashMap<String,Object>(), not recommended as easy to forget key value pair
// TODO: if accidentally forget getter -> failed to debounce/bound to type error
// TODO: -> Firebase underlying built-in serialization library Jackson Object Mapping Library cannot turn Firebase data into JavaObject/POJO

public class ShoppingList {
    private String listName;
    private String owner;

    // TODO: instance variable must match child's key names and type -> i.e. key and value has to be JSON

    // TODO: public empty constructor
    public ShoppingList(){

    };

    // Constructor to create new node with value passed in
    public ShoppingList(String owner, String listName) {
        this.owner = owner; // value
        this.listName = listName; // value
    };
    // TODO: public getter for every variable


    public String getListName() {
        return listName;
    };

    public String getOwner() {
        return owner;
    };
}
