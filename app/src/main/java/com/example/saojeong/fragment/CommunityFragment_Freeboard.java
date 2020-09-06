package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.SingletonClass;
import com.example.saojeong.adapter.CommunityAdapter;
import com.example.saojeong.model.ChartContact;
import com.example.saojeong.model.CommunityValue;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommunityFragment_Freeboard extends Fragment implements View.OnClickListener  {

    CommunityAdapter mAdapter;
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    static CommunityFragment_Freeboard inst;
    TextView mWrite;
    EditText mBoardSearch;

    public CommunityFragment_Freeboard()
    {
        inst=this;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = (ViewGroup) inflater.inflate(R.layout.item_community_fragment, container, false);
        mAdapter=new CommunityAdapter(getActivity());
        viewPager2=view.findViewById(R.id.viewPager);
        viewPager2.setAdapter(mAdapter);


        tabLayout = view.findViewById(R.id.tablayout);
        mWrite=view.findViewById(R.id.tv_community_btn_write);
        mWrite.setOnClickListener(this);
        mBoardSearch=view.findViewById(R.id.et_community_boardsearch);

        mBoardSearch.setOnEditorActionListener((v, actionId, event) -> {
            switch (actionId) {
                case EditorInfo.IME_ACTION_SEARCH:
                    tabLayout.setScrollPosition(1,0,true);
                    viewPager2.setCurrentItem(1);
                    SingletonClass.getCommunitySearchInstance().ListUpdate(mBoardSearch.getText().toString());
                    break;
                default:
                    tabLayout.setScrollPosition(1,0,true);
                    viewPager2.setCurrentItem(1);
                    SingletonClass.getCommunitySearchInstance().ListUpdate(mBoardSearch.getText().toString());
                    break;
            }
            return true;
        });

        //밑줄
        SpannableString content = new SpannableString(mWrite.getText());
        content.setSpan(new UnderlineSpan(), 0, mWrite.getText().length(), 0);
        mWrite.setText(content);
        final TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) { //탭 목록
                switch(position){
                    case 0:{
                        tab.setText("최신");
                        break;
                    }
                    case 1:{
                        tab.setText("검색");
                        break;
                    }
                    case 2:{
                        tab.setText("내가쓴글");
                        break;
                    }
                }
            }

        });
        tabLayoutMediator.attach();
        return view;
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch(id) {
            case R.id.tv_community_btn_write:
                Community_WriteFragment fragment1=new Community_WriteFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_main, fragment1) // frameLayout에 커뮤니티 Fragment 호출
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                break;

        }

    }

}