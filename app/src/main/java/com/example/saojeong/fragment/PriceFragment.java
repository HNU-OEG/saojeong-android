package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
    TextView mOneWeekend;
    TextView mThreeWeekend;
    RecyclerView mRecyclerView;
    TextView mFrultBackground;
    TextView mVegetableBackground;
    TextView mFishBackground;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_price, container, false);

        mFrult=view.findViewById(R.id.iv_price_btn_fruit_price);
        mVegetable=view.findViewById(R.id.iv_price_btn_vegetable_price);
        mFish=view.findViewById(R.id.iv_price_btn_fish_price);
        mShopSearch=view.findViewById(R.id.et_price_shopsearch);
        mFish=view.findViewById(R.id.iv_price_btn_fish_price);
        mShopSearch=view.findViewById(R.id.et_price_shopsearch);
        mOneWeekend=view.findViewById(R.id.tv_oneweekend);
        mThreeWeekend=view.findViewById(R.id.tv_threeweekend);
        mRecyclerView = view.findViewById(R.id.chartview);


        mFrult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFrult.setBackgroundResource(R.drawable.rounded_edittext_gray);
                mVegetable.setBackground(null);
                mFish.setBackground(null);
            }
        });

        mVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFrult.setBackground(null);
                mVegetable.setBackgroundResource(R.drawable.rounded_edittext_gray);
                mFish.setBackground(null);
            }
        });

        mFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFrult.setBackground(null);
                mVegetable.setBackground(null);
                mFish.setBackgroundResource(R.drawable.rounded_edittext_gray);

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

        mOneWeekend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOneWeekend.setTextColor(Color.parseColor("#fa8f68"));
                mThreeWeekend.setTextColor(Color.parseColor("#000000"));
                mChartContact=new ArrayList<>();
                ArrayList<Entry> list_ChartValue= new ArrayList<>();
                for(int i=0; i<30; ++i)
                {
                    Entry entry1 = new Entry(i, 1000+i*20);
                    list_ChartValue.add(entry1);
                }

                for(int i=0; i<30; ++i)
                {
                    mChartContact.add(new ChartContact(i+"100", list_ChartValue));
                }

                mAdapter = new ChartAdapter(getContext(), mChartContact, 1);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
        mThreeWeekend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mThreeWeekend.setTextColor(Color.parseColor("#fa8f68"));
                mOneWeekend.setTextColor(Color.parseColor("#000000"));
                    mChartContact=new ArrayList<>();
                    ArrayList<Entry> list_ChartValue= new ArrayList<>();
                    for(int i=0; i<30; ++i)
                    {
                        Entry entry1 = new Entry(i, 1000+i*20);
                        list_ChartValue.add(entry1);
                    }

                    for(int i=0; i<30; ++i)
                    {
                        mChartContact.add(new ChartContact(i+"100", list_ChartValue));
                    }

                    mAdapter = new ChartAdapter(getContext(), mChartContact, 3);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }
        });


        mChartContact=new ArrayList<>();
        ArrayList<Entry> list_ChartValue= new ArrayList<>();
        for(int i=0; i<30; ++i)
        {
            Entry entry1 = new Entry(i, 1000+i*20);
            list_ChartValue.add(entry1);
        }

        for(int i=0; i<30; ++i)
        {
            mChartContact.add(new ChartContact(i+"100", list_ChartValue));
        }

        mAdapter = new ChartAdapter(this.getContext(), mChartContact, 1);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        return view;
    }

    public void Search(String str)
    {

    }
}



