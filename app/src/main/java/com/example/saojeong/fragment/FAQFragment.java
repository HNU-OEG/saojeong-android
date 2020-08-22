package com.example.saojeong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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

        recycler_FAQ = view.findViewById(R.id.recycler_FAQ);
        mFAQadapter = new FAQadapter(mFAQ);

        recycler_FAQ.setAdapter(mFAQadapter);
        // TODO: 2020-08-22-022 어댑터 사용 

        return view;

    }
}
