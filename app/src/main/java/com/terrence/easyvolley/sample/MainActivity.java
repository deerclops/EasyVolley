package com.terrence.easyvolley.sample;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.terrence.easyvolley.net.NetEngine;
import com.terrence.easyvolley.net.callback.AbstractSuccess;
import com.terrence.easyvolley.net.callback.IFailure;
import com.terrence.easyvolley.net.callback.IRequest;
import com.terrence.easyvolley.net.callback.IToastError;
import com.terrence.easyvolley.sample.entity.Agent;
import com.terrence.easyvolley.sample.entity.LoginParam;
import com.terrence.easyvolley.sample.entity.SessionRsp;
import com.wang.avi.AVLoadingIndicatorView;

import sample.easyvolley.terrence.com.sample.R;

/**
 * Created by DarkSouls on 2017/11/21.
 */

public class MainActivity extends Activity {

    private EditText etPhone;
    private EditText etCode;

    private TextView tvTerminal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPhone = findViewById(R.id.et_phone);
        etCode = findViewById(R.id.et_code);

        tvTerminal = findViewById(R.id.tv_terminal);

        findViewById(R.id.tv_request_net).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flowerTest();
            }
        });
    }

    public static class SmsReq {

        public String mobile;//接收验证码的帐号（手机号）
        public String smsNum;//验证码

        public SmsReq(String mobile, String smsNum) {
            this.mobile = mobile;
            this.smsNum = smsNum;
        }
    }

    private StringBuilder sb = new StringBuilder();

    private void flowerTest() {
        LoginParam loginParam = new LoginParam();
        loginParam.account = etPhone.getText().toString();
        loginParam.password = etCode.getText().toString();
        NetEngine.builder().url("http://120.31.131.193:8101/sjbAccount/router")
                .apiName("account.company.login").apiVersion("1.0")
                .addBodyParam(loginParam)
                .noSession()
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestBegin() {
                        showLoading();
                        refreshTerminal("req starts...", "\n");
                    }

                    @Override
                    public void onRequestEnd() {
                        getWindow().getDecorView().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dismissLoading();
                            }
                        }, 500);
                        refreshTerminal("req ends.", "\n");
                    }
                })
                .onSuccess(new AbstractSuccess<Agent>(Agent.class) {
                    @Override
                    public void success(Agent result) {
                        refreshTerminal(result.toString(), "\n");
                    }
                })
                .onToastError(new IToastError() {
                    @Override
                    public void onToastError(String errorMsg) {
                        refreshTerminal("error happens, ", errorMsg, "\n");
                    }
                })
                .onFailure(new IFailure() {
                    @Override
                    public void onFailure(String msg) {
                        refreshTerminal(msg, "\n");
                    }
                }).build().fetch();
    }

    private void refreshTerminal(String... msg) {
        for (String str : msg) {
            sb.append(str);
        }
        tvTerminal.setText(sb.toString());
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void test2() {
        SessionRsp result = JSON.parseObject("{\"code\":\"9\",\"message\":\"业务逻辑出错\",\"solution\":\"请了解服务调用的前置条件，检查是否满足业务逻辑\",\"subErrors\":[{\"code\":\"isv.sms-service-error:ERR_SMS_NOT_EXIST\",\"message\":\"验证码不存在\"}]}", SessionRsp.class);
        toast(result.toString());
    }

    private Dialog mDialog;

    private void showLoading() {
        if (mDialog == null) {
            mDialog = new Dialog(this, R.style.dialog);
            AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(this);
            avLoadingIndicatorView.setIndicator(AVLoadingIndicatorView.class.getPackage().getName() + ".indicators.BallSpinFadeLoaderIndicator");
            mDialog.setContentView(avLoadingIndicatorView);
        }
        mDialog.show();
    }

    private void dismissLoading() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
