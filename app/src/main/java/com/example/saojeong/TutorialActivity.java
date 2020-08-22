package com.example.saojeong;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.saojeong.adapter.TutorialAdapter;
import com.example.saojeong.model.TutorialValue;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

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

    private void setupItems() {
        List<TutorialValue> itemTuto=new ArrayList<>();

        TutorialValue itemOnePage=new TutorialValue(R.drawable.image1, false);
        TutorialValue itemTwoPage=new TutorialValue(R.drawable.image2, false);
        TutorialValue itemThreePage=new TutorialValue(R.drawable.image3, true);
        itemTuto.add(itemOnePage);
        itemTuto.add(itemTwoPage);
        itemTuto.add(itemThreePage);
        pageradapter=new TutorialAdapter(itemTuto, this);
    }

}

