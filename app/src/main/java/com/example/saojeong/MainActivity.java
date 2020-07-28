package com.example.saojeong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import com.example.saojeong.fragment.PriceFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout_main, new PriceFragment())
                .commitAllowingStateLoss();
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