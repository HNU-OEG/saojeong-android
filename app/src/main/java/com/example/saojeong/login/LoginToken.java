package com.example.saojeong.login;

import android.app.Activity;
import android.content.SharedPreferences;

import com.auth0.android.jwt.JWT;

import java.util.Date;

public class LoginToken {
    private static String AccessToken = null;
    private static String RefreshToken = null;


    public static void setToken(Activity activity){
        SharedPreferences pref = activity.getSharedPreferences("SHARE_PREF", activity.MODE_PRIVATE);
        AccessToken=pref.getString("AccessToken", "");
        AccessToken=pref.getString("RefreshToken", "");
    }
    public static void deleteToken(){
        AccessToken=null;
        RefreshToken=null;
    }
    public static String getToken() throws Exception {
        return null;
    }



}
