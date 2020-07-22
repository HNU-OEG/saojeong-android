package com.example.saojeong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickHandler(View view) {
        switch (view.getId())
        {
            case R.id.ll_home:
                break;
            case R.id.ll_price:
                break;
            case R.id.ll_community:
                break;
            case R.id.ll_chatbot:
                break;
            case R.id.ll_myPage:
                break;
        }
    }
}