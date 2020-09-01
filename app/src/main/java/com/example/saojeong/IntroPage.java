package com.example.saojeong;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.login.AllLoginManager;
import com.example.saojeong.login.kakaoControl;
import com.example.saojeong.util.ForecdTerminationService;

public class IntroPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        kakaoControl.init(getApplication());
        AllLoginManager allLoginManager = new AllLoginManager(this, this);

        SharedPreferences shared = getApplicationContext().getSharedPreferences("SHARE_PREF", Context.MODE_PRIVATE);
        String accessToken = shared.getString("AccessToken", "");

        Intent intent;
        if (accessToken.equals("")) {
            intent = new Intent(IntroPage.this, TutorialActivity.class);
        } else {
            intent = new Intent(IntroPage.this, MainActivity.class);
        }

        startActivity(intent);
        finish();

    }
}