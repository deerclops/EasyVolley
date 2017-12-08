package com.terrence.easyvolley.sample.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.terrence.easyvolley.net.entity.RopResult;

/**
 * 经纪人对象
 * Created by licai on 2017/11/28.
 */

public class Agent extends RopResult implements Parcelable {

    public String agentname;//	经纪人名称	String	无	false
    public String agentmobile;//	经纪人电话	String	无	false
    public String agentheadurl;//	经纪人头像	String	无	false
    public String agentsession;//	登录返回session	String	无	false
    public String agentqrcode;//	经纪人二维码
    public String idcode;//	身份证号
    public String agentcode;//	身份证号
    public boolean isSelector;//是否选中
    public boolean isprivate;//是否自有

    public Agent() {
    }

    protected Agent(Parcel in) {
        agentname = in.readString();
        agentmobile = in.readString();
        agentheadurl = in.readString();
        agentsession = in.readString();
        agentqrcode = in.readString();
        idcode = in.readString();
        agentcode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(agentname);
        dest.writeString(agentmobile);
        dest.writeString(agentheadurl);
        dest.writeString(agentsession);
        dest.writeString(agentqrcode);
        dest.writeString(idcode);
        dest.writeString(agentcode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Agent> CREATOR = new Creator<Agent>() {
        @Override
        public Agent createFromParcel(Parcel in) {
            return new Agent(in);
        }

        @Override
        public Agent[] newArray(int size) {
            return new Agent[size];
        }
    };
}
