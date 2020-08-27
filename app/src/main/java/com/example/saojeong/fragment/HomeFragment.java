package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.AnnounceAdapter;
import com.example.saojeong.adapter.FoodAdapter;
import com.example.saojeong.adapter.LikeStoreAdapter;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.model.CommunityValue;
import com.example.saojeong.model.ContactAnnounce;
import com.example.saojeong.model.ContactFood;
import com.example.saojeong.model.ContactShopOC;
import com.example.saojeong.model.LikeStore;
import com.example.saojeong.model.OnItemClickListener;
import com.example.saojeong.model.RecyclerDecoration;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.board.GetPostListArrayDto;
import com.example.saojeong.rest.dto.board.GetPostListDto;
import com.example.saojeong.rest.dto.home.AnnounceDto;
import com.example.saojeong.rest.dto.SeasonalFoodDto;
import com.example.saojeong.rest.dto.response.GetNewsesResponseDto;
import com.example.saojeong.rest.dto.store.StoreDto;
import com.example.saojeong.rest.service.AnnounceService;
import com.example.saojeong.rest.service.BoardService;
import com.example.saojeong.rest.service.SeasonalFoodService;
import com.example.saojeong.rest.service.StoreService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class HomeFragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private ShopListFragment fruitFragment;
    private ShopListFragment fishFragment;
    private ShopListFragment vegetableFragment;
    private HomeFragment homeFragment;
    private ShopFragment shopFragment;
    private RecyclerView recyclerShop;
    private RecyclerView recyclerFruits;
    private RecyclerView recyclerVegetables;
    private RecyclerView recyclerSeafoods;
    private FoodAdapter fruitAdapter;
    private FoodAdapter vegetableAdapter;
    private FoodAdapter seafoodsAdapter;

    private LikeStoreAdapter likeStoreAdapter;
    private RecyclerView recyclerAnnounce;
    private AnnounceAdapter announceAdapter;

    List<ContactShopOC> likeStores;
    List<ContactFood> contactFoods;
    List<CommunityValue> contactCommunityValue;

    private StoreService storeService;
    private BoardService boardService;
    private SeasonalFoodService seasonalFoodService;

    RecyclerDecoration.LeftDecoration leftDecoration = new RecyclerDecoration.LeftDecoration(50);

    private InputMethodManager imm;

    TabHost tabHost;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        storeService = ServiceGenerator.createService(StoreService.class, TokenCase.getToken());
        boardService = ServiceGenerator.createService(BoardService.class, TokenCase.getToken());
        seasonalFoodService = ServiceGenerator.createService(SeasonalFoodService.class, TokenCase.getToken());

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);

        fruitFragment = new ShopListFragment(); // 과일동 Fragment 선언
        vegetableFragment = new ShopListFragment(); // 채소동 Fragment 선언
        fishFragment = new ShopListFragment(); // 수산동 Fragment 선언

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        //매장 Recycler View
        recyclerShop = (RecyclerView) rootView.findViewById(R.id.recyclershop_fragment);
        loadStores(this);

        //과일 Recycler View
        recyclerFruits = (RecyclerView) rootView.findViewById(R.id.recyclerfruit_fragment);
        recyclerVegetables = (RecyclerView) rootView.findViewById(R.id.recyclervegetable_fragment);
        recyclerSeafoods = (RecyclerView) rootView.findViewById(R.id.recyclerfish_fragment);
        loadFoods(this);

        //전체 보기(공지) Recycler View
        recyclerAnnounce = (RecyclerView) rootView.findViewById(R.id.recyclerannounce_fragment);
        loadAnnounces(this);

        tabHost = (TabHost) rootView.findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec fruit = tabHost.newTabSpec("과일");
        fruit.setContent(R.id.tabFr);
        fruit.setIndicator("과일"); //Tab Name
        TabHost.TabSpec vegetable = tabHost.newTabSpec("채소");
        vegetable.setContent(R.id.tabVe);
        vegetable.setIndicator("채소"); //Tab Name
        TabHost.TabSpec fish = tabHost.newTabSpec("수산");
        fish.setContent(R.id.tabFi);
        fish.setIndicator("수산"); //Tab Name

        tabHost.addTab(fruit);
        tabHost.addTab(vegetable);
        tabHost.addTab(fish);

        //시작시 Tab Color 지정
        TextView Cfruit = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        Cfruit.setTextColor(Color.parseColor("#ffffff"));
        TextView CVegetable = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        CVegetable.setTextColor(Color.parseColor("#ffd6b7"));
        TextView Cfish = (TextView) tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        Cfish.setTextColor(Color.parseColor("#ffd6b7"));

        //Tab 바꿀 때 마다 색 변경
        tabHost.setOnTabChangedListener((TabHost.OnTabChangeListener) s -> {
            for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                TextView tabcolor = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                if (i == tabHost.getCurrentTab()) {
                    tabcolor.setTextColor(Color.parseColor("#ffffff"));
                } else
                    tabcolor.setTextColor(Color.parseColor("#ffd6b7"));
            }
        });
        return rootView;
    }

    private void loadStores(HomeFragment homeFragment) {
        storeService.getStoreListOrderByGrade().enqueue(new Callback<List<StoreDto>>() {
            @Override
            public void onResponse(Call<List<StoreDto>> call, Response<List<StoreDto>> response) {
                if (response.code() == 201) {
                    List<StoreDto> body = response.body();
                    likeStores = ContactShopOC.createContactsList(body);
                    likeStoreAdapter = new LikeStoreAdapter(Glide.with(homeFragment), likeStores);
                } else {
                    likeStores = ContactShopOC._createContactsList(20);
                    likeStoreAdapter = new LikeStoreAdapter(likeStores);
                }
                recyclerShop.addItemDecoration(leftDecoration);
                recyclerShop.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));

                likeStoreAdapter.setOnItemClickListener(holder -> {
                    ShopFragment targetFragment = shopFragment.newInstance(holder.storeId);
                    MainActivity activity = (MainActivity) getActivity();
                    activity.replaceFragment(targetFragment);
                });

                recyclerShop.setAdapter(likeStoreAdapter);

            }

            @Override
            public void onFailure(Call<List<StoreDto>> call, Throwable t) {
                // 안드로이드에서 문제 발생 (네트워크 환경 체크해보기)
                likeStores = ContactShopOC._createContactsList(20);
                likeStoreAdapter = new LikeStoreAdapter(likeStores);
                recyclerShop.addItemDecoration(leftDecoration);
                recyclerShop.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerShop.setAdapter(likeStoreAdapter);
            }
        });
    }

    private void loadFoods(HomeFragment homeFragment) {
        seasonalFoodService.getSeasonalFood("과일", 9).enqueue(new Callback<List<SeasonalFoodDto>>() {
            @Override
            public void onResponse(Call<List<SeasonalFoodDto>> call, Response<List<SeasonalFoodDto>> response) {
                List<SeasonalFoodDto> body = response.body();
                if (response.code() == 201) {
                    contactFoods = ContactFood.createContactsList(body);
                    fruitAdapter = new FoodAdapter(Glide.with(homeFragment), contactFoods);
                } else {
                    contactFoods = ContactFood._createContactsList(20);
                    fruitAdapter = new FoodAdapter(contactFoods);
                }
                recyclerFruits.addItemDecoration(leftDecoration);
                recyclerFruits.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerFruits.setAdapter(fruitAdapter);
            }

            @Override
            public void onFailure(Call<List<SeasonalFoodDto>> call, Throwable t) {
                contactFoods = ContactFood._createContactsList(20);
                fruitAdapter = new FoodAdapter(contactFoods);
                recyclerFruits.addItemDecoration(leftDecoration);
                recyclerFruits.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerFruits.setAdapter(fruitAdapter);
            }
        });
        seasonalFoodService.getSeasonalFood("채소", 9).enqueue(new Callback<List<SeasonalFoodDto>>() {
            @Override
            public void onResponse(Call<List<SeasonalFoodDto>> call, Response<List<SeasonalFoodDto>> response) {
                List<SeasonalFoodDto> body = response.body();
                if (response.code() == 201) {
                    contactFoods = ContactFood.createContactsList(body);
                    vegetableAdapter = new FoodAdapter(Glide.with(homeFragment), contactFoods);
                } else {
                    contactFoods = ContactFood._createContactsList(20);
                    vegetableAdapter = new FoodAdapter(contactFoods);
                }
                recyclerVegetables.addItemDecoration(leftDecoration);
                recyclerVegetables.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerVegetables.setAdapter(vegetableAdapter);
            }

            @Override
            public void onFailure(Call<List<SeasonalFoodDto>> call, Throwable t) {
                contactFoods = ContactFood._createContactsList(20);
                vegetableAdapter = new FoodAdapter(contactFoods);
                recyclerVegetables.addItemDecoration(leftDecoration);
                recyclerVegetables.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerVegetables.setAdapter(vegetableAdapter);
            }
        });

        seasonalFoodService.getSeasonalFood("수산", 9).enqueue(new Callback<List<SeasonalFoodDto>>() {
            @Override
            public void onResponse(Call<List<SeasonalFoodDto>> call, Response<List<SeasonalFoodDto>> response) {
                List<SeasonalFoodDto> body = response.body();
                if (response.code() == 201) {
                    contactFoods = ContactFood.createContactsList(body);
                    seafoodsAdapter = new FoodAdapter(Glide.with(homeFragment), contactFoods);
                } else {
                    contactFoods = ContactFood._createContactsList(20);
                    seafoodsAdapter = new FoodAdapter(contactFoods);
                }
                recyclerSeafoods.addItemDecoration(leftDecoration);
                recyclerSeafoods.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerSeafoods.setAdapter(seafoodsAdapter);
            }

            @Override
            public void onFailure(Call<List<SeasonalFoodDto>> call, Throwable t) {
                contactFoods = ContactFood._createContactsList(20);
                seafoodsAdapter = new FoodAdapter(contactFoods);
                recyclerSeafoods.addItemDecoration(leftDecoration);
                recyclerSeafoods.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerSeafoods.setAdapter(seafoodsAdapter);
            }
        });
    }

    private void loadAnnounces(HomeFragment homeFragment) {
        boardService.getNewses(10000).enqueue(new Callback<List<GetPostListDto>>() {
            @Override
            public void onResponse(Call<List<GetPostListDto>> call,
                                   Response<List<GetPostListDto>> response) {
                if (response.code() == 201) {
                    List<GetPostListDto> body = response.body();
                    contactCommunityValue = CommunityValue.createContactsList(body);
                    announceAdapter = new AnnounceAdapter(Glide.with(getActivity()), contactCommunityValue);
                } else {
                    contactCommunityValue = CommunityValue.createNewsContactsList();
                    announceAdapter = new AnnounceAdapter(Glide.with(getActivity()), contactCommunityValue);
                }
                recyclerAnnounce.addItemDecoration(leftDecoration);
                recyclerAnnounce.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerAnnounce.setAdapter(announceAdapter);
            }

            @Override
            public void onFailure(Call<List<GetPostListDto>> call, Throwable t) {
                contactCommunityValue = CommunityValue.createNewsContactsList();
                announceAdapter = new AnnounceAdapter(Glide.with(getActivity()), contactCommunityValue);
                recyclerAnnounce.addItemDecoration(leftDecoration);
                recyclerAnnounce.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerAnnounce.setAdapter(announceAdapter);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transaction = fragmentManager.beginTransaction();

        view.findViewById(R.id.iv_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(homeFragment.newInstance());
                fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // 백스택 모두 지우기
            }
        });

        view.findViewById(R.id.btn_fruit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceHomeFragment(fruitFragment.newInstance("fruits"));
            }
        });

        view.findViewById(R.id.btn_vegetable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceHomeFragment(vegetableFragment.newInstance("vegetables"));
            }
        });

        view.findViewById(R.id.btn_fish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceHomeFragment(fishFragment.newInstance("seafoods"));
            }
        });
    }

    public void closeKeyBoard(View view) {
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}