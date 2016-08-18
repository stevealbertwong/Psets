package com.example.steveandrewwong.hairbobo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

/**
 * Created by SteveAndrewWong on 8/11/16.
 */
public class HairCategoryActivity extends AppCompatActivity {

    GridView gridView;

    static final String[] HAIR_CATEGORY = new String[] {"波波頭", "染髪", "小童", "譚詠麟", "許冠傑","陳百強", "鄧麗君", "梅艷芳",
            "張曼玉", "BillGates,SteveJobs", "女生長髪", "女生短髪", "出街頭", "curly", "新娘", "男生Eason", "男生Eason ", "男生Andy", "Dope", "Braid", "返工"  };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hairstyle_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        gridView = (GridView) findViewById(R.id.gridView_hairstyle);

        gridView.setAdapter(new HairstyleGridViewAdapter(this, HAIR_CATEGORY));

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
            Intent intent = new Intent(HairCategoryActivity.this, HairCategoryActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_personalbarber) {
            Intent intent = new Intent(HairCategoryActivity.this, UberActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
