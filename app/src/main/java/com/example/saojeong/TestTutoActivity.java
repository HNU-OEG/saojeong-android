package com.example.saojeong;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class TestTutoActivity extends AppCompatActivity {
    private testTutoPagerRecyclerAdapter pageradapter;
    private DotsIndicator dotsIndicator;
    private ViewPager2 viewPager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testtutomain);
        setupItems();

        viewPager=findViewById(R.id.my_intro_view_pager);
        dotsIndicator=findViewById(R.id.dots_indicator);
        viewPager.setAdapter(pageradapter);

        dotsIndicator.setViewPager2(viewPager);
    }

    private void setupItems() {
        List<TestTutomodel> itemTuto=new ArrayList<>();

        TestTutomodel itemOnePage=new TestTutomodel(R.drawable.image1, false);
        TestTutomodel itemTwoPage=new TestTutomodel(R.drawable.image2, false);
        TestTutomodel itemThreePage=new TestTutomodel(R.drawable.image3, true);
        itemTuto.add(itemOnePage);
        itemTuto.add(itemTwoPage);
        itemTuto.add(itemThreePage);
        pageradapter=new testTutoPagerRecyclerAdapter(itemTuto);
    }

}

