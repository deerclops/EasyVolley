package com.terrence.easyvolley.net.callback;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by DarkSouls on 2017/11/20.
 */

public class RequestCallbacks implements Response.ErrorListener {

    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IRequest REQUEST;

    public RequestCallbacks(ISuccess success, IFailure failure, IError error, IRequest request) {
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUEST = request;
    }

    public void onResponse(String response) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (ERROR != null)
            ERROR.onError();
    }
}
