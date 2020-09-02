package com.example.saojeong.login;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.session.MediaSession;
import android.os.Handler;
import android.os.Looper;
import android.util.AndroidException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.saojeong.IntroPage;
import com.example.saojeong.MainActivity;
import com.example.saojeong.TutorialActivity;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.Login_Dto;
import com.example.saojeong.rest.service.Login_Guest;
import com.example.saojeong.rest.service_login;
import com.facebook.AccessToken;
import com.facebook.login.Login;
import com.kakao.auth.Session;
import com.nhn.android.naverlogin.OAuthLogin;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllLoginManager {

    private final String TAG = "AllLoginManager Error";
    private final int GOOGLE_REQUESTCODE = 9003;
    private final int FACEBOOK_REQUESTCODE = 64206;
    public static AllLoginManager inst;
    HashMap<String, LoginControl> map;
    private Login_Guest Login_GuestService;
    private Activity mActivity;
    private Context mContext;
    public Boolean NetworkCheck;
    public Boolean oneUpdate;

    public AllLoginManager(Activity activity, Context context) {
        if (inst != null) {
            inst.mActivity = activity;
            inst.mContext = context;
            return;
        }
        oneUpdate = false;
        NetworkCheck = false;
        mActivity = activity;
        mContext = context;
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
                String str = GoogleLogin.account.getServerAuthCode();
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
        ///
        if (!LoginToken.getToken().equals(""))
            loadlogin("oneUpdate");

    }

    public void login(String type, Activity activity) {
        if (!inst.NetworkCheck) { //만약 네트워크 사용안했을때(페이스북누르고 바로 게스트누르는작업 막기)
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

    //전체로그아웃할때 호출하는 함수입니다.
    public void logout(Activity activity) {
        mActivity = activity;
        LoginToken.deleteToken(activity);
        map.get("FACEBOOK").Logout();
        map.get("KAKAO").Logout();
        map.get("GOOGLE").Logout();
        map.get("NAVER").Logout();

        inst.ThreadNetworkSleepSync();
    }

    //로그인 실패했을때 호출하는 함수입니다.
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

    //종료할때 불러오는 함수입니다.
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
        if (Type.equals("facebook")) {
            Login_GuestService.FaceBookLogin().subscribeOn(Schedulers.io()) // the observable is emitted on io thread
                    .subscribe(new ObserveLogin(mActivity, true, "FACEBOOK"));
        } else if (Type.equals("kakao")) {
            Login_GuestService.kakaoLogin(Session.getCurrentSession().getAccessToken()).subscribeOn(Schedulers.io()) // the observable is emitted on io thread
                    .subscribe(new ObserveLogin(mActivity, true, "KAKAO"));
        } else if (Type.equals("naver")) {
            Login_GuestService.NaverLogin().subscribeOn(Schedulers.io()) // the observable is emitted on io thread
                    .subscribe(new ObserveLogin(mActivity, true, "NAVER"));
        } else if (Type.equals("google")) {
            Login_GuestService.GoogleLogin().subscribeOn(Schedulers.io()) // the observable is emitted on io thread
                    .subscribe(new ObserveLogin(mActivity, true, "GOOGLE"));
        } else if (Type.equals("guest")) {
            Login_GuestService.CreateLogin().subscribeOn(Schedulers.io()) // the observable is emitted on io thread
                    .subscribe(new ObserveLogin(mActivity, true, "GUEST"));
        } else if (Type.equals("Update")) {
            Login_GuestService = service_login.createService(Login_Guest.class, TokenCase.getToken());
            HashMap hash = new HashMap<>();
            hash.put("RefreshToken", LoginToken.getRefreshToken());
            String str = LoginToken.getToken();
            if (LoginToken.AccessTokenTimer() == 1 || LoginToken.AccessTokenTimer() == 2) {
                Login_GuestService.UpdateToken(hash).subscribeOn(Schedulers.io()) // the observable is emitted on io thread
                        .subscribe(new ObserveLogin(mActivity, false, "UPDATE"));
            }
        }
        //oneUpdate는 첫실행시 호출되는 함수입니다.
        else if (Type.equals("oneUpdate")) {
            Login_GuestService = service_login.createService(Login_Guest.class, TokenCase.getToken());
            HashMap hash = new HashMap<>();
            hash.put("RefreshToken", LoginToken.getRefreshToken());
            Login_GuestService.UpdateToken(hash).subscribeOn(Schedulers.io()) // the observable is emitted on io thread
                    .subscribe(new ObserveLogin(mActivity, false, "oneUpdate"));
        }
        inst.ThreadNetworkSleepSync();
    }

    public void userDelete(Activity activity) {
        mActivity = activity;
        Login_GuestService = service_login.createService(Login_Guest.class, TokenCase.getToken());
        String tok = LoginToken.getToken();
        Login_GuestService.DeleteUser(TokenCase.getUserResource("member_id")).subscribeOn(Schedulers.io()) // the observable is emitted on io thread
                .subscribe(new Observer<Login_Dto>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Login_Dto login_dto) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //유저가 삭제되면 에러메세지호출
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
        String str = LoginToken.getToken();
        String ssssss = TokenCase.getUserResource("member_id");
        Login_GuestService = service_login.createService(Login_Guest.class, TokenCase.getToken());

        Login_GuestService.editUserNickname(hash, TokenCase.getUserResource("member_id")).subscribeOn(Schedulers.io()) // the observable is emitted on io thread
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
