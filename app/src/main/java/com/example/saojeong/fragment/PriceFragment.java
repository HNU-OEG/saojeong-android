package com.example.saojeong.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.saojeong.R;
import com.example.saojeong.adapter.ChartAdapter;
import com.example.saojeong.model.ChartContact;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class PriceFragment extends Fragment {

    private List<ChartContact> mChartContact;
    ChartAdapter mAdapter;


    ImageView mFrult;
    ImageView mVegetable;
    ImageView mFish;
    EditText mShopSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_price, container, false);

        mFrult=view.findViewById(R.id.iv_price_btn_fruit_price);
        mVegetable=view.findViewById(R.id.iv_price_btn_vegetable_price);
        mFish=view.findViewById(R.id.iv_price_btn_fish_price);
        mShopSearch=view.findViewById(R.id.et_price_shopsearch);


        mFrult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=0; i<4; ++i)
                {
                    ArrayList<Entry> list_ChartValue1= new ArrayList<>();
                    Entry entry1 = new Entry(i, 1000+i*20);
                    list_ChartValue1.add(entry1);
                }
            }
        });

        mVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mShopSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String text = mShopSearch.getText().toString();

            }
        });



        RecyclerView mRecyclerView = view.findViewById(R.id.chartview);
        mChartContact=new ArrayList<>();
        ArrayList<Entry> list_ChartValue= new ArrayList<>();
        for(int i=0; i<4; ++i)
        {
            Entry entry1 = new Entry(i, 1000+i*20);
            list_ChartValue.add(entry1);
        }

        for(int i=0; i<4; ++i)
        {
            mChartContact.add(new ChartContact(i+"100", list_ChartValue));
        }

        mAdapter = new ChartAdapter(this.getContext(), mChartContact);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        return view;
    }

    public void Search(String str)
    {

    }
}



