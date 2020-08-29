package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.FitWindowsFrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.ShopDetailAdapter;
import com.example.saojeong.adapter.ShopScoreAdapter;
import com.example.saojeong.adapter.ShopSellListAdapter;
import com.example.saojeong.adapter.ShopStarScoreAdapter;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.model.ContactShopDetail;
import com.example.saojeong.model.ContactShopScore;
import com.example.saojeong.model.ContactShopSellList;
import com.example.saojeong.model.ContactShopStarScore;
import com.example.saojeong.model.RecyclerDecoration;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.response.StarUnstarResponseDto;
import com.example.saojeong.rest.dto.store.StoreDetailDto;
import com.example.saojeong.rest.service.StoreService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShopFragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private HomeFragment homeFragment;
    private ShopListFragment shopListFragment;
    private RecyclerView recyclerShopDetail;
    private RecyclerView recyclerShopScore;
    private RecyclerView recyclerShopSellList;
    private ShopDetailAdapter shopDetailAdapter;
    private ShopScoreAdapter shopScoreAdapter;
    private ShopSellListAdapter shopSellListAdapter;
    ArrayList<ContactShopSellList> contactShopSellLists;
    List<ContactShopScore> contactShopScores;
    List<ContactShopDetail> contactShopDetails;

    private RecyclerView recyclerShopStarScore;
    private ShopStarScoreAdapter shopStarScoreAdapter;
    ArrayList<ContactShopStarScore> contactShopStarScores;

    RecyclerDecoration.LeftDecoration leftDecoration = new RecyclerDecoration.LeftDecoration(1);
    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(50);

    TabHost tabHost_Shop;

    private StoreService storeService;

    public static ShopFragment newInstance() {
        return new ShopFragment();
    }

    public static ShopFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);

        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        storeService = ServiceGenerator.createService(StoreService.class, TokenCase.getToken());

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_shop, container, false);

        TextView tvStoreNumber = rootView.findViewById(R.id.tv_shopnum);
        TextView tvStoreName = rootView.findViewById(R.id.tv_shopname);

        //판매 품목
        recyclerShopSellList = (RecyclerView) rootView.findViewById(R.id.recyclershop_selllist);
        //상세 설명
        recyclerShopDetail = (RecyclerView) rootView.findViewById(R.id.recyclershop_detail);
        //평점
        recyclerShopScore = (RecyclerView) rootView.findViewById(R.id.recyclershop_score);


        if (getArguments() != null) {
            int id = getArguments().getInt("id");
            Log.d("ID", "" + id);
            Call<StoreDetailDto> call = storeService.getStoreDetail(id);
            call.enqueue(new Callback<StoreDetailDto>() {
                @Override
                public void onResponse(Call<StoreDetailDto> call, Response<StoreDetailDto> response) {
                    if (response.code() == 201) {
                        StoreDetailDto body = response.body();
//                        Log.d("DTO", body.toString());
//                        Log.d("DTO.MERCHANDISE", body.getStoreMerchandise().toString());
//                        Log.d("DTO.STORE_DETAIL", body.getStoreDetail().toString());
//                        Log.d("DTO.STORE_DETAIL", body.getStoreGrade().toString());
                        tvStoreName.setText(body.getStoreDetail().getStoreName());
                        tvStoreNumber.setText(body.getStoreDetail().getStoreIndexholder() + "번");
                        contactShopDetails = ContactShopDetail.createContactsList(body.getStoreDetail());
                        shopDetailAdapter = new ShopDetailAdapter(contactShopDetails);

                        // UserType을 넘겨줘서 로그인 후 평가하기 or 평가하기 띄우기
                        contactShopScores = ContactShopScore.createContactsList(body.getStoreGrade(),
                                body.getStoreDetail().getVoteGradeCount());
                        shopScoreAdapter = new ShopScoreAdapter(contactShopScores, getActivity(), id);
                        ImageView storeImage = (ImageView) getView().findViewById(R.id.iv_shopshop);
                        Glide.with(getView())
                                .load(body.getStoreDetail().getStoreImage())
                                .into(storeImage);

                        // 하트 로직
                        ImageView likeImage = (ImageView) getView().findViewById(R.id.iv_like);
                        likeImage.setSelected(body.getStoreDetail().getStarred());
                        likeImage.setOnClickListener(view -> {
                            if (likeImage.isSelected()) {
                                Log.d("UNSTAR BEFORE", "" + likeImage.isSelected());
                                storeService.unstartThisStore(id).enqueue(new Callback<StarUnstarResponseDto>() {
                                    @Override
                                    public void onResponse(Call<StarUnstarResponseDto> call,
                                                           Response<StarUnstarResponseDto> response) {
                                        likeImage.setSelected(!likeImage.isSelected());
                                        Log.d("UNSTAR AFTER", "" + likeImage.isSelected());
                                    }

                                    @Override
                                    public void onFailure(Call<StarUnstarResponseDto> call, Throwable t) {

                                    }
                                });
                            } else {
                                Log.d("STAR BEFORE", "" + likeImage.isSelected());
                                storeService.startThisStore(id).enqueue(new Callback<StarUnstarResponseDto>() {
                                    @Override
                                    public void onResponse(Call<StarUnstarResponseDto> call,
                                                           Response<StarUnstarResponseDto> response) {
                                        likeImage.setSelected(!likeImage.isSelected());
                                        Log.d("STAR AFTER", "" + likeImage.isSelected());
                                    }

                                    @Override
                                    public void onFailure(Call<StarUnstarResponseDto> call, Throwable t) {

                                    }
                                });
                            }
                        });


                        getDefaultAdapters();
                        setAdapters();
                    } else {
                        getDefaultAdapters();
                        setAdapters();
                    }
                }

                @Override
                public void onFailure(Call<StoreDetailDto> call, Throwable t) {
                    getDefaultAdapters();
                    setAdapters();
                    Log.d("FAIL", t.getMessage());
                }
            });
        } else {
            getDefaultAdapters();
            setAdapters();
        }


        //상단 액션바
        Toolbar toolbar = rootView.findViewById(R.id.toolbar_shop);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기버튼
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true); //뒤로가기버튼
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(homeFragment.newInstance());
            }
        });
        //((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.~~); // 뒤로가기 화살표 이미지 바꾸기
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
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

    private void getDefaultAdapters() {
        contactShopSellLists = ContactShopSellList.createContactsList(10);
        shopSellListAdapter = new ShopSellListAdapter(contactShopSellLists);

        contactShopDetails = ContactShopDetail._createContactsList(1);
        shopDetailAdapter = new ShopDetailAdapter(contactShopDetails);

        contactShopScores = ContactShopScore._createContactsList(1);
        shopScoreAdapter = new ShopScoreAdapter(contactShopScores, getActivity());
    }

    private void setAdapters() {
        recyclerShopSellList.addItemDecoration(leftDecoration);
        recyclerShopSellList.addItemDecoration(bottomDecoration);
        recyclerShopSellList.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerShopSellList.setAdapter(shopSellListAdapter);

        recyclerShopDetail.addItemDecoration(leftDecoration);
        recyclerShopDetail.addItemDecoration(bottomDecoration);
        recyclerShopDetail.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerShopDetail.setAdapter(shopDetailAdapter);

        recyclerShopScore.addItemDecoration(leftDecoration);
        recyclerShopScore.addItemDecoration(bottomDecoration);
        recyclerShopScore.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerShopScore.setAdapter(shopScoreAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transaction = fragmentManager.beginTransaction();

    }
}