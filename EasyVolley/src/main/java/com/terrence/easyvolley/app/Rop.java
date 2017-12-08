package com.terrence.easyvolley.app;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.terrence.easyvolley.net.callback.ISessionExpired;

/**
 * Created by DarkSouls on 2017/12/22.
 */

public final class Rop {

    public static RequestQueue sRequestQueue;
    public static String sAppSecret;
    public static String sAppCode;
    public static ISessionProvider sSessionProvider;

    public static void init(Context context, String appSecret, String appCode, ISessionProvider provider) {
        sRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        sRequestQueue.start();

        sAppSecret = appSecret;
        sAppCode = appCode;

        sSessionProvider = provider;
    }

    public static RequestQueue getRequestQueue() {
        return sRequestQueue;
    }

    public static String getAppSecret() {
        return sAppSecret;
    }

    public static String getAppCode() {
        return sAppCode;
    }

    public static String getSession() {
        if (sSessionProvider != null) {
            return sSessionProvider.getSession();
        } else {
            return "";
        }
    }

    public interface ISessionProvider {
        String getSession();
    }
}
