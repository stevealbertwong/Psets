package com.example.steveandrewwong.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    // 1st: Array -> 2nd ArrayAdapter -> 3rd ListView.setAdapter
    // dictionary, list of words in JSON format
    private static final String [] WORDS ={
            "abate", "to lessen to subside",
            "abeyance", "suspended action",
            "abjure", "promise or swear ot give up"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // filter out the elements, odd is word, even is definition
    }

    // Syntatic diarrhea
    // java does not pass the method as a parameter -> anonymous inner classes
    // i.e. make an object that contains the function and pass that object as the parameter
    // new AdapterView.OnItemClickListener(){public void onItemClick}

    // 1st method position *2+1 to get the definition
    //
}
