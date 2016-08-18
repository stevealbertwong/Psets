package com.example.steveandrewwong.a193login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;


import stanford.andriodlib.*;

import static java.lang.Math.log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this); // set android context to be this activity
    }

    public void loginClick(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name);
        final String inputEmail = nameEditText.getText().toString();
        EditText passwordEditText = (EditText) findViewById(R.id.password);
        final String inputPassword = passwordEditText.getText().toString(); // if you want to look at things outside inner nested object

        // CREATING FIREBASE OBJECT
        // look up this person's password in Firebase
        // 1st step: figure out which part of database to go
        Firebase firebase = new Firebase("https://amber-inferno-3012.firebaseio.com/");
        Firebase fbName = firebase.child("simpsons").child("students").child("123").child("name"); // return a firebase
        Firebase fbAuth = firebase.child("database/table/auth/" + inputEmail); // table to be created

        // SETTING DATA
        // once a new value is set -> on complete callback
        fbName.setValue("d-dog", new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        // TODO
                    }
                });

        // RETRIEVING EXISTING DATA
        // must grab the Firebase object for that data + add listener + bind eventhandler -> fb no getValue() method
        // DataSnapshot + addChildEventListener -> notified/snapshot initially and on state changes
        // DataSnapshot: optimized for FireChat, not sending http get/post request -> emphasize their skills in cloud
        //
        fbName.addChildEventListener(new ChildEventListener() {
            @Override

            // Datasnapshot when initialized
            // DataSnapshot will get called multiple times, one for each field e.g. log/toast (username, password)
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String dsnapKey = dataSnapshot.getKey(); // return 4 keys
                if (dsnapKey.equals("password")){
                    String dsnapValue = (String) dataSnapshot.getValue(); // casting Object to String + return 4 values

                    // how to go one level deeper if the key matches ??

                    // login in successfully
                    if (inputPassword.equals(dsnapValue)){
                    // when oneactivity launches another activity -> exchange information e.g. login users' grades
                        Intent intent = new Intent(MainActivity.this, GradesActivity.class);
                        intent.putExtra("inputEmail", inputEmail); // pass along login person's email
                        // startActivity(GradesActivity.class); // show grades, login content
                        startActivity(intent); // starts grade activity tih inputEmail as parameters

                    } else{
                        // toast("incorrect username or password"); // stanford lib
                    }
                }
                // if dsnapKey = "password" -> if dsnapValue =  -> login in content


                // log(username, password); // SELECT username, password FROM students where id = 123


            }

            // Datasnapshot when data changes
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        }); // end of fbName.addChildEventListener(new ChildEventListener()

        // Querying Data
        Firebase gradesTable = firebase.child("simpsons/grades"); // get all the grades
        // sort grades by email, output only the login grade
        Query query = gradesTable.orderByChild("student_email").equalTo(inputEmail);

        // query is similar to firebase -> addChildEventListener to retrieve data
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        })


    }
}
