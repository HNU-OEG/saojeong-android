package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.model.PostValue;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.board.CommentDto;
import com.example.saojeong.rest.dto.board.GetPostDto;
import com.example.saojeong.rest.service.BoardService;
import com.example.saojeong.rest.service.StoreService;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyQnAItemFragment extends Fragment {

    private TextView title;
    private TextView status;
    private TextView date;
    private TextView content;
    private TextView comment;

    private BoardService boardService;

    public static MyQnAItemFragment newInstance() { return new MyQnAItemFragment(); }
    public static MyQnAItemFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);

        MyQnAItemFragment fragment = new MyQnAItemFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_myqna, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_myqna);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.~~); // 뒤로가기 화살표 이미지 바꾸기
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("");
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.BLACK);

        boardService = ServiceGenerator.createService(BoardService.class, TokenCase.getToken());

        title = view.findViewById(R.id.tv_QnA_title);
        status = view.findViewById(R.id.tv_QnA_status);
        date = view.findViewById(R.id.tv_QnA_date);
        content = view.findViewById(R.id.tv_QnA_content);
        comment = view.findViewById(R.id.tv_QnA_comment);

        // TODO: 2020-08-25-025 통신

        if (getArguments() != null) {
            int id = getArguments().getInt("id");
            Log.d("MyQnA", "ID : " + id);
            Call<GetPostDto> call = boardService.getPost(10001,id);
            call.enqueue(new Callback<GetPostDto>() {
                @Override
                public void onResponse(Call<GetPostDto> call, Response<GetPostDto> response) {
                    GetPostDto body = response.body();

                    if(response.code() == 201) {
                        PostValue mPostValue = new PostValue(body.getContentDto());
                        String mContent = mPostValue.getContent();
                        mContent = mContent.replace('$', '\n');
                        title.setText(mPostValue.getTitle());
                        date.setText(mPostValue.getCreatedAt());
                        content.setText(mContent);
                        List<CommentDto> mComment = body.getComments();

                        String mStatus = "";

                        if (mComment.size() == 0) {
                            mStatus = "[답변전]";
                            comment.setText("등록된 답변이 없습니다.");
                        } else {
                            mStatus = "[답변완료]";
                            comment.setText(mComment.get(0).getContent());
                        }
                        status.setText(mStatus);
                    }
                }

                @Override
                public void onFailure(Call<GetPostDto> call, Throwable t) {
                    Log.d("MyQnA", "MyQnAItem FAIL " + t.getMessage());
                }
            });
        } else {

        }

        title.setText("제목");
        status.setText("[답변완료]");
        date.setText("날짜");
        content.setText("본문");
        comment.setText("답변");
        return view;
    }
}
