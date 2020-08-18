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

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.CommunityAdapter_item;
import com.example.saojeong.model.CommunityValue;
import com.example.saojeong.model.Community_CommentValue;

import java.util.ArrayList;
import java.util.List;


public class CommunityTabFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener{

    CommunityAdapter_item mAdapter;
    ArrayList<CommunityValue> mCommunityValue;
    TextView btnLeft;
    TextView btnRight;
    TextView tvBoard;
    public static SwipeRefreshLayout swipe;
    public static NestedScrollView scroll;
    RecyclerView mRecyclerViewCommunity;
    int board=0;

    //private Community_Service community_Service;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.viewpaper_community, container, false);
        mRecyclerViewCommunity = view.findViewById(R.id.community_recycler);
        mRecyclerViewCommunity.setNestedScrollingEnabled(false);
        mCommunityValue= new ArrayList<>();
        btnLeft=view.findViewById(R.id.tv_community_btn_Left);
        btnLeft.setOnClickListener(this);
        btnRight=view.findViewById(R.id.tv_community_btn_Right);
        btnRight.setOnClickListener(this);
        scroll=view.findViewById(R.id.scrollns_community);
        tvBoard=view.findViewById(R.id.tv_community_board);
        swipe=view.findViewById(R.id.swipeRefresh);
        swipe.setOnRefreshListener(this);
        tvBoard.setText(board+1+"");
        swipe.setClipToPadding(false);
        swipe.setPadding(0, 0, 0, 0);


       // community_Service = ServiceGenerator.createService(Community_Service.class, "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0ZWFtLk9qZW9uZ2RvbmcuRWNvbm9taWNzLkd1YXJkaWFucyIsImV4cCI6MTU5NzU4ODU3MSwibWVtYmVyX2lkIjoiMEJSNGkwTU92SnA5SzdNWlJCdWNsYWFpWjdFQiIsIm5pY2tuYW1lIjoi7J2166qF7J2YIOuRkOuNlOyngCIsInVzZXJ0eXBlIjoxfQ.G0SdapZG7h9Lr5kJf0P8ecl71DXiLFHicq6805RHDvY");



        //test
        List<Community_CommentValue> CCList;
        CCList = new ArrayList<>();
        CCList.add(new Community_CommentValue("a","a","a",false));
        CCList.add(new Community_CommentValue("b","b","b",false));
        CCList.add(new Community_CommentValue("c","c","c",false));
        CCList.add(new Community_CommentValue("d","d","d",false));
        CCList.add(new Community_CommentValue("d","d","d",false));
        CCList.add(new Community_CommentValue("d","d","d",false));
        CommunityValue com1=new CommunityValue("1","1","1","1",0,0, false);
        com1.SetComment(CCList);
        mCommunityValue.add(com1);
        for(int i=0; i<40; ++i)
        {
            CommunityValue com=new CommunityValue("제목은 두껍게! 한눈에 보이도록!","가나다라","07. 13 03:29","6",0,0, true);
            com.SetComment(CCList);
            mCommunityValue.add(com);
        }


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


        mAdapter = new CommunityAdapter_item(mCommunityValue, 0, (MainActivity)getActivity());
        mRecyclerViewCommunity.setAdapter(mAdapter);
        mRecyclerViewCommunity.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return view;
    }



    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch(id) {
            case R.id.tv_community_btn_Left:
                mAdapter.DownBoard();
                board--;
                tvBoard.setText(board+1+"");
                scroll.scrollTo(0,0);
                mRecyclerViewCommunity.setAdapter(mAdapter);
                mRecyclerViewCommunity.setLayoutManager(new LinearLayoutManager(getActivity()));
                if(board==0) {
                    btnLeft.setVisibility(View.GONE);
                }
                else
                    btnLeft.setVisibility(View.VISIBLE);

                if((board+1)*10>=mCommunityValue.size()) {
                    btnRight.setVisibility(View.GONE);
                }
                else
                    btnRight.setVisibility(View.VISIBLE);

                break;
            case R.id.tv_community_btn_Right:

                mAdapter.UpBoard();
                board++;
                tvBoard.setText(board+1+"");
                scroll.scrollTo(0,0);
                mRecyclerViewCommunity.setAdapter(mAdapter);
                mRecyclerViewCommunity.setLayoutManager(new LinearLayoutManager(getActivity()));
                if(board==0) {
                    btnLeft.setVisibility(View.GONE);
                }
                else
                    btnLeft.setVisibility(View.VISIBLE);

                if((board+1)*10>=mCommunityValue.size()) {
                    btnRight.setVisibility(View.GONE);
                }
                else
                    btnRight.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onRefresh() {
        mCommunityValue.clear();
        load_GetPost();
        board=0;
        swipe.setRefreshing(false);
        if(board==0) {
            btnLeft.setVisibility(View.GONE);
        }
        else
            btnLeft.setVisibility(View.VISIBLE);

        if((board+1)*10>=mCommunityValue.size()) {
            btnRight.setVisibility(View.GONE);
        }
        else
            btnRight.setVisibility(View.VISIBLE);
    }

    private void load_GetPost() {
     //  Log.d("LOADSTORES HERE", "HERE");

     //  community_Service.getPostList().enqueue(new Callback<List<CommunityDto>>() {
     //      @Override
     //      public void onResponse(Call<List<CommunityDto>> call,
     //                             Response<List<CommunityDto>> response) {
     //          if (response.isSuccessful()) {
     //              for (CommunityDto dto:response.body()) {
     //                  CommunityValue com=new CommunityValue("제목은 두껍게! 한눈에 보이도록!","가나다라","07. 13 03:29","6",0,0, true);

     //              }
     //          } else {
     //              Log.d("REST FAILED MESSAGE", response.message());
     //          }
     //      }

     //      @Override
     //      public void onFailure(Call<List<CommunityDto>> call, Throwable t) {
     //          Log.d("REST ERROR!", t.getMessage());
     //      }
     //  });
    }


}
