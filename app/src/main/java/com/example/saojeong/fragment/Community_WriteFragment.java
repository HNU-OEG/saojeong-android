package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.board.CreatePostDto;
import com.example.saojeong.rest.dto.board.GetPostDto;
import com.example.saojeong.rest.service.BoardService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Community_WriteFragment extends Fragment {

    //   private Community_Service community_Service;
    private InputMethodManager imm;
    private final String LOG = "CommunityWrite";

    EditText et_Title;
    EditText et_Content;
    private BoardService boardService;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_community_write, container, false);
        boardService = ServiceGenerator.createService(BoardService.class, TokenCase.getToken());
        imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        Toolbar toolbar = view.findViewById(R.id.community_write_toolbar);
        et_Title = view.findViewById(R.id.et_community_write_title);
        et_Content = view.findViewById(R.id.et_community_write_content);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_community_write, menu);
        MenuItem item = menu.getItem(0);
        SpannableString s = new SpannableString("작성");
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6950")), 0, s.length(), 0);
        s.setSpan(new AbsoluteSizeSpan(30), 0, s.length(),  Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        s.setSpan(new UnderlineSpan(), 0, s.length(),0);
        item.setTitle(s);

        super.onCreateOptionsMenu(menu,inflater);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_community_write_complete:
                CreatePost();
                return true;
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void CreatePost() {
        String strTitle=et_Title.getText().toString();
        if(strTitle.length()==0)
        {
            //제목을 입력하세요
            return;
        }
        String strContent=et_Content.getText().toString();
        if(strContent.length()==0)
        {
            //내용을 입력하세요
            return;
        }
        createPost(strTitle,strContent);
    }

    public void createPost(String title, String contents) {
        boardService.createPost(new CreatePostDto(title, contents), 10004).enqueue(new Callback<CreatePostDto>() {
            @Override
            public void onResponse(Call<CreatePostDto> call, Response<CreatePostDto> response) {

                if (response.code() == 201) {
                    if (response.code() == 201) {
                    CreatePostDto body = response.body();
                    Log.d(LOG, "전송완료");
                    getActivity().onBackPressed();
                }
                    Log.d(LOG, response.message());
                } else {

                }
            }

            @Override
            public void onFailure(Call<CreatePostDto> call, Throwable t) {
                Log.d("fail", t.getMessage());
            }
        });
    }
}