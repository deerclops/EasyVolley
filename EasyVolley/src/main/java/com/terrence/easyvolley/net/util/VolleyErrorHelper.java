package com.terrence.easyvolley.net.util;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import java.util.Map;

public class VolleyErrorHelper {
    /**
     * Returns appropriate message which is to be displayed to the user against
     * the specified error object.
     */
    public static String getMessage(VolleyError error) {
        if (error instanceof TimeoutError) {
            return "请求网络超时，请稍后重试";
        } else if (isServerProblem(error)) {
            return handleServerError(error);
        } else if (isNetworkProblem(error)) {
            return "当前网络不可用，请检查您的网络连接";
        }
        return error != null ? error.getMessage() : "连接服务器异常，请稍后重试";
    }

    /**
     * Determines whether the error is related to network
     *
     * @param error
     * @return
     */
    private static boolean isNetworkProblem(Object error) {
        return error instanceof NetworkError;
    }

    /**
     * Determines whether the error is related to server
     *
     * @param error
     * @return
     */
    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError);
    }

    /**
     * Handles the server error, tries to determine whether to show a stock
     * message or to show a message retrieved from the server.
     */
    private static String handleServerError(Object err) {
        VolleyError error = (VolleyError) err;
        NetworkResponse response = error.networkResponse;
        if (response != null) {
            switch (response.statusCode) {
                case 404:
                case 422:
                case 401:
                    try {
                        // server might return error like this { "error":
                        // "Some error occured" }
                        // Use "Gson" to parse the result
                        Map<String, String> result = JsonParseHelper.parseJsonMap(new String(response.data));
                        if (result != null && result.containsKey("error")) {
                            return result.get("error");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // invalid request
                    return error.getMessage();
                default:
                    return "连接服务器异常，请稍后重试";
            }
        }
        return "连接服务器异常，请稍后重试";
    }
}