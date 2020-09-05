package com.example.saojeong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.saojeong.fragment.CommunityFragment;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.saojeong.fragment.CommunityTabFragment;
import com.example.saojeong.fragment.FAQFragment;
import com.example.saojeong.fragment.MyQnAFragment;
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
import com.example.saojeong.fragment.QnAFragment;
import com.example.saojeong.login.AllLoginManager;
import com.example.saojeong.util.AlertBuilder;

import java.io.InterruptedIOException;
import java.util.Timer;
import java.util.TimerTask;

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
    private ImageView mmypage;

    private InputMethodManager imm;
    private Activity activity;

    private TimerTask mTask;
    private Timer mTimer;

    private String activity_tag;
    long pressedTime;

    int hiddenlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 하단바 클릭시 색상 변경
        mhome = findViewById(R.id.miv_home);
        mprice = findViewById(R.id.miv_price);
        mcommunity = findViewById(R.id.miv_community);
        mmypage = findViewById(R.id.miv_mypage);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        homeFragment = new HomeFragment(); // 홈 Fragment 선언
        myPageFragment = new MyPageFragment(); // MyPage Fragment 선언
        priceFragment = new PriceFragment(); // 시세 Fragment 선언
        communityFragment = new CommunityFragment(); // 커뮤니티 Fragment 선언
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        transaction.replace(R.id.frameLayout_main, homeFragment).addToBackStack(null)
                .commitAllowingStateLoss();
        ; //시작화면에 Home 띄우기
        mhome.setImageResource(R.drawable.home_orange); //시작과 동시에 홈 오렌지색으로 변경
        activity_tag = "homeFragment";
        activity = this;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                AllLoginManager.inst.login("UPDATE", activity);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 60 * 1000 * 5);
        pressedTime = 0;
    }

    public void clickHandler(View view) {
        transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.ll_home:
                if (!activity_tag.equals("homeFragment")) {
                    fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // 백스택 모두 지우기
                    transaction.replace(R.id.frameLayout_main, homeFragment) // frameLayout에 홈 Fragment 호출
                            .addToBackStack(null)
                            .commitAllowingStateLoss();
                    activity_tag = "homeFragment";

                    mhome.setImageResource(R.drawable.home_orange);
                    mprice.setImageResource(R.drawable.price);
                    mcommunity.setImageResource(R.drawable.community);
                    mmypage.setImageResource(R.drawable.mypage);

                }
                break;
            case R.id.ll_price:
                if (!activity_tag.equals("priceFragment")) {
                    fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // 백스택 모두 지우기
                    transaction.replace(R.id.frameLayout_main, priceFragment) // frameLayout에 시세 Fragment 호출
                            .addToBackStack(null)
                            .commitAllowingStateLoss();
                    activity_tag = "priceFragment";

                    mhome.setImageResource(R.drawable.home);
                    mprice.setImageResource(R.drawable.price_orange);
                    mcommunity.setImageResource(R.drawable.community);
                    mmypage.setImageResource(R.drawable.mypage);

                    AlertDialog.Builder pricebuilder = new AlertDialog.Builder(this);
                    pricebuilder.setTitle("시세정보").setMessage("데이터 수집중입니다.");
                    pricebuilder.setPositiveButton("홈으로 돌아가기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            onBackPressed();
                        }
                    });
//                    pricebuilder.setNegativeButton("", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    });
//                    pricebuilder.setNeutralButton("", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    });
                    AlertDialog priceDialog = pricebuilder.create();
                    priceDialog.show();
//                    AlertBuilder.createDialog(this, "사오정 정보", "데이터 수집중입니다.")
//                            .show();
//                    onBackPressed();
                }
                break;
            case R.id.ll_community:
                if (!activity_tag.equals("communityFragment")) {
                    fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // 백스택 모두 지우기
                    transaction.replace(R.id.frameLayout_main, communityFragment) // frameLayout에 커뮤니티 Fragment 호출
                            .addToBackStack(null)
                            .commitAllowingStateLoss();
                    activity_tag = "communityFragment";

                    mhome.setImageResource(R.drawable.home);
                    mprice.setImageResource(R.drawable.price);
                    mcommunity.setImageResource(R.drawable.community_orange);
                    mmypage.setImageResource(R.drawable.mypage);

                }
                break;

            case R.id.ll_myPage:
                if (!activity_tag.equals("myPageFragment")) {
                    fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // 백스택 모두 지우기
                    transaction.replace(R.id.frameLayout_main, myPageFragment) // frameLayout에 MyPage Fragment 호출
                            .addToBackStack(null)
                            .commitAllowingStateLoss();
                    activity_tag = "myPageFragment";
                    mhome.setImageResource(R.drawable.home);
                    mprice.setImageResource(R.drawable.price);
                    mcommunity.setImageResource(R.drawable.community);
                    mmypage.setImageResource(R.drawable.mypage_orange);

                    break;
                }
        }
    }

    @Override
    public void onBackPressed() {
        transaction = fragmentManager.beginTransaction();
        int backstackcount = fragmentManager.getBackStackEntryCount();
//
        //만약 더이상의 백스택이 없다면 홈으로 돌아가기
        if (activity_tag == "homeFragment" && backstackcount == 1) { //만약 더이상의 백스택이 없다면 홈으로 돌아가기
            if (pressedTime == 0) {
                Toast.makeText(MainActivity.this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
                pressedTime = System.currentTimeMillis();
                return;
            } else {
                long second = System.currentTimeMillis();
                if (second - pressedTime > 2000) {
                    Toast.makeText(MainActivity.this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
                    pressedTime = System.currentTimeMillis();
                } else {
                    super.onBackPressed();
                    finish();
                }
            }
        } else {
            super.onBackPressed();
            backstackcount = fragmentManager.getBackStackEntryCount();
            if (backstackcount == 0) {
                activity_tag = "homeFragment";
                transaction.replace(R.id.frameLayout_main, homeFragment)
                        .addToBackStack(null)// frameLayout에 홈 Fragment 호출
                        .commitAllowingStateLoss();

                mhome.setImageResource(R.drawable.home_orange); //시작과 동시에 홈 오렌지색으로 변경
                mprice.setImageResource(R.drawable.price);
                mcommunity.setImageResource(R.drawable.community);
                mmypage.setImageResource(R.drawable.mypage);
            }
        }
    }

    public void replaceFragment(Fragment newFragment) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout_main, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void replaceHomeFragment(Fragment homeFragment) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout_home, homeFragment);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();

    }

    public void closeKeyBoard(View view) {
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void onClickQnA(View view) {

        QnAFragment qnAFragment = new QnAFragment();
        MyQnAFragment myQnAFragment = new MyQnAFragment();
        FAQFragment faQFragment = new FAQFragment();
        transaction = fragmentManager.beginTransaction();

        switch (view.getId()) {

            case R.id.tv_QnA:
                transaction.replace(R.id.frameLayout_main, qnAFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.tv_FAQ:
                transaction.replace(R.id.frameLayout_main, faQFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.tv_myQnA:
                transaction.replace(R.id.frameLayout_main, myQnAFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
    }

    @Override //로그인 받아오기
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AllLoginManager.inst.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AllLoginManager.inst.Destroy(this);
    }
}