package com.example.steveandrewwong.hairbobo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    GridView gridView;

    static final String[] UserNameLocation = new String[] {
            "philip, 旺角", "philip, 旺角 ","winnie, 銅鑼灣", "winnie, 銅鑼灣 ", "阿明, 旺角", "BB, 銅鑼灣", "琪哥, 旺角", "琪哥, 旺角 " };

    static final String[] LikeHastag = new String[] {
            "Like:56 #beckham Aug-2016", "Like:56 #beckham Aug-2016","Like:86 #andylau Aug-2016", "Like:86 #andylau Aug-2016", "Like:56 #bride", "Like:56 #girlhair", "Like:56 #dye", "Like:56 #curly" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Other hair dresser options?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




        gridView = (GridView) findViewById(R.id.gridView_photo);
        gridView.setAdapter(new PostGridViewAdapter(this, UserNameLocation, LikeHastag));

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_trend) {
            Intent intent = new Intent(MainActivity.this, HairCategoryActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_personalbarber) {
            Intent intent = new Intent(MainActivity.this, UberActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
