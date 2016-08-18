package com.udacity.firebase.shoppinglistplusplus.model;

/**
 * Created by SteveAndrewWong on 8/4/16.
 */
public class Instagram {
    private String ownerName;
    private String picture;

    public Instagram(){

    }

    // constructor
    public Instagram (String ownerName, String picture){
        this.ownerName = ownerName;
        this.picture = picture;
    }

    // getter
    public String getOwnerName() {
        return ownerName;
    }

    public String getPicture() {
        return picture;
    }
}
