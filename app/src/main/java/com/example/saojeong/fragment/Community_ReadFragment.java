package com.example.saojeong.fragment;

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
import com.example.saojeong.model.Community_CommentValue;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.service.Community_Service;

import java.util.ArrayList;

public class Community_ReadFragment extends Fragment implements View.OnClickListener{
    TextView mtitle;
    TextView mboard;
    TextView mdate;
    TextView mname;
    TextView mcontents;
    TextView mComment_name;
    EditText mComment_comment;
    TextView mComment_btn;
    ArrayList<Community_CommentValue> mCommunityCommentValue;
    CommunityAdapter_Comment mAdapter;
    RecyclerView mRecycleview;
    LinearLayout mLinearLayout;
    NestedScrollView mNestedScroll;
    LinearLayout m_LikeUp;
    LinearLayout m_LikeDown;

    private Community_Service community_Service;

    public Community_ReadFragment() {
    }

    public Community_ReadFragment(ArrayList<Community_CommentValue> CommunityCommentValue) {
        mCommunityCommentValue=CommunityCommentValue;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.fragment_community_read, container, false); //0,2,외 이탭
        community_Service = ServiceGenerator.createService(Community_Service.class, "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0ZWFtLk9qZW9uZ2RvbmcuRWNvbm9taWNzLkd1YXJkaWFucyIsImV4cCI6MTU5NzU4ODU3MSwibWVtYmVyX2lkIjoiMEJSNGkwTU92SnA5SzdNWlJCdWNsYWFpWjdFQiIsIm5pY2tuYW1lIjoi7J2166qF7J2YIOuRkOuNlOyngCIsInVzZXJ0eXBlIjoxfQ.G0SdapZG7h9Lr5kJf0P8ecl71DXiLFHicq6805RHDvY");


        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

         ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         ((MainActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
         ((MainActivity)getActivity()).getSupportActionBar().setTitle("");
        mtitle = view.findViewById(R.id.tv_community_read_title);
        mboard = view.findViewById(R.id.tv_community_read_board);
        mdate = view.findViewById(R.id.tv_community_read_date);
        mname = view.findViewById(R.id.tv_community_read_name);
        mcontents = view.findViewById(R.id.tv_community_read_contents);
        mComment_name=view.findViewById(R.id.tv_community_comment_name);
        mComment_comment=view.findViewById(R.id.tv_community_comment_comment);
        //mComment_btn=view.findViewById(R.id.tv_community_btn_comment_write);
        mRecycleview=view.findViewById(R.id.testRecycle);
        mNestedScroll=view.findViewById(R.id.testscroll);
        m_LikeUp=view.findViewById(R.id.ll_community_like_up);
        m_LikeDown=view.findViewById(R.id.ll_community_like_down);

        mCommunityCommentValue= new ArrayList<>();
        mCommunityCommentValue.add(new Community_CommentValue("시장이용객23","07. 13   03:29","쓰촨 인정해. 진짜 맛있지.", false));
        mCommunityCommentValue.add(new Community_CommentValue("한남대학생","07. 13   03:29","뭐야. 좀 먹을 줄 아는 놈인가?", true));
        mCommunityCommentValue.add(new Community_CommentValue("시장이용객23","07. 13   03:29","쓰촨이 있었기에, 이번 한학기를 버텼다.", true));
        mCommunityCommentValue.add(new Community_CommentValue("시장사용자17","07. 13   03:29","시나브로 사장님 완젼 친절해요", false));
        mAdapter = new CommunityAdapter_Comment(mCommunityCommentValue);
        mRecycleview.setAdapter(mAdapter);
        mRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleview.setNestedScrollingEnabled(false);


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
            case R.id.ll_community_like_up:
                //라이크 버튼 이벤트
                break;
            case R.id.ll_community_like_down:
                //라이크 버튼 이벤트
                break;
        }
    }
}
