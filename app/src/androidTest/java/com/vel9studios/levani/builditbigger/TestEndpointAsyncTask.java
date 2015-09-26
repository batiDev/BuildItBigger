package com.vel9studios.levani.builditbigger;

import android.test.InstrumentationTestCase;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestEndpointAsyncTask extends InstrumentationTestCase {

    private String LOG_TAG = TestEndpointAsyncTask.class.getSimpleName();
    private String source;
    private String KEY_SOURCE = "source";

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // Async Task testing code from https://gist.github.com/he9lin/2195897
    public final void testSuccessfulFetch() throws Throwable {
        // create  a signal to let us know when our task is done.
        final CountDownLatch signal = new CountDownLatch(1);

        // Execute the async task on the UI thread
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                new EndpointsAsyncTask() {

                    @Override
                    protected void onPostExecute(String response) {
                        super.onPostExecute(response);

                        try{
                            JSONObject jokeObj = new JSONObject(response);
                            source = jokeObj.getString(KEY_SOURCE);
                        } catch (JSONException e) {
                            Log.d(LOG_TAG, e.getMessage());
                            source = null;
                        }

                        signal.countDown();

                    }
                }.execute();
            }
        });

	    /* The testing thread will wait here until the UI thread releases it
	     * above with the countDown() or 30 seconds passes and it times out.
	     */
        signal.await(10, TimeUnit.SECONDS);
        assertTrue(source != null && source.length() > 0);
    }
}