package com.example.saojeong.login;

import android.app.Activity;
import android.content.SharedPreferences;


public class LoginToken {
    private static String AccessToken = null;
    private static String RefreshToken = null;

    public static void setToken(Activity activity){
        SharedPreferences pref = activity.getSharedPreferences("SHARE_PREF", activity.MODE_PRIVATE);
        AccessToken=pref.getString("AccessToken", "");
        RefreshToken=pref.getString("RefreshToken", "");
    }
    public static void deleteToken(Activity activity){
        SharedPreferences pref = activity.getSharedPreferences("SHARE_PREF", activity.MODE_PRIVATE);
        AccessToken=null;
        RefreshToken=null;
        pref.edit().clear();
        pref.edit().commit();
        pref=null;
    }
    public static String getToken() {

        return AccessToken;
    }



}
