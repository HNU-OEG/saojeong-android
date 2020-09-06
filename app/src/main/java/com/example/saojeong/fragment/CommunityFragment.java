package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.SingletonClass;
import com.example.saojeong.adapter.CommunityAdapter_fragment;

public class CommunityFragment extends Fragment implements View.OnClickListener{
    private CommunityAdapter_fragment mAdapter;
    private ViewPager2 viewPager2;
    private TextView mFreeboard;
    private TextView mNotice;
    private LinearLayout llBottombutton;
    private LinearLayout mBottomLeft;
    private LinearLayout mBottomRight;
    private LinearLayout mBottomHome;
    private LinearLayout mBottomRe;
    private LinearLayout mBottomUpScroll;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = (ViewGroup) inflater.inflate(R.layout.fragment_community, container, false);
        mAdapter = new CommunityAdapter_fragment(getActivity());
        viewPager2 = view.findViewById(R.id.viewpager);
        viewPager2.setAdapter(mAdapter);
        viewPager2.setUserInputEnabled(false);
        llBottombutton = view.findViewById(R.id.ll_community_bottom);
        mFreeboard = view.findViewById(R.id.tv_community_btn_freeboard);
        mFreeboard.setOnClickListener(this);
        mNotice = view.findViewById(R.id.tv_community_btn_notice);
        mNotice.setOnClickListener(this);
        mBottomLeft=view.findViewById(R.id.ll_community_left);
        mBottomLeft.setOnClickListener(this);
        mBottomRight=view.findViewById(R.id.ll_community_right);
        mBottomRight.setOnClickListener(this);
        mBottomHome=view.findViewById(R.id.ll_community_home);
        mBottomHome.setOnClickListener(this);
        mBottomRe=view.findViewById(R.id.ll_community_re);
        mBottomRe.setOnClickListener(this);
        mBottomUpScroll=view.findViewById(R.id.ll_community_upscroll);
        mBottomUpScroll.setOnClickListener(this);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("");
        toolbar.setTitleTextColor(Color.BLACK);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(viewPager2.getCurrentItem()==0) {
            mFreeboard.setTextColor(Color.parseColor("#fa8f68"));
            mNotice.setTextColor(Color.parseColor("#000000"));
        }
        else if(viewPager2.getCurrentItem()==1) {
            mFreeboard.setTextColor(Color.parseColor("#000000"));
            mNotice.setTextColor(Color.parseColor("#fa8f68"));
        }
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch(id) {
            case R.id.tv_community_btn_freeboard:
                mFreeboard.setTextColor(Color.parseColor("#fa8f68"));
                mNotice.setTextColor(Color.parseColor("#000000"));
                llBottombutton.setVisibility(View.VISIBLE);
                viewPager2.setCurrentItem(0);
                break;
            case R.id.tv_community_btn_notice:
                mFreeboard.setTextColor(Color.parseColor("#000000"));
                mNotice.setTextColor(Color.parseColor("#fa8f68"));
                viewPager2.setCurrentItem(1);
                llBottombutton.setVisibility(View.GONE);
                break;
            case R.id.ll_community_left:
                if( CommunityFragment_Freeboard.CommunityFragment_FreeboardInstance().getViewPager2().getCurrentItem()==0)
                    SingletonClass.getCommunityTabInstance().btn_Left();

                break;
            case R.id.ll_community_right:
                int a=viewPager2.getCurrentItem();
                if( CommunityFragment_Freeboard.CommunityFragment_FreeboardInstance().getViewPager2().getCurrentItem()==0)
                    SingletonClass.getCommunityTabInstance().btn_Right();
                break;
            case R.id.ll_community_home:
                CommunityFragment_Freeboard.CommunityFragment_FreeboardInstance().getTabLayout().setScrollPosition(0,0,true);
                break;
            case R.id.ll_community_re:
                SingletonClass.getCommunityTabInstance().load_GetPost();
                SingletonClass.getCommunityUserInstance().load_GetPost();
                break;
            case R.id.ll_community_upscroll:
                switch( CommunityFragment_Freeboard.CommunityFragment_FreeboardInstance().getViewPager2().getCurrentItem()) {
                    case 0:
                        if (SingletonClass.getCommunityTabInstance().getScroll() != null) {
                            SingletonClass.getCommunityTabInstance().getScroll().fullScroll(View.FOCUS_UP);
                        }
                        break;
                    case 1:
                        if (SingletonClass.getCommunitySearchInstance().getScroll() != null) {
                            SingletonClass.getCommunitySearchInstance().getScroll().fullScroll(View.FOCUS_UP);
                        }
                        break;
                    case 2:
                        if (SingletonClass.getCommunityUserInstance().getScroll() != null) {
                            SingletonClass.getCommunityUserInstance().getScroll().fullScroll(View.FOCUS_UP);
                        }
                        break;
                }
                break;
        }
    }
}