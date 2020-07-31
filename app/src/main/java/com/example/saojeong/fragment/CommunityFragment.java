package com.example.saojeong.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.saojeong.CommunityWirteActivity;
import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.CommunityAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import static com.example.saojeong.fragment.CommunityTabFragment.swipe;

public class CommunityFragment extends Fragment {

    CommunityAdapter mAdapter;
    ViewPager2 viewPager2;
    TabLayout tabLayout;

    TextView mFreeboard;
    TextView mNotice;
    TextView mWrite;
    EditText mBoardSearch;

    LinearLayout mBottomLeft;
    LinearLayout mBottomRight;
    LinearLayout mBottomHome;
    LinearLayout mBottomRe;
    LinearLayout mBottomUpScroll;
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
        mBottomLeft=view.findViewById(R.id.ll_community_left);
        mBottomRight=view.findViewById(R.id.ll_community_right);
        mBottomHome=view.findViewById(R.id.ll_community_home);
        mBottomRe=view.findViewById(R.id.ll_community_re);
        mBottomUpScroll=view.findViewById(R.id.ll_community_upscroll);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("");
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.BLACK);
        final TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
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
                    case 2:{
                        tab.setText("내가쓴글");
                        break;
                    }
                }
            }

        });
        tabLayoutMediator.attach(); //붙임
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
                    return true;
                }
                return false;
            }
        });
        mBottomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabLayout.setScrollPosition(0,0,true);
            }
        });
        mBottomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabLayout.setScrollPosition(1,0,true);
            }
        });
        mBottomHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mBottomRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //직접접근후 새로고침
            }
        });
        mBottomUpScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CommunityTabFragment.scroll!=null)
                {
                    CommunityTabFragment.scroll.scrollTo(0,0);
                }
                if(Community_Popularity_Fragment.scroll!=null)
                {
                    Community_Popularity_Fragment.scroll.scrollTo(0,0);
                }
                if(Community_User_Fragment.scroll!=null)
                {
                    Community_User_Fragment.scroll.scrollTo(0,0);
                }
            }
        });


        return view;
    }
}