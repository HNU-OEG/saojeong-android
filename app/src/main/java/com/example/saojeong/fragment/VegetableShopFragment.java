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
import com.example.saojeong.adapter.VegetableDetailAdapter;
import com.example.saojeong.adapter.VegetableScoreAdapter;
import com.example.saojeong.adapter.VegetableSellListAdapter;
import com.example.saojeong.adapter.VegetableStarScoreAdapter;
import com.example.saojeong.model.ContactVegetableDetail;
import com.example.saojeong.model.ContactVegetableScore;
import com.example.saojeong.model.ContactVegetableSellList;
import com.example.saojeong.model.ContactVegetableStarScore;
import com.example.saojeong.model.RecyclerDecoration;

import java.util.ArrayList;


public class VegetableShopFragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private RecyclerView recyclerVegetableDetail;
    private RecyclerView recyclerVegetableScore;
    private RecyclerView recyclerVegetableSellList;
    private VegetableDetailAdapter vegetableDetailAdapter;
    private VegetableScoreAdapter vegetableScoreAdapter;
    private VegetableSellListAdapter vegetableSellListAdapter;
    ArrayList<ContactVegetableSellList> contactVegetableSellLists;
    ArrayList<ContactVegetableScore> contactVegetableScores;
    ArrayList<ContactVegetableDetail> contactVegetableDetails;

    private RecyclerView recyclerVegetableStarScore;
    private VegetableStarScoreAdapter vegetableStarScoreAdapter;
    ArrayList<ContactVegetableStarScore> contactVegetableStarScores;

    RecyclerDecoration.LeftDecoration leftDecoration = new RecyclerDecoration.LeftDecoration(1);
    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(50);

    TabHost tabHost_Vegetable;

    public static VegetableShopFragment newInstance() {
        return new VegetableShopFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_vegetable, container, false);
        //판매 품목
        recyclerVegetableSellList = (RecyclerView) rootView.findViewById(R.id.recyclervegetable_selllist);
        contactVegetableSellLists = ContactVegetableSellList.createContactsList(10);
        vegetableSellListAdapter = new VegetableSellListAdapter(contactVegetableSellLists);
        recyclerVegetableSellList.addItemDecoration(leftDecoration);
        recyclerVegetableSellList.addItemDecoration(bottomDecoration);
        recyclerVegetableSellList.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerVegetableSellList.setAdapter(vegetableSellListAdapter);

        //상세 설명
        recyclerVegetableDetail = (RecyclerView) rootView.findViewById(R.id.recyclervegetable_detail);
        contactVegetableDetails = ContactVegetableDetail.createContactsList(1);
        vegetableDetailAdapter = new VegetableDetailAdapter(contactVegetableDetails);
        recyclerVegetableDetail.addItemDecoration(leftDecoration);
        recyclerVegetableDetail.addItemDecoration(bottomDecoration);
        recyclerVegetableDetail.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerVegetableDetail.setAdapter(vegetableDetailAdapter);

        //평점
        recyclerVegetableScore = (RecyclerView) rootView.findViewById(R.id.recyclervegetable_score);
        contactVegetableScores = ContactVegetableScore.createContactsList(1);
        vegetableScoreAdapter = new VegetableScoreAdapter(contactVegetableScores, getActivity());
        recyclerVegetableScore.addItemDecoration(leftDecoration);
        recyclerVegetableScore.addItemDecoration(bottomDecoration);
        recyclerVegetableScore.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerVegetableScore.setAdapter(vegetableScoreAdapter);

        //상단 액션바
        Toolbar toolbar = rootView.findViewById(R.id.toolbar_vegetable);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.~~); // 뒤로가기 화살표 이미지 바꾸기
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("");
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.BLACK);

        //탭 호스트
        tabHost_Vegetable = (TabHost) rootView.findViewById(R.id.tabhost_vegetable);
        tabHost_Vegetable.setup();

        TabHost.TabSpec selllist = tabHost_Vegetable.newTabSpec("판매 품목");
        selllist.setContent(R.id.tabSl);
        selllist.setIndicator("판매 품목"); //Tab Name
        TabHost.TabSpec detail = tabHost_Vegetable.newTabSpec("상세 정보");
        detail.setContent(R.id.tabDe);
        detail.setIndicator("상세 정보"); //Tab Name
        TabHost.TabSpec score = tabHost_Vegetable.newTabSpec("평점");
        score.setContent(R.id.tabSc);
        score.setIndicator("평점"); //Tab Name

        tabHost_Vegetable.addTab(selllist);
        tabHost_Vegetable.addTab(detail);
        tabHost_Vegetable.addTab(score);

        //탭 밑줄 없애기
        for (int i = 0; i < tabHost_Vegetable.getTabWidget().getChildCount(); i++) {
            tabHost_Vegetable.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
            tabHost_Vegetable.getTabWidget().setStripEnabled(false);
        }

        //시작시 Tab Color 지정
        TextView Cselllist = (TextView) tabHost_Vegetable.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        Cselllist.setTextColor(Color.parseColor("#f67043"));
        TextView Cdetail = (TextView) tabHost_Vegetable.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        Cdetail.setTextColor(Color.parseColor("#8c8c8c"));
        TextView Cscore = (TextView) tabHost_Vegetable.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        Cscore.setTextColor(Color.parseColor("#8c8c8c"));

        //Tab 바꿀 때 마다 색 변경
        tabHost_Vegetable.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                for (int i = 0; i < tabHost_Vegetable.getTabWidget().getChildCount(); i++) {
                    TextView tabcolor = (TextView) tabHost_Vegetable.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                    if (i == tabHost_Vegetable.getCurrentTab()) {
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