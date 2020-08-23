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

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.ShopOCAdapter;
import com.example.saojeong.model.ContactShopOC;
import com.example.saojeong.model.OnItemClickListener;
import com.example.saojeong.model.RecyclerDecoration;

import java.util.ArrayList;
import java.util.List;

public class VegetableFragment extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private ShopFragment shopFragment;
    private RecyclerView recyclerShopoc;
    private RecyclerView recyclerShopclose;
    private ShopOCAdapter shopOCAdapter;
    List<ContactShopOC> contactShopOCs;

    TextView selectedText;
    Spinner spinner_shop;
    String[] item_shop;

    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(50);

    public static VegetableFragment newInstance() {
        return new VegetableFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_vegetable, container, false);

        ((MainActivity) getActivity()).closeKeyBoard(rootView);

        //순서 나열 Spinner
        spinner_shop = (Spinner) rootView.findViewById(R.id.spinner_vegetable);
        item_shop = new String[]{"평점 높은 순", "평점 많은 순", "이름 순"};
        ArrayAdapter<String> adapter_shopoc = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, item_shop);
        adapter_shopoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_shop.setAdapter(adapter_shopoc);
        spinner_shop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //채소동 오픈 가게 Recycler View
        recyclerShopoc = (RecyclerView) rootView.findViewById(R.id.recyclershop_open);
        setAdapter();

        //채소동 휴식 가게 Recycler View
        recyclerShopoc = (RecyclerView) rootView.findViewById(R.id.recyclershop_close);
        setAdapter();

        return rootView;

    }

    private void setAdapter() {
        contactShopOCs = ContactShopOC._createContactsList(5);
        shopOCAdapter = new ShopOCAdapter(contactShopOCs);
        recyclerShopoc.addItemDecoration(bottomDecoration);
        recyclerShopoc.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        shopOCAdapter.setOnItemClicklistener(new OnItemClickListener() {
            @Override
            public void onItemClick(Object holder, View view, int position) {
                ((MainActivity) getActivity()).replaceFragment(shopFragment.newInstance());
            }
        });
        recyclerShopoc.setAdapter(shopOCAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transaction = fragmentManager.beginTransaction();

        shopOCAdapter.setOnItemClicklistener(new OnItemClickListener() {
            @Override
            public void onItemClick(Object holder, View view, int position) {
                ((MainActivity) getActivity()).replaceFragment(shopFragment.newInstance());
            }
        });
    }

}