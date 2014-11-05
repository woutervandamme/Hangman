package com.example.wouter.test.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wouter.test.R;

import java.util.ArrayList;

public class ThemeActivity extends Activity{

    public ArrayList<String> themas;
    private String selectedTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_chooser);

        ListView themesList=(ListView)findViewById(R.id.themes_list);
        Bundle extras = getIntent().getExtras();

        themas = extras.getStringArrayList("themas");

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, themas);

        themesList.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        themesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3){
                selectedTheme =themas.get(position);
                Intent i = new Intent(ThemeActivity.this, MainActivity.class);
                i.putExtra("thema", selectedTheme);

                startActivity(i);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hello_you, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
