package com.example.saojeong.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.IccOpenLogicalChannelResponse;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.saojeong.CommunityWirteActivity;
import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.CommunityAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CommunityFragment extends Fragment {

    CommunityAdapter mAdapter;
    ViewPager2 viewPager2;
    TabLayout tabLayout;

    TextView mFreeboard;
    TextView mNotice;
    TextView mWrite;
    EditText mBoardSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = (ViewGroup) inflater.inflate(R.layout.fragment_community, container, false);
        mAdapter=new CommunityAdapter(getActivity());
        viewPager2=view.findViewById(R.id.viewPager);
        viewPager2.setAdapter(mAdapter);
        tabLayout = view.findViewById(R.id.tablayout);
        mFreeboard=view.findViewById(R.id.tv_community_btn_freeboard);
        mNotice=view.findViewById(R.id.tv_community_btn_notice);
        mWrite=view.findViewById(R.id.tv_community_btn_write);
        mBoardSearch=view.findViewById(R.id.et_community_boardsearch);

        mFreeboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFreeboard.setTextColor(Color.parseColor("#fa8f68"));
                mNotice.setTextColor(Color.parseColor("#000000"));
            }
        });
        mNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFreeboard.setTextColor(Color.parseColor("#000000"));
                mNotice.setTextColor(Color.parseColor("#fa8f68"));
            }
        });
        mWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((MainActivity)getActivity(), CommunityWirteActivity.class);

                startActivity(intent);
            }
        });
        mBoardSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Enter key Action
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //Enter키눌렀을떄 처리
                    return true;
                }
                return false;
            }
        });

        //Tab사용시 필요
        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) { //탭 목록
                switch(position){
                    case 0:{
                        tab.setText("최신"); //
                        break;
                    }
                    case 1:{
                        tab.setText("인기");
                        break;
                    }
                }
            }
        });
        tabLayoutMediator.attach(); //붙임

        return view;
    }
}