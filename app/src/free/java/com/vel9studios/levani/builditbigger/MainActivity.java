package com.vel9studios.levani.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.vel9studios.levani.builditbigger.constants.AppConstants;
import com.vel9studios.levani.jokelib.JokeActivity;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements AsyncResponse {

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //https://developers.google.com/admob/android/interstitial?hl=en
        mInterstitialAd = new InterstitialAd(this);
        //hard-coded "test" interstitial add id
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });

        requestNewInterstitial();
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

        //https://developers.google.com/admob/android/interstitial?hl=en
        if (mInterstitialAd.isLoaded())
            mInterstitialAd.show();

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.setDelegate(this);
        endpointsAsyncTask.execute();
    }

    //http://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a
    public void processFinish(ArrayList<String> jokeContent){

        Intent jokeIntent = new Intent(this, JokeActivity.class);
        jokeIntent.putStringArrayListExtra(AppConstants.INTENT_KEY_JOKE, jokeContent);
        startActivity(jokeIntent);
    }

    //https://developers.google.com/admob/android/interstitial?hl=en
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
