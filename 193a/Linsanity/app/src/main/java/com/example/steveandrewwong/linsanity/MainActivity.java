package com.example.steveandrewwong.linsanity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sunnyClick(View view) {
        // view is a reference to the widget that gets clicked on
        // toggle between sunny's picture

        // if 1st button is selected -> show 1st pic
        ImageView imageView = (ImageView) findViewById(R.id.view);

        // ask the parameter view which widget are you
        if (view.getId() == R.id.sunny1){
            imageView.setImageResource(R.drawable.sunny);
        } else if (view.getId() == R.id.sunny2){
            imageView.setImageResource(R.drawable.sunny2);
        } else if (view.getId() == R.id.sunny3){
            imageView.setImageResource(R.drawable.sunny3);

            // this refers to the screen yo uwant to display
            Toast.makeText(MainActivity.this, "I love sunny so much.", Toast.LENGTH_SHORT).show();
        };

    }

    public void okClick(View view) {
        EditText et = (EditText) findViewById(R.id.edit_text);
        String s= et.getText().toString(); // returns editable you need to convert to string
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }
}
