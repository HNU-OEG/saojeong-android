package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.saojeong.R;
import com.example.saojeong.adapter.ContactsAdapter;
import com.example.saojeong.adapter.FishAdapter;
import com.example.saojeong.adapter.FruitAdapter;
import com.example.saojeong.adapter.FullviewAdapter;
import com.example.saojeong.adapter.VegetableAdapter;
import com.example.saojeong.model.Contact;
import com.example.saojeong.model.ContactFish;
import com.example.saojeong.model.ContactFruit;
import com.example.saojeong.model.ContactFullview;
import com.example.saojeong.model.ContactVegetable;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerShop;
    private RecyclerView recyclerFruit;
    private RecyclerView recyclerVegetable;
    private RecyclerView recyclerFish;
    private RecyclerView recyclerFullview;
    private ContactsAdapter shopAdapter;
    private FruitAdapter fruitAdapter;
    private VegetableAdapter vegetableAdapter;
    private FishAdapter fishAdapter;
    private FullviewAdapter fullviewAdapter;
    ArrayList<Contact> contacts;
    ArrayList<ContactFruit> contactFruits;
    ArrayList<ContactVegetable> contactVegetables;
    ArrayList<ContactFish> contactFishs;
    ArrayList<ContactFullview> contactFullviews;

    TabHost tabHost;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_fragment, container, false);
        //매장 Recycler View
        recyclerShop = (RecyclerView) rootView.findViewById(R.id.recyclershop_fragment);
        contacts = Contact.createContactsList(20);
        shopAdapter = new ContactsAdapter(contacts);
        recyclerShop.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
        recyclerShop.setAdapter(shopAdapter);

        //과일 Recycler View
        recyclerFruit = (RecyclerView) rootView.findViewById(R.id.recyclerfruit_fragment);
        contactFruits = ContactFruit.createContactsList(20);
        fruitAdapter = new FruitAdapter(contactFruits);
        recyclerFruit.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
        recyclerFruit.setAdapter(fruitAdapter);

        //채소 Recycler View
        recyclerVegetable = (RecyclerView) rootView.findViewById(R.id.recyclervegetable_fragment);
        contactVegetables = ContactVegetable.createContactsList(20);
        vegetableAdapter = new VegetableAdapter(contactVegetables);
        recyclerVegetable.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
        recyclerVegetable.setAdapter(vegetableAdapter);

        //수산 Recycler View
        recyclerFish = (RecyclerView) rootView.findViewById(R.id.recyclerfish_fragment);
        contactFishs = ContactFish.createContactsList(20);
        fishAdapter = new FishAdapter(contactFishs);
        recyclerFish.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
        recyclerFish.setAdapter(fishAdapter);

        //전체 보기(공지) Recycler View
        recyclerFullview = (RecyclerView) rootView.findViewById(R.id.recyclerfullview_fragment);
        contactFullviews = ContactFullview.createContactsList(20);
        fullviewAdapter = new FullviewAdapter(contactFullviews);
        recyclerFullview.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
        recyclerFullview.setAdapter(fullviewAdapter);

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
}