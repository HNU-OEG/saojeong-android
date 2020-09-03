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
import com.example.saojeong.adapter.CommunityAdapter;
import com.example.saojeong.model.ChartContact;
import com.example.saojeong.model.CommunityValue;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommunityFragment_Freeboard extends Fragment implements View.OnClickListener, View.OnKeyListener  {

    CommunityAdapter mAdapter;
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    static CommunityFragment_Freeboard inst;
    TextView mWrite;
    EditText mBoardSearch;

    LinearLayout mBottomLeft;
    LinearLayout mBottomRight;
    LinearLayout mBottomHome;
    LinearLayout mBottomRe;
    LinearLayout mBottomUpScroll;
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
        mBoardSearch.setOnKeyListener(this);

        mBoardSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
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

        switch(id) {
            case R.id.tv_community_btn_write:
                Community_WriteFragment fragment1=new Community_WriteFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_main, fragment1) // frameLayout에 커뮤니티 Fragment 호출
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                break;

        }

    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
            if(viewPager2.getCurrentItem()==0)
            {
                if(mBoardSearch.getText().toString()!="")
                  CommunityTabFragment.inst.mAdapter.getFilter().filter(mBoardSearch.getText().toString());
            }

            if(viewPager2.getCurrentItem()==1)
            {

            }

            if(viewPager2.getCurrentItem()==2)
            {

            }
            return true;
        }
        return false;
    }


}