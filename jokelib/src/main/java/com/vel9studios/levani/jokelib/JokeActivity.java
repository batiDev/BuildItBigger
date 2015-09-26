package com.vel9studios.levani.jokelib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class JokeActivity extends AppCompatActivity {

    private static final String INTENT_KEY_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        if (getIntent() != null){
            Intent jokeIntent = getIntent();
            if (jokeIntent.hasExtra(INTENT_KEY_JOKE)){

                ArrayList<String> jokeContent = jokeIntent.getStringArrayListExtra(INTENT_KEY_JOKE);

                FragmentManager fm = getSupportFragmentManager();

                //if you added fragment via layout xml
                JokeActivityFragment fragment = (JokeActivityFragment)fm.findFragmentById(R.id.fragment_joke);
                fragment.onDataUpdated(jokeContent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_joke, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}