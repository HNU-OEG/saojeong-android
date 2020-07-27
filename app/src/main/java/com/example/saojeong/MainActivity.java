package com.example.saojeong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.saojeong.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private HomeFragment homeFragment;

    int layoutstate = R.id.ll_home;// 같은 버튼 두번누르면 앱이 종료되는 버그가 있어 예외처리 함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        homeFragment = new HomeFragment(); // 홈 Fragment 선언
        transaction.replace(R.id.frameLayout_main, homeFragment)
                .commitAllowingStateLoss(); //시작화면에 Home 띄우기
    }
    public void clickHandler(View view) {
        switch (view.getId())
        {
            case R.id.ll_home:
                if(layoutstate != view.getId()) { // 같은 버튼 두번누르면 앱이 종료되는 버그가 있어 예외처리 함
                    transaction.replace(R.id.frameLayout_main, homeFragment) // frameLayout에 홈 Fragment 호출
                            .commitAllowingStateLoss();
                    layoutstate = view.getId();
                }
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