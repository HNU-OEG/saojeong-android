package com.example.saojeong;

import android.content.Intent;
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

        Handler handler;
        handler = new Handler(Looper.getMainLooper());

        kakaoControl.init(getApplication());
        AllLoginManager LoginManager=null;
        if(AllLoginManager.inst==null) {
            LoginManager = new AllLoginManager(this, this);
            AllLoginManager.inst=LoginManager;
        }

        startService(new Intent(this, ForecdTerminationService.class));
        handler.postDelayed(new Runnable() {
            public void run() {
                if(AllLoginManager.inst.oneUpdate) {
                    Intent intent = new Intent(IntroPage.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(IntroPage.this, TutorialActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000); //3000은 3초를 의미함
    }
}