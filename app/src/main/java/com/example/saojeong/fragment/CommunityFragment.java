package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.CommunityAdapter;
import com.example.saojeong.adapter.CommunityAdapter_fragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

    public class CommunityFragment extends Fragment implements View.OnClickListener{

        CommunityAdapter_fragment mAdapter;
        ViewPager2 viewPager2;

        TextView mFreeboard;
        TextView mNotice;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            final View view = (ViewGroup) inflater.inflate(R.layout.fragment_community, container, false);
            mAdapter = new CommunityAdapter_fragment(getActivity());
            viewPager2 = view.findViewById(R.id.viewPager);
            viewPager2.setAdapter(mAdapter);

            mFreeboard = view.findViewById(R.id.tv_community_btn_freeboard);
            mFreeboard.setOnClickListener(this);
            mNotice = view.findViewById(R.id.tv_community_btn_notice);
            mNotice.setOnClickListener(this);


            Toolbar toolbar = view.findViewById(R.id.toolbar);
            ((MainActivity) getActivity()).setSupportActionBar(toolbar);
            ((MainActivity) getActivity()).getSupportActionBar().setTitle("");
            toolbar.setTitleTextColor(Color.BLACK);
            return view;
        }
        @Override
        public void onClick(View view) {
            int id=view.getId();

            switch(id) {
                case R.id.tv_community_btn_freeboard:
                    mFreeboard.setTextColor(Color.parseColor("#fa8f68"));
                    mNotice.setTextColor(Color.parseColor("#000000"));
                    viewPager2.setCurrentItem(0);
                    break;
                case R.id.tv_community_btn_notice:
                    mFreeboard.setTextColor(Color.parseColor("#000000"));
                    mNotice.setTextColor(Color.parseColor("#fa8f68"));
                    viewPager2.setCurrentItem(1);
                    break;

            }

        }


    }