package com.example.saojeong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.saojeong.R;
import com.example.saojeong.adapter.CommunityAdapter_item;
import com.example.saojeong.model.CommunityValue;
import com.example.saojeong.model.Community_CommentValue;

import java.util.ArrayList;
import java.util.List;

public class Community_User_Fragment extends Fragment {

    CommunityAdapter_item mAdapter;
    ArrayList<CommunityValue> mCommunityValue;
    TextView btnLeft;
    TextView btnRight;
    TextView tvBoard;

    public static SwipeRefreshLayout swipe;
    public static NestedScrollView scroll;
    int board=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.viewpaper_community, container, false); //0,2,외 이탭
        final RecyclerView mRecyclerViewCommunity = view.findViewById(R.id.community_board);
        mCommunityValue= new ArrayList<>();
        btnLeft=view.findViewById(R.id.tv_community_btn_Left);
        btnRight=view.findViewById(R.id.tv_community_btn_Right);
        scroll=view.findViewById(R.id.scrollns_community);
        tvBoard=view.findViewById(R.id.tv_community_board);
        swipe=view.findViewById(R.id.swipeRefresh);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(false);
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.DownBoard();
                board--;
                tvBoard.setText(board+1+"");
                scroll.scrollTo(0,0);
                mRecyclerViewCommunity.setAdapter(mAdapter);
                mRecyclerViewCommunity.setLayoutManager(new LinearLayoutManager(getActivity()));
                if(board==0)
                {
                    btnLeft.setVisibility(View.GONE);
                }
                else
                    btnLeft.setVisibility(View.VISIBLE);

                if((board+1)*10>=mCommunityValue.size())
                {
                    btnRight.setVisibility(View.GONE);
                }
                else
                    btnRight.setVisibility(View.VISIBLE);
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.UpBoard();
                board++;
                tvBoard.setText(board+1+"");
                scroll.scrollTo(0,0);
                mRecyclerViewCommunity.setAdapter(mAdapter);
                mRecyclerViewCommunity.setLayoutManager(new LinearLayoutManager(getActivity()));
                if(board==0)
                {
                    btnLeft.setVisibility(View.GONE);
                }
                else
                    btnLeft.setVisibility(View.VISIBLE);

                if((board+1)*10>=mCommunityValue.size())
                {
                    btnRight.setVisibility(View.GONE);
                }
                else
                    btnRight.setVisibility(View.VISIBLE);
            }
        });


        tvBoard.setText(board+1+"");

        //test
        List<Community_CommentValue> CCList;
        CCList = new ArrayList<>();
        CCList.add(new Community_CommentValue("a","a","a",false));
        CCList.add(new Community_CommentValue("b","b","b",false));
        CCList.add(new Community_CommentValue("c","c","c",false));
        CCList.add(new Community_CommentValue("d","d","d",false));

        CommunityValue com=new CommunityValue("1","1","1","1","1",0,0, true);
        CommunityValue com1=new CommunityValue("1","1","1","1","1",0,0, true);
        com.SetComment(CCList);
        com1.SetComment(CCList);
        mCommunityValue.add(com1);
        mCommunityValue.add(new CommunityValue("8","1","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","2","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","3","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","4","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","5","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","6","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","7","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","8","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","9","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","11011","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","1","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","2","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","3","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","4","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","5","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","6","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","7","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","8","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","9","2","2","2",0,0, true));
        mCommunityValue.add(new CommunityValue("8","1110","2","2","2",0,0, true));
        mCommunityValue.add(com);


        if(board==0)
        {
            btnLeft.setVisibility(View.GONE);
        }
        else
            btnLeft.setVisibility(View.VISIBLE);

        if((board+1)*10>=mCommunityValue.size())
        {
            btnRight.setVisibility(View.GONE);
        }
        else
            btnRight.setVisibility(View.VISIBLE);
        mAdapter = new CommunityAdapter_item(mCommunityValue, 0);
        mRecyclerViewCommunity.setAdapter(mAdapter);
        mRecyclerViewCommunity.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return view;
    }
}
