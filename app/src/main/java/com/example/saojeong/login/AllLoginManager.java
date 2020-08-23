package com.example.saojeong.login;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.service.Login_Guest;
import com.facebook.AccessToken;
import com.kakao.auth.Session;

import java.util.HashMap;

public class AllLoginManager {
    final int GOOGLE_REQUESTCODE=9003;
    final int FACEBOOK_REQUESTCODE=64206;
    public static AllLoginManager inst;
    HashMap<String, LoginControl> map;
    private Login_Guest Login_GuestService;
    Activity mActivity;
    Context mContext;
    public AllLoginManager(Activity activity, Context context) {
        mActivity=activity;
        mContext=context;
        if(inst!=null)
            return;
        map=new HashMap<>();
        LoginControl facelogin=new FacebookLogin(mActivity, new LoginControl.LoginHandler() {
            @Override
            public void cancel() {
            }
            @Override
            public void success() {
                Login_GuestService = ServiceGenerator.createService(Login_Guest.class, AccessToken.getCurrentAccessToken().getToken());
                loadlogin(mActivity, "facebook");
            }

            @Override
            public void error(Throwable th) {
            }
        });
        LoginControl kakaologin=new kakaoControl(new LoginControl.LoginHandler() {
            @Override
            public void cancel() {
            }
            @Override
            public void success() {
                Login_GuestService = ServiceGenerator.createService(Login_Guest.class, Session.getCurrentSession().getRefreshToken());
                loadlogin(mActivity, "kakao");
            }
            @Override
            public void error(Throwable th) {
            }
        });
        LoginControl googlelogin=new GoogleLogin(mActivity, mContext, new LoginControl.LoginHandler() {
            @Override
            public void cancel() {
            }
            @Override
            public void success() {
                Login_GuestService = ServiceGenerator.createService(Login_Guest.class, GoogleLogin.account.getServerAuthCode());
                String str=GoogleLogin.account.getServerAuthCode();
                loadlogin(mActivity, "google");
            }
            @Override
            public void error(Throwable th) {
            }
        });
        //네이버미구현
        map.put("FACEBOOK", facelogin);
        map.put("KAKAO", kakaologin);
        map.put("GOOGLE", googlelogin);
        inst=this;
    }
    public void login(String type, Activity activity){
        LoginControl login=map.get(type);
        login.Login(activity);
    }

    public void logout(){
        for(int i=0; i<map.size(); ++i){
            map.get(i).Logout();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==GOOGLE_REQUESTCODE)
            map.get("GOOGLE").onActivityResult(requestCode,resultCode,data);
        if(requestCode==FACEBOOK_REQUESTCODE)
            map.get("FACEBOOK").onActivityResult(requestCode, resultCode, data);
    }

    private void loadlogin(Activity activity, String Type) {
        if(Type=="facebook")
        {
            Login_GuestService.FaceBookLogin().enqueue(new CallBackLogin(mActivity));
        }
        else if(Type=="kakao")
        {
            Login_GuestService.kakaoLogin(Session.getCurrentSession().getAccessToken()).enqueue(new CallBackLogin(mActivity));
        }
        else if(Type=="naver")
        {
            Login_GuestService.NaverLogin().enqueue(new CallBackLogin(mActivity));
        }
        else  if(Type=="google")
        {
            Login_GuestService.GoogleLogin().enqueue(new CallBackLogin(mActivity));
        }else  if(Type=="guest")
        {
            //Login_GuestService = ServiceGenerator.createService(Login_Guest.class, AccessToken.getCurrentAccessToken().getToken());
            //loadlogin(mActivity, "facebook");
        }
    }
}
