package com.vel9studios.levani.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.vel9studios.levani.builditbigger.jokecloud.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.vel9studios.levani.builditbigger.constants.AppConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class EndpointsAsyncTask extends AsyncTask<String, Void, String> {

    private static MyApi myApiService = null;
    private static final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();

    private AsyncResponse delegate;

    public void setDelegate(AsyncResponse context){
        delegate = context;
    }

    @Override
    protected String doInBackground(String... params) {

        // core doInBackground code from https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(AppConstants.LOCAL_SERVER_ADDRESS);
            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        ArrayList<String> jokeContent = new ArrayList<>();
        try {

            JSONObject jokeObj = new JSONObject(result);
            JSONArray jArray = jokeObj.getJSONArray(AppConstants.KEY_API_JOKE);

            //http://stackoverflow.com/a/17037364
            for (int i = 0; i < jArray.length(); i++){
                jokeContent.add(jArray.get(i).toString());
            }

        } catch (JSONException e){
            Log.d(LOG_TAG, e.getMessage());
            jokeContent = null;
        }

        // send the data back to the caller context
        if (delegate != null)
            delegate.processFinish(jokeContent);
    }


}