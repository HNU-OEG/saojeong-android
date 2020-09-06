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


public class CommunityUserFragment extends Fragment implements View.OnClickListener {

    private CommunityAdapter_item mAdapter;
    private List<CommunityValue> mCommunityNormalValue;
    private List<CommunityValue> mCommunityHotValue;
    private TextView btnLeft;
    private TextView btnRight;
    private TextView tvBoard;
    private static NestedScrollView scroll;
    private RecyclerView mRecyclerViewCommunity;
    private int board=0;
    public CommunityUserFragment(){
    }
    public NestedScrollView getScroll(){
        return scroll;
    }
    private BoardService boardService;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.viewpaper_community, container, false);
        mRecyclerViewCommunity = view.findViewById(R.id.community_recycler);
        mCommunityNormalValue= new ArrayList<>();
        mCommunityHotValue= new ArrayList<>();
        btnLeft=view.findViewById(R.id.tv_community_btn_Left);
        btnLeft.setOnClickListener(this);
        btnRight=view.findViewById(R.id.tv_community_btn_Right);
        btnRight.setOnClickListener(this);
        scroll=view.findViewById(R.id.scrollns_community);
        tvBoard=view.findViewById(R.id.tv_community_board);
        tvBoard.setText(board+1+"");
        load_GetPost();

        return view;
    }
    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch(id) {
            case R.id.tv_community_btn_Left:
                btn_Left();
                break;
            case R.id.tv_community_btn_Right:
                btn_Right();
                break;
        }
    }
    public void load_GetPost() {
        boardService = ServiceGenerator.createService(BoardService.class, TokenCase.getToken());
        boardService.userBoard().enqueue(new Callback<GetPostListArrayDto>() {
            @Override
            public void onResponse(Call<GetPostListArrayDto> call, Response<GetPostListArrayDto> response) {
                GetPostListArrayDto body = response.body();
                if (response.code() == 201|| response.code() == 200) {
                    if (body.getNormal() != null) {
                        mCommunityNormalValue = CommunityValue.createContactsList(body.getNormal());
                        mCommunityHotValue.clear();
                        btnLeft.setVisibility(View.GONE);
                        if (mCommunityHotValue.size() + mCommunityNormalValue.size() > 10) {
                            btnRight.setVisibility(View.VISIBLE);
                        } else
                            btnRight.setVisibility(View.GONE);
                        mAdapter = new CommunityAdapter_item(mCommunityHotValue, mCommunityNormalValue, (MainActivity) getActivity());
                    }
                    else {
                        mCommunityNormalValue.clear();
                    }
                    mRecyclerViewCommunity.setAdapter(mAdapter);
                    mRecyclerViewCommunity.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
            }

            @Override
            public void onFailure(Call<GetPostListArrayDto> call, Throwable t) {
            }
        });
    }

    public void btn_Right(){
        if((board+1)*10<mCommunityNormalValue.size()) {
            mAdapter.UpBoard();
            board++;
            tvBoard.setText(board + 1 + "");
            scroll.scrollTo(0, 0);
            mRecyclerViewCommunity.setAdapter(mAdapter);
            mRecyclerViewCommunity.setLayoutManager(new LinearLayoutManager(getActivity()));
            if (board == 0) {
                btnLeft.setVisibility(View.GONE);
            } else
                btnLeft.setVisibility(View.VISIBLE);

            if ((board + 1) * 10 >= mCommunityNormalValue.size()) {
                btnRight.setVisibility(View.GONE);
            } else
                btnRight.setVisibility(View.VISIBLE);
        }
    }


    public void btn_Left(){
        if(board!=0) {
            mAdapter.DownBoard();
            board--;
            tvBoard.setText(board + 1 + "");
            scroll.scrollTo(0, 0);
            mRecyclerViewCommunity.setAdapter(mAdapter);
            mRecyclerViewCommunity.setLayoutManager(new LinearLayoutManager(getActivity()));
            if (board == 0) {
                btnLeft.setVisibility(View.GONE);
            } else
                btnLeft.setVisibility(View.VISIBLE);

            if ((board + 1) * 10 >= mCommunityNormalValue.size()) {
                btnRight.setVisibility(View.GONE);
            } else
                btnRight.setVisibility(View.VISIBLE);
        }
    }

}
