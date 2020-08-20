package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.LinearLayout;
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
import com.example.saojeong.model.ContactFruitDetail;
import com.example.saojeong.model.ContactFruitScore;
import com.example.saojeong.model.ContactFruitShop;
import com.example.saojeong.model.RecyclerDecoration;

import java.util.ArrayList;


public class FruitShopFragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private FruitFragment fruitFragment;
    private RecyclerView recyclerFruitDetail;
    private RecyclerView recyclerFruitScore;
    private RecyclerView recyclerFruitSellList;
    private FruitDetailAdapter fruitDetailAdapter;
    private FruitScoreAdapter fruitScoreAdapter;
    private FruitSellListAdapter fruitSellListAdapter;
    ArrayList<ContactFruitShop> contactFruitShops;
    ArrayList<ContactFruitScore> contactFruitScores;
    ArrayList<ContactFruitDetail> contactFruitDetails;

    TextView tv_kindscore;
    TextView tv_itemscore;
    TextView tv_pricescore;

    ImageView iv_kindstar1;
    ImageView iv_kindstar2;
    ImageView iv_kindstar3;
    ImageView iv_kindstar4;
    ImageView iv_kindstar5;
    ImageView iv_itemstar1;
    ImageView iv_itemstar2;
    ImageView iv_itemstar3;
    ImageView iv_itemstar4;
    ImageView iv_itemstar5;
    ImageView iv_pricestar1;
    ImageView iv_pricestar2;
    ImageView iv_pricestar3;
    ImageView iv_pricestar4;
    ImageView iv_pricestar5;

    String S_kindscore;
    String S_itemscore;
    String S_pricescore;
    Double kindscore;
    Double itemscore;
    Double pricescore;

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
        contactFruitShops = ContactFruitShop.createContactsList(10);
        fruitSellListAdapter = new FruitSellListAdapter(contactFruitShops);
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
        fruitScoreAdapter = new FruitScoreAdapter(contactFruitScores);
        recyclerFruitScore.addItemDecoration(leftDecoration);
        recyclerFruitScore.addItemDecoration(bottomDecoration);
        recyclerFruitScore.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerFruitScore.setAdapter(fruitScoreAdapter);

        //평점에 따라 변경되는 별 갯수
            //Id 불러오기
        tv_kindscore = (TextView) rootView.findViewById(R.id.tv_fruit_kindscore);
        tv_itemscore = (TextView) rootView.findViewById(R.id.tv_fruit_itemscore);
        tv_pricescore = (TextView) rootView.findViewById(R.id.tv_fruit_pricescore);
        iv_kindstar1 = (ImageView) rootView.findViewById(R.id.iv_fruit_kindstar1);
        iv_kindstar2 = (ImageView) rootView.findViewById(R.id.iv_fruit_kindstar2);
        iv_kindstar3 = (ImageView) rootView.findViewById(R.id.iv_fruit_kindstar3);
        iv_kindstar4 = (ImageView) rootView.findViewById(R.id.iv_fruit_kindstar4);
        iv_kindstar5 = (ImageView) rootView.findViewById(R.id.iv_fruit_kindstar5);
        iv_itemstar1 = (ImageView) rootView.findViewById(R.id.iv_fruit_itemstar1);
        iv_itemstar2 = (ImageView) rootView.findViewById(R.id.iv_fruit_itemstar2);
        iv_itemstar3 = (ImageView) rootView.findViewById(R.id.iv_fruit_itemstar3);
        iv_itemstar4 = (ImageView) rootView.findViewById(R.id.iv_fruit_itemstar4);
        iv_itemstar5 = (ImageView) rootView.findViewById(R.id.iv_fruit_itemstar5);
        iv_pricestar1 = (ImageView) rootView.findViewById(R.id.iv_fruit_pricestar1);
        iv_pricestar2 = (ImageView) rootView.findViewById(R.id.iv_fruit_pricestar2);
        iv_pricestar3 = (ImageView) rootView.findViewById(R.id.iv_fruit_pricestar3);
        iv_pricestar4 = (ImageView) rootView.findViewById(R.id.iv_fruit_pricestar4);
        iv_pricestar5 = (ImageView) rootView.findViewById(R.id.iv_fruit_pricestar5);
            //문자열로 받아오기
        S_kindscore = tv_kindscore.getText().toString();
        S_itemscore = tv_itemscore.getText().toString();
        S_pricescore = tv_pricescore.getText().toString();
//            //Double로 변환
//        kindscore = Double.parseDouble(S_kindscore);
//        itemscore = Double.parseDouble(S_itemscore);
//        pricescore = Double.parseDouble(S_pricescore);
//            //평점에 따라 별 개수 변경(친절도)
//        if(kindscore >= 0 && kindscore < 0.5) {
//            iv_kindstar1.setImageResource(R.drawable.unlike);
//            iv_kindstar2.setImageResource(R.drawable.unlike);
//            iv_kindstar3.setImageResource(R.drawable.unlike);
//            iv_kindstar4.setImageResource(R.drawable.unlike);
//            iv_kindstar5.setImageResource(R.drawable.unlike);
//        }
//        else if(kindscore >= 0.5 && kindscore < 1) {
//            iv_kindstar1.setImageResource(R.drawable.unlike);
//            iv_kindstar2.setImageResource(R.drawable.unlike);
//            iv_kindstar3.setImageResource(R.drawable.unlike);
//            iv_kindstar4.setImageResource(R.drawable.unlike);
//            iv_kindstar5.setImageResource(R.drawable.unlike);
//        }
//        else if(kindscore >= 1 && kindscore < 1.5) {
//            iv_kindstar1.setImageResource(R.drawable.like);
//            iv_kindstar2.setImageResource(R.drawable.unlike);
//            iv_kindstar3.setImageResource(R.drawable.unlike);
//            iv_kindstar4.setImageResource(R.drawable.unlike);
//            iv_kindstar5.setImageResource(R.drawable.unlike);
//        }
//        else if(kindscore >= 1.5 && kindscore < 2) {
//            iv_kindstar1.setImageResource(R.drawable.like);
//            iv_kindstar2.setImageResource(R.drawable.unlike);
//            iv_kindstar3.setImageResource(R.drawable.unlike);
//            iv_kindstar4.setImageResource(R.drawable.unlike);
//            iv_kindstar5.setImageResource(R.drawable.unlike);
//        }
//        else if(kindscore >= 2 && kindscore < 2.5) {
//            iv_kindstar1.setImageResource(R.drawable.like);
//            iv_kindstar2.setImageResource(R.drawable.like);
//            iv_kindstar3.setImageResource(R.drawable.unlike);
//            iv_kindstar4.setImageResource(R.drawable.unlike);
//            iv_kindstar5.setImageResource(R.drawable.unlike);
//        }
//        else if(kindscore >= 2.5 && kindscore < 3) {
//            iv_kindstar1.setImageResource(R.drawable.like);
//            iv_kindstar2.setImageResource(R.drawable.like);
//            iv_kindstar3.setImageResource(R.drawable.unlike);
//            iv_kindstar4.setImageResource(R.drawable.unlike);
//            iv_kindstar5.setImageResource(R.drawable.unlike);
//        }
//        else if(kindscore >= 3 && kindscore < 3.5) {
//            iv_kindstar1.setImageResource(R.drawable.like);
//            iv_kindstar2.setImageResource(R.drawable.like);
//            iv_kindstar3.setImageResource(R.drawable.like);
//            iv_kindstar4.setImageResource(R.drawable.unlike);
//            iv_kindstar5.setImageResource(R.drawable.unlike);
//        }
//        else if(kindscore >= 3.5 && kindscore < 4) {
//            iv_kindstar1.setImageResource(R.drawable.like);
//            iv_kindstar2.setImageResource(R.drawable.like);
//            iv_kindstar3.setImageResource(R.drawable.like);
//            iv_kindstar4.setImageResource(R.drawable.unlike);
//            iv_kindstar5.setImageResource(R.drawable.unlike);
//        }
//        else if(kindscore >= 4 && kindscore < 4.5) {
//            iv_kindstar1.setImageResource(R.drawable.like);
//            iv_kindstar2.setImageResource(R.drawable.like);
//            iv_kindstar3.setImageResource(R.drawable.like);
//            iv_kindstar4.setImageResource(R.drawable.like);
//            iv_kindstar5.setImageResource(R.drawable.unlike);
//        }
//        else if(kindscore >= 4.5 && kindscore < 5) {
//            iv_kindstar1.setImageResource(R.drawable.like);
//            iv_kindstar2.setImageResource(R.drawable.like);
//            iv_kindstar3.setImageResource(R.drawable.like);
//            iv_kindstar4.setImageResource(R.drawable.like);
//            iv_kindstar5.setImageResource(R.drawable.unlike);
//        }
//        else if(kindscore == 5) {
//            iv_kindstar1.setImageResource(R.drawable.like);
//            iv_kindstar2.setImageResource(R.drawable.like);
//            iv_kindstar3.setImageResource(R.drawable.like);
//            iv_kindstar4.setImageResource(R.drawable.like);
//            iv_kindstar5.setImageResource(R.drawable.like);
//        }


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