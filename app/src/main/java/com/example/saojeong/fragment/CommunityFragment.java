package com.example.saojeong.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class CommunityFragment extends Fragment implements View.OnClickListener, View.OnKeyListener{

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
        mFreeboard.setOnClickListener(this);
        mNotice=view.findViewById(R.id.tv_community_btn_notice);
        mNotice.setOnClickListener(this);
        mWrite=view.findViewById(R.id.tv_community_btn_write);
        mWrite.setOnClickListener(this);
        mBoardSearch=view.findViewById(R.id.et_community_boardsearch);
        mBoardSearch.setOnKeyListener(this);
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
        viewPager2.setUserInputEnabled(false);
        Toolbar toolbar = view.findViewById(R.id.toolbar_community);
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
        return view;
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch(id)
        {
            case R.id.tv_community_btn_freeboard:
                mFreeboard.setTextColor(Color.parseColor("#fa8f68"));
                mNotice.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.tv_community_btn_notice:
                mFreeboard.setTextColor(Color.parseColor("#000000"));
                mNotice.setTextColor(Color.parseColor("#fa8f68"));
                break;
            case R.id.tv_community_btn_write:
                Intent intent = new Intent((MainActivity)getActivity(), CommunityWirteActivity.class);

                startActivity(intent);
                break;
            case R.id.ll_community_left:
                tabLayout.setScrollPosition(0,0,true);
                viewPager2.setCurrentItem(0);

                break;
            case R.id.ll_community_right:
                tabLayout.setScrollPosition(1,0,true);
                viewPager2.setCurrentItem(1);
                break;
            case R.id.ll_community_home:
                break;
            case R.id.ll_community_re:
                break;
            case R.id.ll_community_upscroll:
                switch(viewPager2.getCurrentItem()) {
                    case 0:
                        if (CommunityTabFragment.scroll != null) {

                            CommunityTabFragment.scroll.scrollTo(0, 0);
                        }
                        break;
                    case 1:
                        if (Community_Popularity_Fragment.scroll != null) {
                            Community_Popularity_Fragment.scroll.scrollTo(0, 0);
                        }
                        break;
                    case 2:
                        if (Community_User_Fragment.scroll != null) {
                            Community_User_Fragment.scroll.scrollTo(0, 0);
                        }
                        break;
                }
                break;
        }

    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
            return true;
        }
        return false;
    }
}