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
import com.example.saojeong.model.CommunityValue;
import com.example.saojeong.model.Community_CommentValue;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.CommunityDto;
import com.example.saojeong.rest.dto.Community_CreateBoardPostDto;
import com.example.saojeong.rest.service.Community_Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Community_WriteFragment extends Fragment {

    private Community_Service community_Service;

    EditText et_Title;
    EditText et_Content;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_community_write, container, false); //0,2,외 이탭

        community_Service = ServiceGenerator.createService(Community_Service.class, "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0ZWFtLk9qZW9uZ2RvbmcuRWNvbm9taWNzLkd1YXJkaWFucyIsImV4cCI6MTU5NzU4ODU3MSwibWVtYmVyX2lkIjoiMEJSNGkwTU92SnA5SzdNWlJCdWNsYWFpWjdFQiIsIm5pY2tuYW1lIjoi7J2166qF7J2YIOuRkOuNlOyngCIsInVzZXJ0eXBlIjoxfQ.G0SdapZG7h9Lr5kJf0P8ecl71DXiLFHicq6805RHDvY");


        Toolbar toolbar = view.findViewById(R.id.community_write_toolbar);
        et_Title = view.findViewById(R.id.et_community_write_title);
        et_Content = view.findViewById(R.id.et_community_write_content);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
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
                //뒤로가기버튼
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void CreatePost() {
        Log.d("LOADSTORES HERE", "HERE");
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
        Call<List<Community_CreateBoardPostDto>> call=community_Service.CreatePost(strTitle, strContent);
        call.enqueue(new Callback<List<Community_CreateBoardPostDto>>() {
            @Override
            public void onResponse(Call<List<Community_CreateBoardPostDto>> call,
                                   Response<List<Community_CreateBoardPostDto>> response) {
                if (response.isSuccessful()) {
                    for (Community_CreateBoardPostDto dto:response.body()) {
                        CommunityValue com=new CommunityValue("제목은 두껍게! 한눈에 보이도록!","가나다라","07. 13 03:29","6",0,0, true);

                    }
                } else {
                    Log.d("REST FAILED MESSAGE", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Community_CreateBoardPostDto>> call, Throwable t) {
                Log.d("REST ERROR!", t.getMessage());
            }
        });
    }
}