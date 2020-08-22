package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;


import com.bumptech.glide.Glide;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.FishAdapter;
import com.example.saojeong.adapter.FruitAdapter;
import com.example.saojeong.adapter.FullviewAdapter;
import com.example.saojeong.adapter.LikeStoreAdapter;
import com.example.saojeong.adapter.VegetableAdapter;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.model.ContactFish;
import com.example.saojeong.model.ContactFruit;
import com.example.saojeong.model.ContactFullview;
import com.example.saojeong.model.ContactVegetable;
import com.example.saojeong.model.LikeStore;
import com.example.saojeong.model.RecyclerDecoration;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.SeasonalFoodDto;
import com.example.saojeong.rest.dto.StoreDto;
import com.example.saojeong.rest.dto.board.ContentDto;
import com.example.saojeong.rest.service.BoardService;
import com.example.saojeong.rest.service.SeasonalFoodService;
import com.example.saojeong.rest.service.StoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static android.content.Context.INPUT_METHOD_SERVICE;


public class HomeFragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private FruitFragment fruitFragment;
    private VegetableFragment vegetableFragment;
    private HomeFragment homeFragment;
    private FishFragment fishFragment;
    private RecyclerView recyclerShop;
    private RecyclerView recyclerFruit;
    private RecyclerView recyclerVegetable;
    private RecyclerView recyclerFish;
    private RecyclerView recyclerFullview;
    private LikeStoreAdapter likeStoreAdapter;
    private FruitAdapter fruitAdapter;
    private VegetableAdapter vegetableAdapter;
    private FishAdapter fishAdapter;
    private FullviewAdapter fullviewAdapter;
    List<LikeStore> likeStores;
    List<ContactFruit> contactFruits;
    List<ContactVegetable> contactVegetables;
    List<ContactFish> contactFishs;
    List<ContactFullview> contactFullviews;

    private StoreService storeService;
    private SeasonalFoodService foodService;
    private BoardService boardService;

    RecyclerDecoration.LeftDecoration leftDecoration = new RecyclerDecoration.LeftDecoration(50);

    private InputMethodManager imm;

    TabHost tabHost;

    private final String TAG = this.getClass().getName();

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        storeService = ServiceGenerator.createService(StoreService.class, TokenCase.getToken());
        foodService = ServiceGenerator.createService(SeasonalFoodService.class, TokenCase.getToken());
        boardService = ServiceGenerator.createService(BoardService.class, TokenCase.getToken());

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);

        fruitFragment = new FruitFragment(); // 과일동 Fragment 선언
        vegetableFragment = new VegetableFragment(); // 채소동 Fragment 선언
        fishFragment = new FishFragment(); // 수산동 Fragment 선언

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        //매장 Recycler View
        recyclerShop = (RecyclerView) rootView.findViewById(R.id.recyclershop_fragment);
        loadStores(this);

        //과일 Recycler View
        recyclerFruit = (RecyclerView) rootView.findViewById(R.id.recyclerfruit_fragment);
        //채소 Recycler View
        recyclerVegetable = (RecyclerView) rootView.findViewById(R.id.recyclervegetable_fragment);
        //수산 Recycler View
        recyclerFish = (RecyclerView) rootView.findViewById(R.id.recyclerfish_fragment);
        loadFoods(this);

        //전체 보기(공지) Recycler View
        recyclerFullview = (RecyclerView) rootView.findViewById(R.id.recyclerfullview_fragment);
        loadNews(this);

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
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    TextView tabcolor = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                    if (i == tabHost.getCurrentTab()) {
                        tabcolor.setTextColor(Color.parseColor("#ffffff"));
                    } else
                        tabcolor.setTextColor(Color.parseColor("#ffd6b7"));

                }
            }
        });
        return rootView;
    }

    private void loadStores(HomeFragment homeFragment) {
        storeService.getStoreListOrderByGrade().enqueue(new Callback<List<StoreDto>>() {
            @Override
            public void onResponse(Call<List<StoreDto>> call,
                                   Response<List<StoreDto>> response) {
                if (response.code() != 201) {
                    likeStores = LikeStore._createLikeStoreList(20);
                    likeStoreAdapter = new LikeStoreAdapter(likeStores);
                    Log.d(TAG, response.message());
                } else {
                    likeStores = LikeStore.createLikeStoreList(Objects.requireNonNull(response.body()));
                    likeStoreAdapter = new LikeStoreAdapter(Glide.with(homeFragment), likeStores);
                }

                recyclerShop.addItemDecoration(leftDecoration);
                recyclerShop.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerShop.setAdapter(likeStoreAdapter);
            }

            @Override
            public void onFailure(Call<List<StoreDto>> call, Throwable t) {
                likeStores = LikeStore._createLikeStoreList(20);
                likeStoreAdapter = new LikeStoreAdapter(likeStores);
                recyclerShop.addItemDecoration(leftDecoration);
                recyclerShop.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerShop.setAdapter(likeStoreAdapter);
                Log.d(TAG, t.getMessage());
            }
        });
    }

    private void loadFoods(HomeFragment homeFragment) {
        foodService.getSeasonalFood("과일", 9).enqueue(new Callback<List<SeasonalFoodDto>>() {
            @Override
            public void onResponse(Call<List<SeasonalFoodDto>> call, Response<List<SeasonalFoodDto>> response) {
                if (response.code() != 201) {
                    contactFruits = ContactFruit._createContactsList(20);
                    fruitAdapter = new FruitAdapter(contactFruits);
                    Log.d(TAG, response.message());
                } else {
                    contactFruits = ContactFruit.createContactsList(Objects.requireNonNull(response.body()));
                    fruitAdapter = new FruitAdapter(Glide.with(homeFragment), contactFruits);
                }
                recyclerFruit.addItemDecoration(leftDecoration);
                recyclerFruit.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerFruit.setAdapter(fruitAdapter);
            }

            @Override
            public void onFailure(Call<List<SeasonalFoodDto>> call, Throwable t) {
                contactFruits = ContactFruit._createContactsList(20);
                fruitAdapter = new FruitAdapter(contactFruits);
                recyclerFruit.addItemDecoration(leftDecoration);
                recyclerFruit.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerFruit.setAdapter(fruitAdapter);
                Log.d(TAG, t.getMessage());
            }
        });
        foodService.getSeasonalFood("채소", 9).enqueue(new Callback<List<SeasonalFoodDto>>() {
            @Override
            public void onResponse(Call<List<SeasonalFoodDto>> call, Response<List<SeasonalFoodDto>> response) {
                if (response.code() != 201) {
                    contactVegetables = ContactVegetable._createContactsList(20);
                    vegetableAdapter = new VegetableAdapter(contactVegetables);
                    Log.d(TAG, response.message());
                } else {
                    contactVegetables = ContactVegetable.createContactsList(Objects.requireNonNull(response.body()));
                    vegetableAdapter = new VegetableAdapter(Glide.with(homeFragment), contactVegetables);
                }
                recyclerVegetable.addItemDecoration(leftDecoration);
                recyclerVegetable.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerVegetable.setAdapter(vegetableAdapter);
            }

            @Override
            public void onFailure(Call<List<SeasonalFoodDto>> call, Throwable t) {
                contactVegetables = ContactVegetable._createContactsList(20);
                vegetableAdapter = new VegetableAdapter(contactVegetables);
                recyclerVegetable.addItemDecoration(leftDecoration);
                recyclerVegetable.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerVegetable.setAdapter(vegetableAdapter);
                Log.d(TAG, t.getMessage());
            }
        });
        foodService.getSeasonalFood("수산", 9).enqueue(new Callback<List<SeasonalFoodDto>>() {
            @Override
            public void onResponse(Call<List<SeasonalFoodDto>> call, Response<List<SeasonalFoodDto>> response) {
                if (response.code() != 201) {
                    contactFishs = ContactFish._createContactsList(20);
                    fishAdapter = new FishAdapter(contactFishs);
                    Log.d(TAG, response.message());
                } else {
                    contactFishs = ContactFish.createContactsList(Objects.requireNonNull(response.body()));
                    fishAdapter = new FishAdapter(Glide.with(homeFragment), contactFishs);
                }

                recyclerFish.addItemDecoration(leftDecoration);
                recyclerFish.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerFish.setAdapter(fishAdapter);
            }

            @Override
            public void onFailure(Call<List<SeasonalFoodDto>> call, Throwable t) {
                contactFishs = ContactFish._createContactsList(20);
                fishAdapter = new FishAdapter(contactFishs);
                recyclerFish.addItemDecoration(leftDecoration);
                recyclerFish.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerFish.setAdapter(fishAdapter);
                Log.d(TAG, t.getMessage());
            }
        });
    }

    private void loadNews(HomeFragment homeFragment) {
        boardService.getBoardList(10000).enqueue(new Callback<List<ContentDto>>() {
            @Override
            public void onResponse(Call<List<ContentDto>> call, Response<List<ContentDto>> response) {
                if (response.code() != 201) {
                    contactFullviews = ContactFullview._createContactsList(20);
                    fullviewAdapter = new FullviewAdapter(contactFullviews);
                } else {
                    contactFullviews = ContactFullview.createContactsList(Objects.requireNonNull(response.body()));
                    fullviewAdapter = new FullviewAdapter(Glide.with(homeFragment), contactFullviews);
                }
                recyclerFullview.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerFullview.setAdapter(fullviewAdapter);
            }

            @Override
            public void onFailure(Call<List<ContentDto>> call, Throwable t) {
                contactFullviews = ContactFullview._createContactsList(20);
                fullviewAdapter = new FullviewAdapter(contactFullviews);
                recyclerFullview.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerFullview.setAdapter(fullviewAdapter);
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
                ((MainActivity) getActivity()).replaceHomeFragment(fruitFragment.newInstance());
            }
        });

        view.findViewById(R.id.btn_vegetable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceHomeFragment(VegetableFragment.newInstance());
            }
        });

        view.findViewById(R.id.btn_fish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceHomeFragment(FishFragment.newInstance());
            }
        });
    }

    public void closeKeyBoard(View view) {
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}