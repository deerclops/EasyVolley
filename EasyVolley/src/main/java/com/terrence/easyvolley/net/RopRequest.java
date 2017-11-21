package com.terrence.easyvolley.net;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by DarkSouls on 2017/11/21.
 */
public class RopRequest<T> extends Request<T> {

    private Class<T> mClazz;
    private Response.Listener<T> mSuccessListener;

    public RopRequest(int method, String url, Class<T> clazz,
                      Response.Listener<T> successListener, Response.ErrorListener errorListener){
        super(method,url,errorListener);
        this.mSuccessListener = successListener;
        this.mClazz = clazz;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String jsonStr;
        try {
            jsonStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            jsonStr = new String(response.data);
        }
        T jsonObject = null;
        try {
            jsonObject = JsonParseHelper.parseJsonObject(jsonStr, mClazz);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(new VolleyError(e.getMessage()));
        }
        return Response.success(jsonObject,HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        if(mSuccessListener!=null){
            mSuccessListener.onResponse(response);
        }
    }
}
