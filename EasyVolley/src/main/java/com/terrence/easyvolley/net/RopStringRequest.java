package com.terrence.easyvolley.net;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.terrence.easyvolley.net.callback.RequestCallbacks;

import java.io.UnsupportedEncodingException;

/**
 * Created by DarkSouls on 2017/11/20.
 */

public class RopStringRequest extends Request<String> {

    private final RequestCallbacks CALLBACKS;

    public RopStringRequest(String url, RequestCallbacks callbacks) {
        super(Method.POST, url, callbacks);
        CALLBACKS = callbacks;
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
        if (CALLBACKS != null)
            CALLBACKS.onResponse(response);
    }
}
