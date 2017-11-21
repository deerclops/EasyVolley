package com.terrence.easyvolley.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.terrence.easyvolley.net.RopRequest;
import com.terrence.easyvolley.sample.app.SampleApp;
import com.terrence.easyvolley.sample.entity.Result;

import sample.easyvolley.terrence.com.sample.R;

/**
 * Created by DarkSouls on 2017/11/21.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_request_net).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });
    }

    private void test(){
        RopRequest<Result> request = new RopRequest<>(Request.Method.GET,
                "http://gank.io/api/data/Android/10/1",
                Result.class, new Response.Listener<Result>() {
            @Override
            public void onResponse(Result response) {
                toast(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toast(error.getMessage());
            }
        });
        ((SampleApp)getApplicationContext()).getQueue().add(request);
    }

    private void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
