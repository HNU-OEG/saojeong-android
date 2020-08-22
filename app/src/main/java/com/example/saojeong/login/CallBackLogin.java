package com.example.saojeong.login;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.saojeong.model.LoginData;
import com.example.saojeong.rest.dto.Login_Dto;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallBackLogin implements Callback<Login_Dto> {

    Activity mActivity;

    public CallBackLogin(Activity activity)
    {
        mActivity=activity;
    }

    @Override
    public void onResponse(Call<Login_Dto> call, Response<Login_Dto> response) {
        LoginData dto = new LoginData(Objects.requireNonNull(response.body()));
//
        Login_Dto body = response.body();
        String str = body.accessToken;
        SharedPreferences pref = mActivity.getSharedPreferences("SHARE_PREF", mActivity.MODE_PRIVATE);
        SharedPreferences.Editor editer = pref.edit();
        editer.putString("AccessToken", str);
        editer.apply();
    }

    @Override
    public void onFailure(Call<Login_Dto> call, Throwable t) {

    }
}
