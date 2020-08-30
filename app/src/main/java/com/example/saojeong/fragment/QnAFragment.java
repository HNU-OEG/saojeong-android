package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.board.CreatePostDto;
import com.example.saojeong.rest.service.BoardService;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class QnAFragment extends Fragment {
    private final String TAG = this.getClass().getName();
    private BoardService boardService;

    private EditText et_qna;
    private Button btn_qna_submit;
    private InputMethodManager imm;

    public static QnAFragment newInstance() {
        return new QnAFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "QnA page start");

        boardService = ServiceGenerator.createService(BoardService.class, TokenCase.getToken());

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_qna, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_qna);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(MyPageFragment.newInstance());
            }
        });
        //((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.~~); // 뒤로가기 화살표 이미지 바꾸기
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("");
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.BLACK);

        imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        et_qna = view.findViewById(R.id.et_qna);
        btn_qna_submit = view.findViewById(R.id.btn_qna_submit);

        btn_qna_submit.setOnClickListener((v) -> {
            // TODO: 2020-08-22-022 서버로 전송
            String content = et_qna.getText().toString().replace('\n', '$');

            Log.d(TAG, "content: " + Arrays.toString(et_qna.getText().toString().split("\n")));

            boardService.createPost(new CreatePostDto("문의사항", content), 10001).enqueue(new Callback<CreatePostDto>() {
                @Override
                public void onResponse(Call<CreatePostDto> call, Response<CreatePostDto> response) {
                    if (response.code() == 201) {
                        CreatePostDto body = response.body();
                        Log.d(TAG, "전송완료");
                    }
                    Log.d(TAG, response.message());
                }

                @Override
                public void onFailure(Call<CreatePostDto> call, Throwable t) {
                    Log.d(TAG, t.getMessage());
                }
            });

            ((MainActivity)getActivity()).replaceFragment(MyPageFragment.newInstance());


        });

        return view;
    }
}
