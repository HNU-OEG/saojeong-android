package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.FruitDetailAdapter;
import com.example.saojeong.adapter.FruitScoreAdapter;
import com.example.saojeong.adapter.FruitSellListAdapter;
import com.example.saojeong.adapter.FruitStarScoreAdapter;
import com.example.saojeong.model.ContactFruitDetail;
import com.example.saojeong.model.ContactFruitScore;
import com.example.saojeong.model.ContactFruitSellList;
import com.example.saojeong.model.ContactFruitStarScore;
import com.example.saojeong.model.RecyclerDecoration;

import java.util.ArrayList;


public class FruitShopFragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private RecyclerView recyclerFruitDetail;
    private RecyclerView recyclerFruitScore;
    private RecyclerView recyclerFruitSellList;
    private FruitDetailAdapter fruitDetailAdapter;
    private FruitScoreAdapter fruitScoreAdapter;
    private FruitSellListAdapter fruitSellListAdapter;
    ArrayList<ContactFruitSellList> contactFruitSellLists;
    ArrayList<ContactFruitScore> contactFruitScores;
    ArrayList<ContactFruitDetail> contactFruitDetails;

    private RecyclerView recyclerFruitStarScore;
    private FruitStarScoreAdapter fruitStarScoreAdapter;
    ArrayList<ContactFruitStarScore> contactFruitStarScores;

    RecyclerDecoration.LeftDecoration leftDecoration = new RecyclerDecoration.LeftDecoration(1);
    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(50);

    TabHost tabHost_Fruit;

    public static FruitShopFragment newInstance() {
        return new FruitShopFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_fruit, container, false);
        //판매 품목
        recyclerFruitSellList = (RecyclerView) rootView.findViewById(R.id.recyclerfruit_selllist);
        contactFruitSellLists = ContactFruitSellList.createContactsList(10);
        fruitSellListAdapter = new FruitSellListAdapter(contactFruitSellLists);
        recyclerFruitSellList.addItemDecoration(leftDecoration);
        recyclerFruitSellList.addItemDecoration(bottomDecoration);
        recyclerFruitSellList.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerFruitSellList.setAdapter(fruitSellListAdapter);

        //상세 설명
        recyclerFruitDetail = (RecyclerView) rootView.findViewById(R.id.recyclerfruit_detail);
        contactFruitDetails = ContactFruitDetail.createContactsList(1);
        fruitDetailAdapter = new FruitDetailAdapter(contactFruitDetails);
        recyclerFruitDetail.addItemDecoration(leftDecoration);
        recyclerFruitDetail.addItemDecoration(bottomDecoration);
        recyclerFruitDetail.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerFruitDetail.setAdapter(fruitDetailAdapter);

        //평점
        recyclerFruitScore = (RecyclerView) rootView.findViewById(R.id.recyclerfruit_score);
        contactFruitScores = ContactFruitScore.createContactsList(1);
        fruitScoreAdapter = new FruitScoreAdapter(contactFruitScores, getActivity());
        recyclerFruitScore.addItemDecoration(leftDecoration);
        recyclerFruitScore.addItemDecoration(bottomDecoration);
        recyclerFruitScore.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerFruitScore.setAdapter(fruitScoreAdapter);

        //상단 액션바
        Toolbar toolbar = rootView.findViewById(R.id.toolbar_fruit);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.~~); // 뒤로가기 화살표 이미지 바꾸기
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("");
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.BLACK);

        //탭 호스트
        tabHost_Fruit = (TabHost) rootView.findViewById(R.id.tabhost_fruit);
        tabHost_Fruit.setup();

        TabHost.TabSpec selllist = tabHost_Fruit.newTabSpec("판매 품목");
        selllist.setContent(R.id.tabSl);
        selllist.setIndicator("판매 품목"); //Tab Name
        TabHost.TabSpec detail = tabHost_Fruit.newTabSpec("상세 정보");
        detail.setContent(R.id.tabDe);
        detail.setIndicator("상세 정보"); //Tab Name
        TabHost.TabSpec score = tabHost_Fruit.newTabSpec("평점");
        score.setContent(R.id.tabSc);
        score.setIndicator("평점"); //Tab Name

        tabHost_Fruit.addTab(selllist);
        tabHost_Fruit.addTab(detail);
        tabHost_Fruit.addTab(score);

        //탭 밑줄 없애기
        for (int i = 0; i < tabHost_Fruit.getTabWidget().getChildCount(); i++) {
            tabHost_Fruit.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
            tabHost_Fruit.getTabWidget().setStripEnabled(false);
        }

        //시작시 Tab Color 지정
        TextView Cselllist = (TextView) tabHost_Fruit.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        Cselllist.setTextColor(Color.parseColor("#f67043"));
        TextView Cdetail = (TextView) tabHost_Fruit.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        Cdetail.setTextColor(Color.parseColor("#8c8c8c"));
        TextView Cscore = (TextView) tabHost_Fruit.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        Cscore.setTextColor(Color.parseColor("#8c8c8c"));

        //Tab 바꿀 때 마다 색 변경
        tabHost_Fruit.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                for (int i = 0; i < tabHost_Fruit.getTabWidget().getChildCount(); i++) {
                    TextView tabcolor = (TextView) tabHost_Fruit.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                    if (i == tabHost_Fruit.getCurrentTab()) {
                        tabcolor.setTextColor(Color.parseColor("#f67043"));
                    } else
                        tabcolor.setTextColor(Color.parseColor("#8c8c8c"));

                }
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transaction = fragmentManager.beginTransaction();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                getActivity().finish(); // 뒤로가기가 왜 안되지?
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}