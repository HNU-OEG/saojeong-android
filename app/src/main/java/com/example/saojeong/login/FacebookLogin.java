package com.example.saojeong.login;

import android.app.Activity;
import android.content.Intent;
import com.facebook.CallbackManager;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

public class FacebookLogin implements LoginControl  {

    private CallbackManager mCallbackManager;
    private LoginControl.LoginHandler mhandler;
    private FacebookLoginCallback mLoginCallback;
    private String[] mPermissions = {"public_profile", "email"};

    public FacebookLogin(LoginControl.LoginHandler loginHandler) {
        mhandler = loginHandler;
        init();
    }
    private void init() {
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
    }
    public void Logout() {
        LoginManager.getInstance().logOut();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

}