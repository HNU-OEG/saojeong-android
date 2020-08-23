package com.example.saojeong.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.saojeong.MainActivity;
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
        int a=response.code();
        if(response.code()==200) {
            Login_Dto body = response.body();
            String str1 = body.accessToken;
            String str2 = body.refreshToken;
            SharedPreferences pref = mActivity.getSharedPreferences("SHARE_PREF", mActivity.MODE_PRIVATE);
            SharedPreferences.Editor editer = pref.edit();
            editer.putString("AccessToken", str1);
            editer.putString("RefreshToken", str2);
            editer.apply();
            editer.commit();
            Intent intent = new Intent(mActivity, MainActivity.class);
            mActivity.startActivity(intent);
            mActivity.finish();
        }
        else
        {
            Toast.makeText(mActivity,"접속에러", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<Login_Dto> call, Throwable t) {

    }
}
