package com.example.saojeong.login;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.session.MediaSession;

import androidx.annotation.Nullable;

import com.example.saojeong.MainActivity;
import com.example.saojeong.TutorialActivity;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.Login_Dto;
import com.example.saojeong.rest.service.Login_Guest;
import com.facebook.AccessToken;
import com.facebook.login.Login;
import com.kakao.auth.Session;
import com.nhn.android.naverlogin.OAuthLogin;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllLoginManager {
    private final int GOOGLE_REQUESTCODE=9003;
    private final int FACEBOOK_REQUESTCODE=64206;
    public static AllLoginManager inst;
    HashMap<String, LoginControl> map;
    private Login_Guest Login_GuestService;
    private Activity mActivity;
    private Context mContext;
    public AllLoginManager(Activity activity, Context context) {
        if(inst!=null) {

            inst.mActivity=activity;
            inst.mContext=context;
            return;
        }
        mActivity=activity;
        mContext=context;
        map=new HashMap<>();
        LoginControl facelogin=new FacebookLogin(new LoginControl.LoginHandler() {
            @Override
            public void cancel() {
            }
            @Override
            public void success() {
                Login_GuestService = ServiceGenerator.createService(Login_Guest.class, AccessToken.getCurrentAccessToken().getToken());
                loadlogin("facebook");
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
                loadlogin("kakao");
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
                loadlogin("google");
            }
            @Override
            public void error(Throwable th) {
            }
        });
        String ID="";
        String Secret="";
        String Name="";
        LoginControl naverlogin=new NaverLogin(mContext,ID,Secret,Name ,activity,new LoginControl.LoginHandler() {
            @Override
            public void cancel() {
            }
            @Override
            public void success() {
                Login_GuestService = ServiceGenerator.createService(Login_Guest.class, OAuthLogin.getInstance().getAccessToken(mContext));
                loadlogin("naver");
            }
            @Override
            public void error(Throwable th) {
            }
        });
        //네이버미구현
        map.put("FACEBOOK", facelogin);
        map.put("KAKAO", kakaologin);
        map.put("GOOGLE", googlelogin);
        map.put("NAVER", naverlogin);

        inst=this;
        LoginToken.setToken(mActivity);
        ///


        if(LoginToken.getToken()!="") {

            loadlogin("Update");
            if(LoginToken.getToken()!="") { //업데이트후 토큰발급되었을때 홈화면으로
                Intent intent = new Intent(mActivity, MainActivity.class);
                mActivity.startActivity(intent);
                mActivity.finish();
            }
            //발급안되었으면 로그인실행
        }
    }

    public void login(String type, Activity activity){
        LoginControl login=map.get(type);
        mActivity=activity;
        if(login==null)
        {
            if(type=="UPDATE") {

                Login_GuestService = ServiceGenerator.createService(Login_Guest.class, TokenCase.getGuestToken());
                loadlogin("Update");
                return;
            }
            Login_GuestService = ServiceGenerator.createService(Login_Guest.class, TokenCase.getGuestToken());
            loadlogin("guest");
        }else {
            login.Login(mActivity);
        }
    }

    public void logout(Activity activity){
        mActivity=activity;
        map.get("FACEBOOK").Logout();
        map.get("KAKAO").Logout();
        map.get("GOOGLE").Logout();
        map.get("NAVER").Logout();
        LoginToken.deleteToken(activity);
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==GOOGLE_REQUESTCODE)
            map.get("GOOGLE").onActivityResult(requestCode,resultCode,data);
        if(requestCode==FACEBOOK_REQUESTCODE)
            map.get("FACEBOOK").onActivityResult(requestCode, resultCode, data);
    }

    private void loadlogin(String Type) {
        Login_GuestService = ServiceGenerator.createService(Login_Guest.class, TokenCase.getToken());
        if(Type=="facebook")
        {
            Login_GuestService.FaceBookLogin().enqueue(new CallBackLogin(mActivity, true));
        }
        else if(Type=="kakao")
        {
            Login_GuestService.kakaoLogin(Session.getCurrentSession().getAccessToken()).enqueue(new CallBackLogin(mActivity, true));
        }
        else if(Type=="naver")
        {
            Login_GuestService.NaverLogin().enqueue(new CallBackLogin(mActivity, true));
        }
        else  if(Type=="google")
        {
            Login_GuestService.GoogleLogin().enqueue(new CallBackLogin(mActivity, true));
        }else  if(Type=="guest")
        {
            Login_GuestService.CreateLogin().enqueue(new CallBackLogin(mActivity, true));
        }
        else  if(Type=="Update")
        {
           //HashMap hash=new HashMap<>();
           //hash.put("accesstoken", LoginToken.getToken());
           //String str=LoginToken.getToken();
           //    Login_GuestService.UpdateToken(hash).enqueue(new CallBackLogin(mActivity, false));
           //if(LoginToken.AccessTokenTimer()==1)
           //    Login_GuestService.UpdateToken(LoginToken.getToken(), LoginToken.getRefreshToken()).enqueue(new CallBackLogin(mActivity, false));

        }
    }
    public void userDelete(Activity activity) {
        mActivity=activity;
        Login_GuestService = ServiceGenerator.createService(Login_Guest.class, TokenCase.getToken());
        String tok=LoginToken.getToken();
        Login_GuestService.DeleteUser(TokenCase.getUserResource("member_id")).enqueue(new Callback<Login_Dto>() {
            @Override
            public void onResponse(Call<Login_Dto> call, Response<Login_Dto> response) {
                int a=response.code();
                logout(mActivity);
                Intent intent = new Intent(mActivity, TutorialActivity.class);
                mActivity.startActivity(intent);
                mActivity.finish();
            }

            @Override
            public void onFailure(Call<Login_Dto> call, Throwable t) {

            }
        });

    }

    public void editUsernickname(Activity activity, String nickname1) {
        mActivity=activity;
        HashMap hash=new HashMap<>();
        hash.put("nickname", nickname1);
        String str= LoginToken.getToken();
        Login_GuestService = ServiceGenerator.createService(Login_Guest.class, TokenCase.getToken());
        Login_GuestService.editUserNickname(hash).enqueue(new CallBackLogin(mActivity, false));
    }
}
