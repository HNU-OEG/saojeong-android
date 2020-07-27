package com.example.saojeong;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class IntroPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Handler handler;
        handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(IntroPage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000); //3000은 3초를 의미함
    }
}