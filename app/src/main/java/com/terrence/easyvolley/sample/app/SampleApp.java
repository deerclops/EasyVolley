package com.terrence.easyvolley.sample.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by DarkSouls on 2017/11/21.
 */

public class SampleApp extends Application {

    private RequestQueue queue;

    public RequestQueue getQueue(){
        return this.queue;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        queue = Volley.newRequestQueue(getApplicationContext());
        queue.start();
    }
}
