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
import com.example.saojeong.adapter.ShopDetailAdapter;
import com.example.saojeong.adapter.ShopScoreAdapter;
import com.example.saojeong.adapter.ShopSellListAdapter;
import com.example.saojeong.adapter.ShopStarScoreAdapter;
import com.example.saojeong.model.ContactShopDetail;
import com.example.saojeong.model.ContactShopScore;
import com.example.saojeong.model.ContactShopSellList;
import com.example.saojeong.model.ContactShopStarScore;
import com.example.saojeong.model.RecyclerDecoration;

import java.util.ArrayList;


public class ShopFragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private RecyclerView recyclerShopDetail;
    private RecyclerView recyclerShopScore;
    private RecyclerView recyclerShopSellList;
    private ShopDetailAdapter shopDetailAdapter;
    private ShopScoreAdapter shopScoreAdapter;
    private ShopSellListAdapter shopSellListAdapter;
    ArrayList<ContactShopSellList> contactShopSellLists;
    ArrayList<ContactShopScore> contactShopScores;
    ArrayList<ContactShopDetail> contactShopDetails;

    private RecyclerView recyclerShopStarScore;
    private ShopStarScoreAdapter shopStarScoreAdapter;
    ArrayList<ContactShopStarScore> contactShopStarScores;

    RecyclerDecoration.LeftDecoration leftDecoration = new RecyclerDecoration.LeftDecoration(1);
    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(50);

    TabHost tabHost_Shop;

    public static ShopFragment newInstance() {
        return new ShopFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_shop, container, false);
        //판매 품목
        recyclerShopSellList = (RecyclerView) rootView.findViewById(R.id.recyclershop_selllist);
        contactShopSellLists = ContactShopSellList.createContactsList(10);
        shopSellListAdapter = new ShopSellListAdapter(contactShopSellLists);
        recyclerShopSellList.addItemDecoration(leftDecoration);
        recyclerShopSellList.addItemDecoration(bottomDecoration);
        recyclerShopSellList.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerShopSellList.setAdapter(shopSellListAdapter);

        //상세 설명
        recyclerShopDetail = (RecyclerView) rootView.findViewById(R.id.recyclershop_detail);
        contactShopDetails = ContactShopDetail.createContactsList(1);
        shopDetailAdapter = new ShopDetailAdapter(contactShopDetails);
        recyclerShopDetail.addItemDecoration(leftDecoration);
        recyclerShopDetail.addItemDecoration(bottomDecoration);
        recyclerShopDetail.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerShopDetail.setAdapter(shopDetailAdapter);

        //평점
        recyclerShopScore = (RecyclerView) rootView.findViewById(R.id.recyclershop_score);
        contactShopScores = ContactShopScore.createContactsList(1);
        shopScoreAdapter = new ShopScoreAdapter(contactShopScores, getActivity());
        recyclerShopScore.addItemDecoration(leftDecoration);
        recyclerShopScore.addItemDecoration(bottomDecoration);
        recyclerShopScore.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerShopScore.setAdapter(shopScoreAdapter);

        //상단 액션바
        Toolbar toolbar = rootView.findViewById(R.id.toolbar_shop);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.~~); // 뒤로가기 화살표 이미지 바꾸기
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("");
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.BLACK);

        //탭 호스트
        tabHost_Shop = (TabHost) rootView.findViewById(R.id.tabhost_shop);
        tabHost_Shop.setup();

        TabHost.TabSpec selllist = tabHost_Shop.newTabSpec("판매 품목");
        selllist.setContent(R.id.tabSl);
        selllist.setIndicator("판매 품목"); //Tab Name
        TabHost.TabSpec detail = tabHost_Shop.newTabSpec("상세 정보");
        detail.setContent(R.id.tabDe);
        detail.setIndicator("상세 정보"); //Tab Name
        TabHost.TabSpec score = tabHost_Shop.newTabSpec("평점");
        score.setContent(R.id.tabSc);
        score.setIndicator("평점"); //Tab Name

        tabHost_Shop.addTab(selllist);
        tabHost_Shop.addTab(detail);
        tabHost_Shop.addTab(score);

        //탭 밑줄 없애기
        for (int i = 0; i < tabHost_Shop.getTabWidget().getChildCount(); i++) {
            tabHost_Shop.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
            tabHost_Shop.getTabWidget().setStripEnabled(false);
        }

        //시작시 Tab Color 지정
        TextView Cselllist = (TextView) tabHost_Shop.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        Cselllist.setTextColor(Color.parseColor("#f67043"));
        TextView Cdetail = (TextView) tabHost_Shop.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        Cdetail.setTextColor(Color.parseColor("#8c8c8c"));
        TextView Cscore = (TextView) tabHost_Shop.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        Cscore.setTextColor(Color.parseColor("#8c8c8c"));

        //Tab 바꿀 때 마다 색 변경
        tabHost_Shop.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                for (int i = 0; i < tabHost_Shop.getTabWidget().getChildCount(); i++) {
                    TextView tabcolor = (TextView) tabHost_Shop.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                    if (i == tabHost_Shop.getCurrentTab()) {
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