package com.example.saojeong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.saojeong.fragment.CommunityFragment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.saojeong.fragment.PriceFragment;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.saojeong.fragment.CommunityFragment;
import com.example.saojeong.fragment.HomeFragment;
import com.example.saojeong.fragment.PriceFragment;
import com.example.saojeong.fragment.MyPageFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private HomeFragment homeFragment;
    private MyPageFragment myPageFragment;
    private PriceFragment priceFragment;
    private CommunityFragment communityFragment;
    private ImageView mhome;
    private ImageView mprice;
    private ImageView mcommunity;
    private ImageView mchatbot;
    private ImageView mmypage;

    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        mhome = findViewById(R.id.miv_home);
        mprice = findViewById(R.id.miv_price);
        mcommunity = findViewById(R.id.miv_community);
        mchatbot = findViewById(R.id.miv_chatbot);
        mmypage = findViewById(R.id.miv_mypage);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        homeFragment = new HomeFragment(); // 홈 Fragment 선언
        myPageFragment = new MyPageFragment(); // MyPage Fragment 선언
        priceFragment = new PriceFragment(); // 시세 Fragment 선언
        communityFragment = new CommunityFragment(); // 커뮤니티 Fragment 선언
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        transaction.replace(R.id.frameLayout_main, homeFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss(); //시작화면에 Home 띄우기
        mhome.setImageResource(R.drawable.home_orange); //시작과 동시에 홈 오렌지색으로 변경
    }
    public void clickHandler(View view) {
        transaction = fragmentManager.beginTransaction();
        switch (view.getId())
        {
            case R.id.ll_home:
                    transaction.replace(R.id.frameLayout_main, homeFragment) // frameLayout에 홈 Fragment 호출
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commitAllowingStateLoss();
                    mhome.setImageResource(R.drawable.home_orange);
                    mprice.setImageResource(R.drawable.price);
                    mcommunity.setImageResource(R.drawable.community);
                    mchatbot.setImageResource(R.drawable.chatbot);
                    mmypage.setImageResource(R.drawable.mypage);
                break;

            case R.id.ll_price:
                    transaction.replace(R.id.frameLayout_main, priceFragment) // frameLayout에 시세 Fragment 호출
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commitAllowingStateLoss();
                    mhome.setImageResource(R.drawable.home);
                    mprice.setImageResource(R.drawable.price_orange);
                    mcommunity.setImageResource(R.drawable.community);
                    mchatbot.setImageResource(R.drawable.chatbot);
                    mmypage.setImageResource(R.drawable.mypage);
                break;

            case R.id.ll_community:
                    transaction.replace(R.id.frameLayout_main, communityFragment) // frameLayout에 커뮤니티 Fragment 호출
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commitAllowingStateLoss();
                    mhome.setImageResource(R.drawable.home);
                    mprice.setImageResource(R.drawable.price);
                    mcommunity.setImageResource(R.drawable.community_orange);
                    mchatbot.setImageResource(R.drawable.chatbot);
                    mmypage.setImageResource(R.drawable.mypage);
                break;

            case R.id.ll_chatbot:
                break;

            case R.id.ll_myPage:
                    transaction.replace(R.id.frameLayout_main, myPageFragment) // frameLayout에 MyPage Fragment 호출
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commitAllowingStateLoss();
                    mhome.setImageResource(R.drawable.home);
                    mprice.setImageResource(R.drawable.price);
                    mcommunity.setImageResource(R.drawable.community);
                    mchatbot.setImageResource(R.drawable.chatbot);
                    mmypage.setImageResource(R.drawable.mypage_orange);
                break;
        }
    }

    public void replaceFragment(Fragment newFragment) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout_main, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void signOut(View view) {
        Toast.makeText(view.getContext(),"test", Toast.LENGTH_LONG).show();
    }

    public void closeKeyBoard(View view) {
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
