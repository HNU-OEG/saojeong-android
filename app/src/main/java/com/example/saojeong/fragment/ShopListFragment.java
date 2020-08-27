package com.example.saojeong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
import com.example.saojeong.model.RecyclerDecoration;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.TypeStoreDto;
import com.example.saojeong.rest.dto.store.StoreDto;
import com.example.saojeong.rest.service.StoreService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopListFragment extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private ShopFragment shopFragment;
    private RecyclerView recyclerClosedShop;
    private RecyclerView recyclerOpenedShop;
    private ShopOCAdapter shopOpenedAdapter;
    private ShopOCAdapter shopClosedAdapter;
    List<ContactShopOC> contactShopOCs;

    private StoreService storeService;

    Spinner spinner_shop;
    String[] item_shop;

    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(50);

    public static ShopListFragment newInstance() {
        return new ShopListFragment();
    }

    public static ShopListFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);

        ShopListFragment fragment = new ShopListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        assert getArguments() != null;
        String type = getArguments().getString("type");

        storeService = ServiceGenerator.createService(StoreService.class, TokenCase.getToken());


        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_fruit, container, false);

        Glide.with(this)
                .load("https://saojeong-images.s3.ap-northeast-2.amazonaws.com/4_01.png")
                .into((ImageView) rootView.findViewById(R.id.iv_fruitmap));

        ImageView typeImage = rootView.findViewById(R.id.iv_store_type);
        TextView typeText = rootView.findViewById(R.id.tv_store_type);

        switch (type) {
            case "vegetables":
                typeText.setText("채소");
                typeImage.setImageResource(R.drawable.icon_vegetable_list);
                break;
            case "fruits":
                typeText.setText("과일");
                typeImage.setImageResource(R.drawable.icon_fruit_list);
                break;
            default:
                typeText.setText("수산");
                typeImage.setImageResource(R.drawable.icon_fish_list);
                break;
        }

        typeImage.setImageResource(R.drawable.icon_fish_list);



        ((MainActivity) getActivity()).closeKeyBoard(rootView);

        //순서 나열 Spinner
        spinner_shop = (Spinner) rootView.findViewById(R.id.spinner_fruit);
        item_shop = new String[]{"평점 높은 순", "평점 많은 순", "이름 순"};
        ArrayAdapter<String> adapter_shopoc = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, item_shop);
        adapter_shopoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_shop.setAdapter(adapter_shopoc);

        //과일동 오픈 가게 Recycler View
        recyclerOpenedShop = (RecyclerView) rootView.findViewById(R.id.recyclershop_open);
        //과일동 휴식 가게 Recycler View
        recyclerClosedShop = (RecyclerView) rootView.findViewById(R.id.recyclershop_close);

        storeService.getTypeStore(type, "count").enqueue(new Callback<TypeStoreDto>() {
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
            ShopFragment targetFragment = shopFragment.newInstance(holder.storeId);
            MainActivity activity = (MainActivity) getActivity();
            activity.replaceFragment(targetFragment);
        });
        recyclerOpenedShop.setAdapter(shopOpenedAdapter);

        recyclerClosedShop.addItemDecoration(bottomDecoration);
        recyclerClosedShop.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        shopClosedAdapter.setOnItemClickListener(holder -> {
            ShopFragment targetFragment = shopFragment.newInstance(holder.storeId);
            MainActivity activity = (MainActivity) getActivity();
            activity.replaceFragment(targetFragment);
        });
        recyclerClosedShop.setAdapter(shopClosedAdapter);
    }
}
