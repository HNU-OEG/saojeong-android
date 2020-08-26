package com.example.saojeong.login;

import android.app.Activity;
import android.content.Intent;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

public class FacebookLogin implements LoginControl  {
    // 로그인 성공 시 호출 됩니다. Access Token 발급 성공.
    private CallbackManager mCallbackManager;
    public LoginControl.LoginHandler mhandler;
    private FacebookLoginCallback mLoginCallback;
    private String[] mPermissions = {"public_profile", "email"};

    public FacebookLogin(Activity activity, LoginControl.LoginHandler loginHandler) {
        mhandler = loginHandler;
        init(activity);
    }

    private void init(Activity activity) {
        mCallbackManager = CallbackManager.Factory.create();
        mLoginCallback = new FacebookLoginCallback() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                FacebookLogin.this.mhandler.success();
            }

            @Override
            public void onCancel() {
                FacebookLogin.this.mhandler.cancel();
            }

            @Override
            public void onError(FacebookException error) {
                FacebookLogin.this.mhandler.error(error);
            }
        };

        LoginManager.getInstance().registerCallback(mCallbackManager,mLoginCallback);

    }

    public void Login(Activity activity) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList(this.mPermissions));
       //URL url = new URL("https://www.googleapis.com/tasks/v1/users/@me/lists?key=" + your_api_key);
       //URLConnection conn = (HttpURLConnection) url.openConnection();
       //conn.addRequestProperty("client_id", your client id);
       //conn.addRequestProperty("client_secret", your client secret);
       //conn.setRequestProperty("Authorization", "OAuth " + token);
    }

    public void Logout() {
        LoginManager.getInstance().logOut();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

}