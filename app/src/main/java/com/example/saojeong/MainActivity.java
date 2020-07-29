package com.example.saojeong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import com.example.saojeong.fragment.PriceFragment;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.saojeong.fragment.HomeFragment;
import com.example.saojeong.fragment.MyPageFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private HomeFragment homeFragment;
    private MyPageFragment myPageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        homeFragment = new HomeFragment(); // 홈 Fragment 선언
        myPageFragment = new MyPageFragment(); // MyPage Fragment 선언
        transaction.replace(R.id.frameLayout_main, homeFragment)
                .commitAllowingStateLoss(); //시작화면에 Home 띄우기
    }
    public void clickHandler(View view) {
        transaction = fragmentManager.beginTransaction();
        switch (view.getId())
        {
            case R.id.ll_home:
                    transaction.replace(R.id.frameLayout_main, homeFragment) // frameLayout에 홈 Fragment 호출
                            .commitAllowingStateLoss();
                break;

            case R.id.ll_price:
                break;

            case R.id.ll_community:
                break;

            case R.id.ll_chatbot:
                break;

            case R.id.ll_myPage:
                transaction.replace(R.id.frameLayout_main, myPageFragment) // frameLayout에 MyPage Fragment 호출
                        .commitAllowingStateLoss();
                break;
        }
    }
}
