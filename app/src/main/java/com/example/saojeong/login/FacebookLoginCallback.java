package com.example.saojeong.login;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

public class FacebookLoginCallback implements FacebookCallback<LoginResult> {

    @Override
    public void onSuccess(LoginResult loginResult) {
        requestMe(loginResult.getAccessToken());
    }
    @Override
    public void onCancel() {

    }
    @Override
    public void onError(FacebookException error) {
    }
    public void requestMe(AccessToken token) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(token,
                (object, response) -> Log.e("result",object.toString()));
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }
}