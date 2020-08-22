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
import com.example.saojeong.adapter.FishDetailAdapter;
import com.example.saojeong.adapter.FishScoreAdapter;
import com.example.saojeong.adapter.FishSellListAdapter;
import com.example.saojeong.adapter.FishStarScoreAdapter;
import com.example.saojeong.model.ContactFishDetail;
import com.example.saojeong.model.ContactFishScore;
import com.example.saojeong.model.ContactFishSellList;
import com.example.saojeong.model.ContactFishStarScore;
import com.example.saojeong.model.RecyclerDecoration;

import java.util.ArrayList;


public class FishShopFragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private RecyclerView recyclerFishDetail;
    private RecyclerView recyclerFishScore;
    private RecyclerView recyclerFishSellList;
    private FishDetailAdapter fishDetailAdapter;
    private FishScoreAdapter fishScoreAdapter;
    private FishSellListAdapter fishSellListAdapter;
    ArrayList<ContactFishSellList> contactFishSellLists;
    ArrayList<ContactFishScore> contactFishScores;
    ArrayList<ContactFishDetail> contactFishDetails;

    private RecyclerView recyclerFishStarScore;
    private FishStarScoreAdapter fishStarScoreAdapter;
    ArrayList<ContactFishStarScore> contactFishStarScores;

    RecyclerDecoration.LeftDecoration leftDecoration = new RecyclerDecoration.LeftDecoration(1);
    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(50);

    TabHost tabHost_Fish;

    public static FishShopFragment newInstance() {
        return new FishShopFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_fish, container, false);
        //판매 품목
        recyclerFishSellList = (RecyclerView) rootView.findViewById(R.id.recyclerfish_selllist);
        contactFishSellLists = ContactFishSellList.createContactsList(10);
        fishSellListAdapter = new FishSellListAdapter(contactFishSellLists);
        recyclerFishSellList.addItemDecoration(leftDecoration);
        recyclerFishSellList.addItemDecoration(bottomDecoration);
        recyclerFishSellList.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerFishSellList.setAdapter(fishSellListAdapter);

        //상세 설명
        recyclerFishDetail = (RecyclerView) rootView.findViewById(R.id.recyclerfish_detail);
        contactFishDetails = ContactFishDetail.createContactsList(1);
        fishDetailAdapter = new FishDetailAdapter(contactFishDetails);
        recyclerFishDetail.addItemDecoration(leftDecoration);
        recyclerFishDetail.addItemDecoration(bottomDecoration);
        recyclerFishDetail.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerFishDetail.setAdapter(fishDetailAdapter);

        //평점
        recyclerFishScore = (RecyclerView) rootView.findViewById(R.id.recyclerfish_score);
        contactFishScores = ContactFishScore.createContactsList(1);
        fishScoreAdapter = new FishScoreAdapter(contactFishScores, getActivity());
        recyclerFishScore.addItemDecoration(leftDecoration);
        recyclerFishScore.addItemDecoration(bottomDecoration);
        recyclerFishScore.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerFishScore.setAdapter(fishScoreAdapter);

        //상단 액션바
        Toolbar toolbar = rootView.findViewById(R.id.toolbar_fish);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.~~); // 뒤로가기 화살표 이미지 바꾸기
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("");
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.BLACK);

        //탭 호스트
        tabHost_Fish = (TabHost) rootView.findViewById(R.id.tabhost_fish);
        tabHost_Fish.setup();

        TabHost.TabSpec selllist = tabHost_Fish.newTabSpec("판매 품목");
        selllist.setContent(R.id.tabSl);
        selllist.setIndicator("판매 품목"); //Tab Name
        TabHost.TabSpec detail = tabHost_Fish.newTabSpec("상세 정보");
        detail.setContent(R.id.tabDe);
        detail.setIndicator("상세 정보"); //Tab Name
        TabHost.TabSpec score = tabHost_Fish.newTabSpec("평점");
        score.setContent(R.id.tabSc);
        score.setIndicator("평점"); //Tab Name

        tabHost_Fish.addTab(selllist);
        tabHost_Fish.addTab(detail);
        tabHost_Fish.addTab(score);

        //탭 밑줄 없애기
        for (int i = 0; i < tabHost_Fish.getTabWidget().getChildCount(); i++) {
            tabHost_Fish.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
            tabHost_Fish.getTabWidget().setStripEnabled(false);
        }

        //시작시 Tab Color 지정
        TextView Cselllist = (TextView) tabHost_Fish.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        Cselllist.setTextColor(Color.parseColor("#f67043"));
        TextView Cdetail = (TextView) tabHost_Fish.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        Cdetail.setTextColor(Color.parseColor("#8c8c8c"));
        TextView Cscore = (TextView) tabHost_Fish.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        Cscore.setTextColor(Color.parseColor("#8c8c8c"));

        //Tab 바꿀 때 마다 색 변경
        tabHost_Fish.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                for (int i = 0; i < tabHost_Fish.getTabWidget().getChildCount(); i++) {
                    TextView tabcolor = (TextView) tabHost_Fish.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                    if (i == tabHost_Fish.getCurrentTab()) {
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