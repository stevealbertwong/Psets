package com.example.steveandrewwong.berkeleyadmissionquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // method 1: setting private instance
    // private int leftNumber;
    // private int rightNumber;
    private int points;

    @Override
    // onCreate runs when Activity gets created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pickNumber();
        points =0;

    }

    public void pickNumber(){
        // update points
        TextView points_tv = (TextView) findViewById(R.id.textView2);
        points_tv.setText("points:" + points);

        // random number
        Random randomNumber = new Random();
        // making sure wont generate same random number
        while (true) {
            int num1 = randomNumber.nextInt(10);
            int num2 = randomNumber.nextInt(10);
            if (num1 != num2) break;
        };
        // view object -> button, textview, edittext + cast the return value to appropriate type
        Button leftButton = (Button) findViewById(R.id.button);
        Button rightButton = (Button) findViewById(R.id.button2);
        String leftNum = String.valueOf(num1); // convert int to string ?? WHY NO toString()

        // pass in string version of int, otherwise NullPointerException crashes the app
        leftButton.setText(leftNum);
        rightButton.setText(String.valueOf(num2));
    };

    // randomly generate button's text
    // convert the button's text into int and compare the 2 buttons
    // when the button is clicked -> compare e.g. if left is clicked and left int > right int + change the random number inside button's text
    // if true, get the int inside TextView point and increase by 1

    public void leftButtomClicked(View view) {

        Button leftButton = (Button) findViewById(R.id.button);
        Button rightButton = (Button) findViewById(R.id.button2);

        // each widget has an associated java object i.e. get() set() methods that corresponds to the properties in Design View
        // getting the button's text -> convert to int
        String left = (String) leftButton.getText();
        int leftNumber = Integer.parseInt(left);
        String right = (String) rightButton.getText();
        int rightNumber = Integer.parseInt(right);

        if (leftNumber > rightNumber){
            points++;
        } else{
            points--;
        }
        pickNumber();
        ;
    }


    public void rightButtomClicked(View view) {
        Button leftButton = (Button) findViewById(R.id.button);
        Button rightButton = (Button) findViewById(R.id.button2);


        String left = (String) leftButton.getText();
        int leftNumber = Integer.parseInt(left);
        String right = (String) rightButton.getText();
        int rightNumber = Integer.parseInt(right);

        if (leftNumber < rightNumber){
            points++;
        } else{
            points--;
        }
        pickNumber();
        ;
    }
}
