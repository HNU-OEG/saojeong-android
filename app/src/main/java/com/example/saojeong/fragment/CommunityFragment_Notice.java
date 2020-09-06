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
import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.CommunityAdapter_item;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.model.CommunityValue;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.board.GetPostListArrayDto;
import com.example.saojeong.rest.service.BoardService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityFragment_Notice extends Fragment{

    private CommunityAdapter_item mAdapter;
    private List<CommunityValue> mCommunityNormalValue;
    private List<CommunityValue> mCommunityHotValue;
    private RecyclerView mRecyclerViewCommunity;
    public CommunityFragment_Notice(){
    }
    private BoardService boardService;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.viewpager_notice, container, false);
        mRecyclerViewCommunity = view.findViewById(R.id.community_recycler);
        mCommunityNormalValue= new ArrayList<>();
        mCommunityHotValue= new ArrayList<>();
        load_GetPost();
        return view;
    }
    public void load_GetPost() {
        boardService = ServiceGenerator.createService(BoardService.class, TokenCase.getToken());
        boardService.getPostList(10003).enqueue(new Callback<GetPostListArrayDto>() {
            @Override
            public void onResponse(Call<GetPostListArrayDto> call, Response<GetPostListArrayDto> response) {
                GetPostListArrayDto body = response.body();
                if (response.code() == 201) {
                    mCommunityNormalValue = CommunityValue.createContactsList(body.getNormal());
                    mCommunityHotValue.clear();
                    mAdapter = new CommunityAdapter_item( mCommunityHotValue, mCommunityNormalValue,(MainActivity)getActivity());
                } else {
                }
                mRecyclerViewCommunity.setAdapter(mAdapter);
                mRecyclerViewCommunity.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<GetPostListArrayDto> call, Throwable t) {
            }
        });
    }


}
