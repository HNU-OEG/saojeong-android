package com.example.saojeong.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

public class NaverLogin implements LoginControl {

    private static OAuthLogin mOAuthLoginInstance;
    private static Context mContext;
    private String mID;
    private String mSecret;
    private String mName;
    private LoginHandler handler;
    public NaverLogin(Context Context, String ID, String Secret, String Name,Activity activity ,LoginHandler loginHandler) {
        mContext=Context;
        mID=ID;
        mSecret=Secret;
        mName=Name;
        handler=loginHandler;
        init();
        String str=OAuthLogin.getInstance().getAccessToken(mContext);
        if(str!=null)
        {
            OAuthLogin.getInstance().startOauthLoginActivity(activity, mOAuthLoginHandler);
        }
    }


    public void init() {
        mOAuthLoginInstance= OAuthLogin.getInstance();
        mOAuthLoginInstance.showDevelopersLog(true);
        mOAuthLoginInstance.init(mContext, mID, mSecret, mName);
    }

    static private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
            } else {
                String errorCode = mOAuthLoginInstance.getLastErrorCode(mContext).getCode();
                String errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext);
                Toast.makeText(mContext, "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }

    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
    @Override
    public void Login(Activity activity) {
        OAuthLogin.getInstance().startOauthLoginActivity(activity, mOAuthLoginHandler);
    }
    @Override
    public void Logout() {
        OAuthLogin.getInstance().logout(mContext);
    }
}

