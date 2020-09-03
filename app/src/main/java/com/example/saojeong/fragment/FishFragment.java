package com.example.saojeong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
import com.example.saojeong.adapter.ShopOCAdapter;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.model.ContactShopOC;
import com.example.saojeong.model.OnItemClickListener;
import com.example.saojeong.model.RecyclerDecoration;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.TypeStoreDto;
import com.example.saojeong.rest.dto.store.StoreDto;
import com.example.saojeong.rest.service.StoreService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FishFragment extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private ShopFragment shopFragment;
    private RecyclerView recyclerClosedShop;
    private RecyclerView recyclerOpenedShop;
    private ShopOCAdapter shopOpenedAdapter;
    private ShopOCAdapter shopClosedAdapter;
    List<ContactShopOC> contactShopOCs;

    private StoreService storeService;

    TextView selectedText;
    Spinner spinner_shop;
    String[] item_shop;

    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(50);

    public static FishFragment newInstance() {
        return new FishFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        storeService = ServiceGenerator.createService(StoreService.class, TokenCase.getToken());

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_fish, container, false);

        ((MainActivity) getActivity()).closeKeyBoard(rootView);

        //순서 나열 Spinner
        spinner_shop = rootView.findViewById(R.id.spinner_fish);
        item_shop = new String[]{"평점 높은 순", "평점 많은 순", "이름 순"};
        ArrayAdapter<String> adapter_shopoc = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, item_shop);
        adapter_shopoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_shop.setAdapter(adapter_shopoc);

        //과일동 오픈 가게 Recycler View
        recyclerOpenedShop = rootView.findViewById(R.id.recyclershop_open);
        //과일동 휴식 가게 Recycler View
        recyclerClosedShop = rootView.findViewById(R.id.recyclershop_close);

        storeService.getTypeStore("seafoods", "count").enqueue(new Callback<TypeStoreDto>() {
            @Override
            public void onResponse(Call<TypeStoreDto> call, Response<TypeStoreDto> response) {
                if (response.code() == 201) {
                    TypeStoreDto body = response.body();
                    List<StoreDto> openedStore = body.getOpenStore();
                    List<StoreDto> closedStore = body.getClosedStore();

                    contactShopOCs = ContactShopOC.createContactsList(openedStore);
                    shopOpenedAdapter = new ShopOCAdapter(Glide.with(getActivity()), contactShopOCs);

                    contactShopOCs = ContactShopOC.createContactsList(closedStore);
                    shopClosedAdapter = new ShopOCAdapter(Glide.with(getActivity()), contactShopOCs);
                } else {
                    getDefaultAdapter();
                }
                setAdapter();
            }

            @Override
            public void onFailure(Call<TypeStoreDto> call, Throwable t) {
                getDefaultAdapter();
                setAdapter();
            }
        });
        
        return rootView;

    }

    private void getDefaultAdapter() {
        contactShopOCs = ContactShopOC._createContactsList(10);
        shopOpenedAdapter = new ShopOCAdapter(Glide.with(getActivity()), contactShopOCs);
        shopClosedAdapter = new ShopOCAdapter(Glide.with(getActivity()), contactShopOCs);
    }

    private void setAdapter() {
        recyclerOpenedShop.addItemDecoration(bottomDecoration);
        recyclerOpenedShop.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        shopOpenedAdapter.setOnItemClickListener(holder -> {
            ShopFragment targetFragment = ShopFragment.newInstance(holder.storeId);
            MainActivity activity = (MainActivity) getActivity();
            activity.replaceFragment(targetFragment);
        });
        recyclerOpenedShop.setAdapter(shopOpenedAdapter);

        recyclerClosedShop.addItemDecoration(bottomDecoration);
        recyclerClosedShop.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        shopClosedAdapter.setOnItemClickListener(holder -> {
            ShopFragment targetFragment = ShopFragment.newInstance(holder.storeId);
            MainActivity activity = (MainActivity) getActivity();
            activity.replaceFragment(targetFragment);
        });
        recyclerClosedShop.setAdapter(shopClosedAdapter);
    }

//    private void setAdapter() {
//        contactShopOCs = ContactShopOC._createContactsList(5);
//        shopOCAdapter = new ShopOCAdapter(contactShopOCs);
//        recyclerShopoc.addItemDecoration(bottomDecoration);
//        recyclerShopoc.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
////        shopOCAdapter.setOnItemClicklistener(new OnItemClickListener() {
////            @Override
////            public void onItemClick(Object holder, View view, int position) {
//////                ((MainActivity) getActivity()).replaceFragment(shopFragment.newInstance());
////            }
////        });
//        recyclerShopoc.setAdapter(shopOCAdapter);
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transaction = fragmentManager.beginTransaction();

//        shopOCAdapter.setOnItemClicklistener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(Object holder, View view, int position) {
//                ((MainActivity) getActivity()).replaceFragment(shopFragment.newInstance());
//            }
//        });
    }
}