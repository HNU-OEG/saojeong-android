package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.FAQadapter;
import com.example.saojeong.model.FAQ;

import java.util.ArrayList;

public class FAQFragment extends Fragment {

    private RecyclerView recycler_FAQ;
    private FAQadapter mFAQadapter;

    ArrayList<FAQ> mFAQ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_faq, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_faq);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.~~); // 뒤로가기 화살표 이미지 바꾸기
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("");
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.BLACK);

        recycler_FAQ = view.findViewById(R.id.recycler_FAQ);
        mFAQ = FAQ.createFAQ(20);
        mFAQadapter = new FAQadapter(mFAQ);
        recycler_FAQ.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler_FAQ.addItemDecoration(new DividerItemDecoration(view.getContext(), 1));
        recycler_FAQ.setAdapter(mFAQadapter);

        return view;

    }
}
