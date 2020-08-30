package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.ChartAdapter;
import com.example.saojeong.adapter.CommunityAdapter_item;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.model.ChartContact;
import com.example.saojeong.model.CommunityValue;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.board.GetPostListArrayDto;
import com.example.saojeong.rest.dto.chart.chart_Dto;
import com.example.saojeong.rest.service.BoardService;
import com.example.saojeong.rest.service.chartService;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PriceFragment extends Fragment implements View.OnClickListener{

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
    private chartService chartService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_price, container, false);

        mFrult=view.findViewById(R.id.iv_price_btn_fruit_price);
        mFrult.setOnClickListener(this);
        mVegetable=view.findViewById(R.id.iv_price_btn_vegetable_price);
        mVegetable.setOnClickListener(this);
        mFish=view.findViewById(R.id.iv_price_btn_fish_price);
        mFish.setOnClickListener(this);
        mShopSearch=view.findViewById(R.id.et_price_shopsearch);
        mOneWeekend=view.findViewById(R.id.tv_oneweekend);
        mOneWeekend.setOnClickListener(this);
        mThreeWeekend=view.findViewById(R.id.tv_threeweekend);
        mThreeWeekend.setOnClickListener(this);
        mRecyclerView = view.findViewById(R.id.chartview);

        mShopSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                mAdapter.getFilter().filter( mShopSearch.getText().toString());
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        mChartContact=new ArrayList<>();
        ArrayList<Entry> list_ChartValue= new ArrayList<>();
        for(int i=0; i<4; ++i) {
            Entry entry1 = new Entry(i, 1000+i*10000);
            list_ChartValue.add(entry1);
        }
        mChartContact.add(new ChartContact("과일", list_ChartValue));
        mChartContact.add(new ChartContact("사과", list_ChartValue));
        mChartContact.add(new ChartContact("채소", list_ChartValue));
        mChartContact.add(new ChartContact("토마토", list_ChartValue));
        mChartContact.add(new ChartContact("파프리카", list_ChartValue));
        mChartContact.add(new ChartContact("바나나", list_ChartValue));
        mChartContact.add(new ChartContact("사과", list_ChartValue));
        mChartContact.add(new ChartContact("딸기", list_ChartValue));
        mChartContact.add(new ChartContact("포도", list_ChartValue));
        mChartContact.add(new ChartContact("수박", list_ChartValue));

        load_GetPost();
        mAdapter = new ChartAdapter(this.getContext(), mChartContact, 1);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        return view;
    }

    @Override
    public void onClick(View view) {
        ArrayList<Entry> list_ChartValue;
        int id=view.getId();
        switch(id) {
            case R.id.iv_price_btn_fruit_price:
                mFrult.setBackgroundResource(R.drawable.rounded_edittext_gray);
                mVegetable.setBackground(null);
                mFish.setBackground(null);
                break;
            case R.id.iv_price_btn_vegetable_price:
                mFrult.setBackground(null);
                mVegetable.setBackgroundResource(R.drawable.rounded_edittext_gray);
                mFish.setBackground(null);
                break;
            case R.id.iv_price_btn_fish_price:
                mFrult.setBackground(null);
                mVegetable.setBackground(null);
                mFish.setBackgroundResource(R.drawable.rounded_edittext_gray);
                break;
            case R.id.tv_oneweekend:
                mOneWeekend.setTextColor(Color.parseColor("#fa8f68"));
                mThreeWeekend.setTextColor(Color.parseColor("#000000"));
                mAdapter.SetWeekend(1);
                mAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mAdapter);
                break;
            case R.id.tv_threeweekend:

                mThreeWeekend.setTextColor(Color.parseColor("#fa8f68"));
                mOneWeekend.setTextColor(Color.parseColor("#000000"));
                mAdapter.SetWeekend(3);
                mAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mAdapter);
                break;
        }
    }

    public void load_GetPost() {
        chartService = ServiceGenerator.createService(chartService.class, TokenCase.getToken());
        chartService.getChartinfo("3140205").enqueue(new Callback<chart_Dto>() {
            @Override
            public void onResponse(Call<chart_Dto> call, Response<chart_Dto> response) {
                chart_Dto body = response.body();
                if (response.code() == 201) { // 서버와 통신 성공
                    chart_Dto a = response.body();
                }
            }

            @Override
            public void onFailure(Call<chart_Dto> call, Throwable t) {

            }
        });

    }
}



