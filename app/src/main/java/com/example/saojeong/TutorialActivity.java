package com.example.saojeong;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.saojeong.adapter.TutorialAdapter;
import com.example.saojeong.login.AllLoginManager;
import com.example.saojeong.login.kakaoControl;
import com.example.saojeong.model.TutorialValue;
import com.kakao.auth.KakaoSDK;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class TutorialActivity extends AppCompatActivity {
    private TutorialAdapter pageradapter;
    private DotsIndicator dotsIndicator;
    private ViewPager2 viewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        setupItems();

        viewPager=findViewById(R.id.my_intro_view_pager);
        dotsIndicator=findViewById(R.id.dots_indicator);
        viewPager.setAdapter(pageradapter);
        dotsIndicator.setViewPager2(viewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AllLoginManager.getInstance().onActivityResult(requestCode,resultCode,data);

    }

    private void setupItems() {
        List<TutorialValue> itemTuto=new ArrayList<>();

        TutorialValue itemOnePage=new TutorialValue(R.drawable.image1, false);
        TutorialValue itemTwoPage=new TutorialValue(R.drawable.image2, false);
        TutorialValue itemThreePage=new TutorialValue(R.drawable.image3, true);
        itemTuto.add(itemOnePage);
        itemTuto.add(itemTwoPage);
        itemTuto.add(itemThreePage);
        pageradapter=new TutorialAdapter(itemTuto, this, this);
    }

    @Nullable
    public static String getHashKey(Context context) {
        final String TAG = "KeyHash";
        String keyHash = null;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                keyHash = new String(Base64.encode(md.digest(), 0));
                Log.d(TAG, keyHash);
            }
        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }
        return keyHash;
    }
}

