package com.vel9studios.levani.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.vel9studios.levani.builditbigger.constants.AppConstants;
import com.vel9studios.levani.jokelib.JokeActivity;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements AsyncResponse {

    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){

        //http://www.tutorialspoint.com/android/android_loading_spinner.htm
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.setDelegate(this);
        endpointsAsyncTask.execute();
    }

    //http://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a
    public void processFinish(ArrayList<String> jokeContent){
        //called in the OnPostExecute of the AsyncTask
        spinner.setVisibility(View.GONE);
        //Once we have the data, hide the spinner and start new intent
        Intent jokeIntent = new Intent(this, JokeActivity.class);
        jokeIntent.putStringArrayListExtra(AppConstants.INTENT_KEY_JOKE, jokeContent);
        startActivity(jokeIntent);
    }
}
