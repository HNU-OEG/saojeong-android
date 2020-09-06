package com.example.saojeong.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.Nullable;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.Login_Dto;
import com.example.saojeong.rest.service.Login_Guest;
import com.example.saojeong.rest.service_login;
import com.facebook.AccessToken;
import com.kakao.auth.Session;
import com.nhn.android.naverlogin.OAuthLogin;
import java.util.HashMap;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.SneakyThrows;

public class AllLoginManager {
    private final int GOOGLE_REQUESTCODE = 9003;
    private final int FACEBOOK_REQUESTCODE = 64206;
    private static AllLoginManager inst;
    HashMap<String, LoginControl> map;
    private Login_Guest Login_GuestService;
    private Activity mActivity;
    private Context mContext;
    public Boolean NetworkCheck;
    public Boolean oneUpdate;


    public static AllLoginManager getInstance(){
        if(inst == null){
            return null;
        }
        return inst;
    }
    public AllLoginManager(Activity activity, Context context) {

        mActivity = activity;
        mContext = context;
        oneUpdate = false;
        NetworkCheck = false;
        map = new HashMap<>();

        LoginControl facelogin = new FacebookLogin(new LoginControl.LoginHandler() {
            @Override
            public void cancel() {
                AllLoginManager.inst.NetworkCheck = false;
            }

            @Override
            public void success() {
                Login_GuestService = service_login.createService(Login_Guest.class, AccessToken.getCurrentAccessToken().getToken());
                loadlogin("facebook");
            }

            @Override
            public void error(Throwable th) {
                AllLoginManager.inst.NetworkCheck = false;
            }
        });

        LoginControl kakaologin = new kakaoControl(new LoginControl.LoginHandler() {
            @Override
            public void cancel() {
                AllLoginManager.inst.NetworkCheck = false;
            }

            @Override
            public void success() {
                Login_GuestService = service_login.createService(Login_Guest.class, Session.getCurrentSession().getRefreshToken());
                loadlogin("kakao");
            }

            @Override
            public void error(Throwable th) {
                AllLoginManager.inst.NetworkCheck = false;
            }
        });

        LoginControl googlelogin = new GoogleLogin(mActivity, mContext, new LoginControl.LoginHandler() {
            @Override
            public void cancel() {
                AllLoginManager.inst.NetworkCheck = false;
            }

            @Override
            public void success() {
                Login_GuestService = service_login.createService(Login_Guest.class, GoogleLogin.account.getServerAuthCode());
                loadlogin("google");
            }

            @Override
            public void error(Throwable th) {
                AllLoginManager.inst.NetworkCheck = false;
            }
        });

        String ID = "";
        String Secret = "";
        String Name = "";
        LoginControl naverlogin = new NaverLogin(mContext, ID, Secret, Name, activity, new LoginControl.LoginHandler() {
            @Override
            public void cancel() {
                AllLoginManager.inst.NetworkCheck = false;
            }

            @Override
            public void success() {
                Login_GuestService = service_login.createService(Login_Guest.class, OAuthLogin.getInstance().getAccessToken(mContext));
                loadlogin("naver");
            }

            @Override
            public void error(Throwable th) {
                AllLoginManager.inst.NetworkCheck = false;
            }
        });
        //네이버미구현
        map.put("FACEBOOK", facelogin);
        map.put("KAKAO", kakaologin);
        map.put("GOOGLE", googlelogin);
        map.put("NAVER", naverlogin);

        inst = this;
        LoginToken.setToken(mActivity);
    }

    public void login(String type, Activity activity) {
        if (!inst.NetworkCheck) {
            inst.NetworkCheck = true;
            LoginControl login = map.get(type);
            mActivity = activity;
            if (login == null) {
                if (type.equals("UPDATE")) {
                    Login_GuestService = ServiceGenerator.createService(Login_Guest.class, TokenCase.getToken());
                    loadlogin("Update");
                    return;
                }
                Login_GuestService = service_login.createService(Login_Guest.class, TokenCase.getGuestToken());
                loadlogin("guest");
            } else {
                login.Login(mActivity);
            }
        }
    }
    public void logout(Activity activity) {
        mActivity = activity;
        LoginToken.deleteToken(activity);
        map.get("FACEBOOK").Logout();
        map.get("KAKAO").Logout();
        map.get("GOOGLE").Logout();
        map.get("NAVER").Logout();
        inst.ThreadNetworkSleepSync();
    }

    public void logout(Activity activity, String type) {
        mActivity = activity;
        LoginToken.deleteToken(activity);
        if (type.equals("FACEBOOK"))
            map.get("FACEBOOK").Logout();
        if (type.equals("KAKAO"))
            map.get("KAKAO").Logout();
        if (type.equals("GOOGLE"))
            map.get("GOOGLE").Logout();
        if (type.equals("NAVER"))
            map.get("NAVER").Logout();
    }

    public void Destroy(Activity activity) {
        if (activity != null)
            mActivity = activity;
        map.clear();
        inst = null;
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == GOOGLE_REQUESTCODE)
            map.get("GOOGLE").onActivityResult(requestCode, resultCode, data);
        if (requestCode == FACEBOOK_REQUESTCODE)
            map.get("FACEBOOK").onActivityResult(requestCode, resultCode, data);
    }

    @SneakyThrows
    private void loadlogin(String Type) {
        AllLoginManager.getInstance().NetworkCheck = true;
        switch (Type) {
            case "facebook":
                Login_GuestService.FaceBookLogin().subscribeOn(Schedulers.io())
                        .subscribe(new ObserveLogin(mActivity, true, "FACEBOOK"));
                break;
            case "kakao":
                Login_GuestService.kakaoLogin(Session.getCurrentSession().getAccessToken()).subscribeOn(Schedulers.io())
                        .subscribe(new ObserveLogin(mActivity, true, "KAKAO"));
                break;
            case "naver":
                Login_GuestService.NaverLogin().subscribeOn(Schedulers.io())
                        .subscribe(new ObserveLogin(mActivity, true, "NAVER"));
                break;
            case "google":
                Login_GuestService.GoogleLogin().subscribeOn(Schedulers.io())
                        .subscribe(new ObserveLogin(mActivity, true, "GOOGLE"));
                break;
            case "guest":
                Login_GuestService.CreateLogin().subscribeOn(Schedulers.io())
                        .subscribe(new ObserveLogin(mActivity, true, "GUEST"));
                break;
            case "Update":
                if( TokenCase.getToken()=="") {
                    AllLoginManager.getInstance().NetworkCheck=false;
                    break;
                }
                Login_GuestService = service_login.createService(Login_Guest.class, TokenCase.getToken());
                HashMap hash = new HashMap<>();
                hash.put("RefreshToken", LoginToken.getRefreshToken());
                Login_GuestService.UpdateToken(hash).subscribeOn(Schedulers.io())
                        .subscribe(new ObserveLogin(mActivity, false, "UPDATE"));
                break;
        }

        inst.ThreadNetworkSleepSync();
    }

    public void userDelete(Activity activity) {
        mActivity = activity;
        Login_GuestService = service_login.createService(Login_Guest.class, TokenCase.getToken());

        Login_GuestService.DeleteUser(TokenCase.getUserResource("member_id")).subscribeOn(Schedulers.io())
                .subscribe(new Observer<Login_Dto>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Login_Dto login_dto) {
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        logout(mActivity);
                        Destroy(mActivity);
                        inst.NetworkCheck = false;
                    }
                    @Override
                    public void onComplete() {
                        inst.NetworkCheck = false;
                    }
                });
        inst.ThreadNetworkSleepSync();
    }

    public void editUsernickname(Activity activity, String nickname1) {
        inst.NetworkCheck = true;
        mActivity = activity;
        HashMap hash = new HashMap<>();
        hash.put("nickname", nickname1);
        Login_GuestService = service_login.createService(Login_Guest.class, TokenCase.getToken());
        Login_GuestService.editUserNickname(hash, TokenCase.getUserResource("member_id")).subscribeOn(Schedulers.io())
                .subscribe(new ObserveLogin(mActivity, false, "type"));
        inst.ThreadNetworkSleepSync();
    }

    public void ThreadNetworkSleepSync() {
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (inst.NetworkCheck)
            ThreadNetworkSleepSync();
    }
}
