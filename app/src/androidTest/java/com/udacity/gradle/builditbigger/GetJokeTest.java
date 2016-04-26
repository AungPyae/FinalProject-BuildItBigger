package com.udacity.gradle.builditbigger;

import android.test.InstrumentationTestCase;
import android.text.TextUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by aung on 4/26/16.
 */
public class GetJokeTest extends InstrumentationTestCase {

    private String mJoke;

    public void testGetJoke() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                new GetJokeAsyncTask(new GetJokeAsyncTask.GetJokeTaskCallback() {
                    @Override
                    public void onRetrieveJoke(String joke) {
                        mJoke = joke;
                    }
                }){
                    @Override
                    protected void onPostExecute(String joke) {
                        super.onPostExecute(joke);
                        signal.countDown();
                    }
                }.execute();
            }
        });

        signal.await(10, TimeUnit.SECONDS);
        assertTrue(!TextUtils.isEmpty(mJoke));

    }
}
