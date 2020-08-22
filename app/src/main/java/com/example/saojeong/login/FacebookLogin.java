package com.example.saojeong.login;

import android.app.Activity;
import android.content.Intent;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

import java.util.Arrays;

public class FacebookLogin implements LoginControl  {
    // 로그인 성공 시 호출 됩니다. Access Token 발급 성공.
    private CallbackManager mCallbackManager;
    public LoginControl.LoginHandler mhandler;
    private FacebookLoginCallback mLoginCallback;
    private String[] mPermissions = {"public_profile", "email"};

    public FacebookLogin(LoginControl.LoginHandler loginHandler) {
        mhandler = loginHandler;
        init();
    }

    private void init() {
        mCallbackManager = CallbackManager.Factory.create();
        mLoginCallback = new FacebookLoginCallback();
        LoginManager.getInstance().registerCallback(mCallbackManager,mLoginCallback);
    }

    public void Login(Activity activity) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList(this.mPermissions));
    }

    public void Logout() {
        LoginManager.getInstance().logOut();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

}