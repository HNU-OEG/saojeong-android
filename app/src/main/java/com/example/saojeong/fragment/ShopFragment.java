package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import com.bumptech.glide.Glide;
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

    /**
     * Activity에서 알고있는 정보를 Fragment로 전송하기 위해서 Bundle을 사용합니다.
     * 먼저 Bundle을 생성하고 매개변수로 넘겨받은 id를 Bundle에 추가합니다. (80~81번째 줄)
     * 새로운 Fragment를 생성하고 id가 추가된 Bundle을 등록하고 리턴합니다.
     * 이제 다시 HomeFragment.java/189번째 줄로 와주세요.
     */
    public static ShopFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);

        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * 이제 드디어 넘겨받은 id를 가지고 통신을 할 수 있습니다.
         * 아래처럼 Service를 생성하고 그 아래에서 getArguments()로 Bundle을 넘겨받을 수 있습니다.
         * 만약 newInstance()에서 Bundle을 생성하지 않았다면 getArguments는 null입니다.
         * 그런데 getArguments가 null이면 앱이 종료되므로 if문을 통해 != null일 때만 통신을 하도록 설정합니다.
         * 만약 null이 아니라면 getArguments().getInt("id")를 통해 id를 받아옵니다.
         * id를 활용해서 /api/store/{id} 요청을 날립니다.
         * 성공적으로 통신이 완료되고 Dto를 기반으로 각 RecyclerView들에 아이템을 할당하면 됩니다.
         * 제 개인적인 생각이지만 통신 코드 안에서는 데이터들만 받아서 전역변수에 할당하고
         * RecyclerView에 각 아이템들을 등록하는 것은 지금 작성된 코드처럼 enqueue() 밖에서 하는게 나을 것 같습니다.
         */
        storeService = ServiceGenerator.createService(StoreService.class, TokenCase.getToken());

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_shop, container, false);
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
                        Log.d("DTO", body.toString());
                        Log.d("DTO.MERCHANDISE", body.getStoreMerchandise().toString());
                        Log.d("DTO.STORE_DETAIL", body.getStoreDetail().toString());
                        Log.d("DTO.STORE_DETAIL", body.getStoreGrade().toString());
                        contactShopDetails = ContactShopDetail.createContactsList(body.getStoreDetail());
                        shopDetailAdapter = new ShopDetailAdapter(contactShopDetails);

                        contactShopScores = ContactShopScore.createContactsList(body.getStoreGrade(),
                                body.getStoreDetail().getVoteGradeCount());
                        shopScoreAdapter = new ShopScoreAdapter(contactShopScores, getActivity(), id);


                        ImageView storeImage = (ImageView) getView().findViewById(R.id.iv_shopshop);
                        Glide.with(getView()).load(body.getStoreDetail().getStoreImage()).into(storeImage);

                        ImageView likeImage = (ImageView) getView().findViewById(R.id.iv_like);
                        if (!body.getStoreDetail().getStarred()) {
                            likeImage.setImageResource(R.drawable.unlike);
                        }



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

//        contactShopDetails = ContactShopDetail._createContactsList(1);
//        shopDetailAdapter = new ShopDetailAdapter(contactShopDetails);

//        contactShopScores = ContactShopScore._createContactsList(1);
//        shopScoreAdapter = new ShopScoreAdapter(contactShopScores, getActivity());
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