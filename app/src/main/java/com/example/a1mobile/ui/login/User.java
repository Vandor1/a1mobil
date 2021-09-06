package com.example.a1mobile.ui.login;


import org.json.JSONException;
import org.json.JSONObject;

public class User {
    String userid;

    public User() {
    }

    public User(JSONObject jo) throws JSONException {
        setUserid(jo.getString("userid"));
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}

