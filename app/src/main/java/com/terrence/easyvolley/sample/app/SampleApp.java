package com.terrence.easyvolley.sample.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.terrence.easyvolley.app.Rop;

/**
 * Created by DarkSouls on 2017/11/21.
 */

public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Rop.init(this, "d5d907738f444eccb9e26c1e9f9bf295", "100094", new Rop.ISessionProvider() {
            @Override
            public String getSession() {
                return "";
            }
        });
    }
}
