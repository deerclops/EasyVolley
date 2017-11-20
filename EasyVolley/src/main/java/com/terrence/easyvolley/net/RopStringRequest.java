package com.terrence.easyvolley.net;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.Response.ErrorListener;

import java.io.UnsupportedEncodingException;

/**
 * Created by DarkSouls on 2017/11/20.
 */

public class RopStringRequest extends Request<String> {

    public RopStringRequest(String url, ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {

    }


    private ErrorListener createErrorListener() {
        return new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
    }
}