package com.terrence.easyvolley.app;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by DarkSouls on 2017/12/22.
 */

public class Rop {

    public static RequestQueue sRequestQueue;
    public static String sAppSecret;
    public static String sAppCode;

    public static void init(Context context, String appSecret, String appCode) {
        sRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        sRequestQueue.start();

        sAppSecret = appSecret;
        sAppCode = appCode;
    }

    public static RequestQueue getRequestQueue() {
        return sRequestQueue;
    }

    public static String getAppSecret() {
        return sAppSecret;
    }

    public static String getAppCode(){
        return sAppCode;
    }
}
