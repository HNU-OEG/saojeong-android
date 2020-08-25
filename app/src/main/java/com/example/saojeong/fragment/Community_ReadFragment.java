package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.CommunityAdapter_Comment;
import com.example.saojeong.adapter.CommunityAdapter_item;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.model.CommunityValue;
import com.example.saojeong.model.Community_CommentValue;
import com.example.saojeong.model.PostValue;
import com.example.saojeong.model.Post_CommentValue;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.board.GetPostDto;
import com.example.saojeong.rest.dto.board.GetPostListArrayDto;
import com.example.saojeong.rest.service.BoardService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Community_ReadFragment extends Fragment implements View.OnClickListener{
    int document_id;
    TextView mtitle;
    TextView mboard;
    TextView mdate;
    TextView mname;
    TextView mcontents;
    EditText mComment_edit;
    List<Post_CommentValue> mCommunityCommentValue;
    PostValue mPostValue;
    CommunityAdapter_Comment mAdapter;
    RecyclerView mRecycleview;
    NestedScrollView mNestedScroll;

    TextView mComment_btn;
    TextView mLikeUp;
    TextView mLikeDown;
   // private Community_Service community_Service;
   private BoardService boardService;

    public Community_ReadFragment() {
    }

    public Community_ReadFragment(int document_id) {
        this.document_id=document_id;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.fragment_community_read, container, false); //0,2,외 이탭

        boardService = ServiceGenerator.createService(BoardService.class, TokenCase.getToken());
        load_GetPost();
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

         //((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         //((MainActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
         ((MainActivity)getActivity()).getSupportActionBar().setTitle("");
        mtitle = view.findViewById(R.id.tv_community_read_title);
        mboard = view.findViewById(R.id.tv_community_read_board);
        mdate = view.findViewById(R.id.tv_community_read_date);
        mname = view.findViewById(R.id.tv_community_read_name);
        mcontents = view.findViewById(R.id.tv_community_read_contents);
        mComment_edit=view.findViewById(R.id.tv_comment_edit);
        mComment_btn=view.findViewById(R.id.tv_btn_inputcomment);
        mRecycleview=view.findViewById(R.id.commentRecycle);
        mNestedScroll=view.findViewById(R.id.testscroll);
        mLikeUp=view.findViewById(R.id.tv_community_like_up);
        mLikeDown=view.findViewById(R.id.tv_community_like_down);







        SpannableString content = new SpannableString(mComment_btn.getText());
        content.setSpan(new UnderlineSpan(), 0, mComment_btn.getText().length(), 0);
        mComment_btn.setText(content);



        //SpannableString content = new SpannableString(mComment_btn.getText());
        //content.setSpan(new UnderlineSpan(), 0, mComment_btn.getText().length(), 0);
        //mComment_btn.setText(content);

        return view;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch(id) {
            case R.id.tv_community_like_up:
                mLikeUp.setTextColor(Color.parseColor("#ff6950"));
                LikePost("vote", "up");
                //라이크 버튼 이벤트
                break;
            case R.id.tv_community_like_down:
                mLikeDown.setTextColor(Color.parseColor("#878787"));
                LikePost("blame", "up");
                //라이크 버튼 이벤트
                break;
            case R.id.tv_btn_inputcomment:
                String str=mComment_edit.getText().toString();
                mComment_edit.setText("");
                Edit_Comment(str);
                break;
        }
    }
    public void load_GetPost() {
        boardService.getPost(10004, document_id).enqueue(new Callback<GetPostDto>() {
            @Override
            public void onResponse(Call<GetPostDto> call, Response<GetPostDto> response) {
                GetPostDto body = response.body();

                if (response.code() == 201) { // 서버와 통신 성공
                    mPostValue =new PostValue(body.getContentDto());
                    mtitle.setText(mPostValue.getTitle());
                    mboard.setText(mPostValue.getCategory());
                    mdate.setText(mPostValue.getCreatedAt());
                    mname.setText(mPostValue.getAuthor());
                    mcontents.setText(mPostValue.getContent());

                    mLikeUp.setText("추천 "+mPostValue.getVotedCount());
                    mLikeDown.setText("비추천 "+mPostValue.getBlamedCount());
                    mCommunityCommentValue=Post_CommentValue.createContactsList(body.getComments());
                    mAdapter = new CommunityAdapter_Comment(mCommunityCommentValue, getContext(), false);
                    mRecycleview.setAdapter(mAdapter);
                    mRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
                    mRecycleview.setNestedScrollingEnabled(false);
                } else { // 서버에서 문제 발생
                    //likeStores = ContactShopOC._createContactsList(20);
                    //likeStoreAdapter = new LikeStoreAdapter(likeStores);
                }
            }

            @Override
            public void onFailure(Call<GetPostDto> call, Throwable t) {
                Log.d("fail", t.getMessage());
            }
        });
    }

    public void LikePost(String type, String task) {
        boardService.likePost(10004, document_id, type, task).enqueue(new Callback<GetPostDto>() {
            @Override
            public void onResponse(Call<GetPostDto> call, Response<GetPostDto> response) {

                if (response.code() == 201) { // 서버와 통신 성공
                      } else { // 서버에서 문제 발생
                    //likeStores = ContactShopOC._createContactsList(20);
                    //likeStoreAdapter = new LikeStoreAdapter(likeStores);
                }
            }

            @Override
            public void onFailure(Call<GetPostDto> call, Throwable t) {
                Log.d("fail", t.getMessage());
            }
        });
    }


    public void Edit_Comment(String strComment) {
//        boardService.createComment(10004, document_id).enqueue(new Callback<GetPostDto>() {
//            @Override
//            public void onResponse(Call<GetPostDto> call, Response<GetPostDto> response) {
//                GetPostDto body = response.body();
//
//                if (response.code() == 201) { // 서버와 통신 성공
//                    mPostValue =new PostValue(body.getContentDto());
//                    mtitle.setText(mPostValue.getTitle());
//                    mboard.setText(mPostValue.getCategory());
//                    mdate.setText(mPostValue.getCreatedAt());
//                    mname.setText(mPostValue.getAuthor());
//                    mcontents.setText(mPostValue.getContent());
//                    mCommunityCommentValue=Post_CommentValue.createContactsList(body.getComments());
//                    mAdapter = new CommunityAdapter_Comment(mCommunityCommentValue, getContext());
//                    mRecycleview.setAdapter(mAdapter);
//                    mRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
//                    mRecycleview.setNestedScrollingEnabled(false);
//                } else { // 서버에서 문제 발생
//                    //likeStores = ContactShopOC._createContactsList(20);
//                    //likeStoreAdapter = new LikeStoreAdapter(likeStores);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetPostDto> call, Throwable t) {
//                Log.d("fail", t.getMessage());
//            }
//        });
    }

}
