package com.example.saojeong.login;

import android.app.Activity;
import android.content.Intent;

public interface LoginControl {

    interface LoginHandler {
        void cancel();

        void success();

        void error(Throwable th);
    }

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void Login(Activity activity);

    void Logout();
}