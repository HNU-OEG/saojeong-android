package com.example.saojeong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.adapter.ChartAdapter;
import com.example.saojeong.adapter.CommunityAdapter;
import com.example.saojeong.adapter.CommunityAdapter_item;
import com.example.saojeong.model.CommunityValue;

import java.util.ArrayList;
import java.util.List;

public class CommunityTabFragment extends Fragment {
    int num;
    CommunityAdapter_item mAdapter;
    ArrayList<CommunityValue> mCommunityValue;
    public CommunityTabFragment(int position)
    {
        num=position;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        if(num==1)
            view=inflater.inflate(R.layout.viewpaper_community, container, false); //1이면 이탭
        else
            view=inflater.inflate(R.layout.viewpaper_community, container, false); //0,2,외 이탭

        RecyclerView mRecyclerView = view.findViewById(R.id.community_board);
        mCommunityValue= new ArrayList<CommunityValue>();
        mCommunityValue.add(new CommunityValue());
        mCommunityValue.add(new CommunityValue());
        mCommunityValue.add(new CommunityValue());

        

        mAdapter = new CommunityAdapter_item(mCommunityValue);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return view;
    }
}
