package com.terrence.easyvolley.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.terrence.easyvolley.net.entity.RopResult;
import com.terrence.easyvolley.net.util.JsonParseHelper;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by DarkSouls on 2017/11/21.
 */
public class RopRequest extends Request<RopResult> {

    private Class mClazz;
    private Response.Listener<RopResult> mSuccessListener;

    private Map<String, String> mReqParamsMap;
    private Map<String, String> mHeadParamsMap;
//    private Object mParamObj;//请求包含在一个对象内

    public <T extends RopResult> RopRequest(int method, String url, Class<T> clazz,
                      Response.Listener<RopResult> successListener,
                      Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mSuccessListener = successListener;
        this.mClazz = clazz;
    }

    public void setReqParamsMap(Map<String, String> map) {
        this.mReqParamsMap = map;
    }

    public void setHeadParamsMap(Map<String, String> map) {
        this.mHeadParamsMap = map;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeadParamsMap;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (getMethod() == Method.POST) {
            return mReqParamsMap;
        } else {
            return super.getParams();
        }
    }

    @Override
    protected Response<RopResult> parseNetworkResponse(NetworkResponse response) {
        String jsonStr;
        try {
            jsonStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            jsonStr = new String(response.data);
        }
        RopResult jsonObject = null;
        try {
            jsonObject = (RopResult) JsonParseHelper.parseJsonObject(jsonStr, mClazz);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(new VolleyError(e.getMessage()));
        }
        return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(RopResult response) {
        if (mSuccessListener != null) {
            mSuccessListener.onResponse(response);
        }
    }
}
