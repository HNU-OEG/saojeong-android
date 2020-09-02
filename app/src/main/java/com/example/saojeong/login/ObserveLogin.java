package com.example.saojeong.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.saojeong.MainActivity;
import com.example.saojeong.rest.dto.Login_Dto;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class ObserveLogin implements Observer<Login_Dto> {

    Activity mActivity;
    boolean login;
    String type;

    public ObserveLogin(Activity activity, boolean login, String type) {
        mActivity = activity;
        this.login = login;
        this.type = type;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull Login_Dto login_dto) {
        Login_Dto body = login_dto;
        Log.d("LOGIN DTO", body.toString());
        SharedPreferences pref = mActivity.getSharedPreferences("SHARE_PREF", mActivity.MODE_PRIVATE);
        SharedPreferences.Editor editer = pref.edit();
        if (body.AccessToken != null) {
            String str1 = body.AccessToken;
            editer.putString("AccessToken", str1);
        }
        if (body.refreshToken != null) {
            String str2 = body.refreshToken;
            editer.putString("RefreshToken", str2);
            long now = System.currentTimeMillis();
            editer.putLong("LastLoginAt", now);
        }
        editer.apply();
        editer.commit();
        LoginToken.setToken(mActivity);
    }

    @Override
    public void onError(@NonNull Throwable e) {

        Toast.makeText(mActivity, "접속에러", Toast.LENGTH_SHORT).show();
        if (type.equals("oneUpdate"))
            AllLoginManager.inst.logout(mActivity);
        AllLoginManager.inst.logout(mActivity, type);
        AllLoginManager.inst.NetworkCheck = false;
    }

    @Override
    public void onComplete() {
        if (login) {
            Intent intent = new Intent(mActivity, MainActivity.class);
            mActivity.startActivity(intent);
            mActivity.finish();
        }
        if (type.equals("oneUpdate"))
            AllLoginManager.inst.oneUpdate = true;
        AllLoginManager.inst.NetworkCheck = false;

    }
}
