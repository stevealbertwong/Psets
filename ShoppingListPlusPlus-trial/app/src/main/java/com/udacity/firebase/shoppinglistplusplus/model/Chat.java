package com.udacity.firebase.shoppinglistplusplus.model;

/**
 * Created by SteveAndrewWong on 8/5/16.
 */
public class Chat {
    private String chat;
    private String ownerName;

    public Chat(){

    }

    // constructor
    public Chat(String chat, String ownerName){
        this.chat = chat;
        this.ownerName = ownerName;
    }

    public String getChat(){return chat;}
    public String getOwnerName() {return ownerName;}
}
