package com.example.saojeong;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.adapter.CommunityAdapter_Comment;
import com.example.saojeong.adapter.CommunityAdapter_item;
import com.example.saojeong.model.CommunityValue;
import com.example.saojeong.model.Community_CommentValue;

import java.util.ArrayList;

public class CommunityReadActivity extends AppCompatActivity {

    TextView mtitle;
    TextView mboard;
    TextView mdate;
    TextView mname;
    TextView mcontents;
    TextView mloginuser_name;
    EditText mloginuser_comment;
    ArrayList<Community_CommentValue> mCommunityCommentValue;
    CommunityAdapter_Comment mAdapter;
    RecyclerView mRecycleview;
    LinearLayout mLinearLayout;
    NestedScrollView mNestedScroll;
    boolean isBottomDownCheck;
    boolean isBottomUpCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_read);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");
        isBottomDownCheck=false;
        isBottomUpCheck=true;
        mtitle = findViewById(R.id.tv_community_read_title);
        mboard = findViewById(R.id.tv_community_read_board);
        mdate = findViewById(R.id.tv_community_read_date);
        mname = findViewById(R.id.tv_community_read_name);
        mcontents = findViewById(R.id.tv_community_read_contents);
        //mloginuser_name = findViewById(R.id.tv_community_loginuser_name);
        //mloginuser_comment = findViewById(R.id.tv_community_loginuser_comment);
        mRecycleview=findViewById(R.id.testRecycle);
        mLinearLayout=findViewById(R.id.community_read_edit);
        mNestedScroll=findViewById(R.id.testscroll);

        mCommunityCommentValue= new ArrayList<>();
        mCommunityCommentValue.add(new Community_CommentValue("1","2","3", false));
        mCommunityCommentValue.add(new Community_CommentValue("8","2","2", false));
        mCommunityCommentValue.add(new Community_CommentValue("8","3","2", false));
        mCommunityCommentValue.add(new Community_CommentValue("8","4","2", false));
        mCommunityCommentValue.add(new Community_CommentValue("8","5","2", false));
        mCommunityCommentValue.add(new Community_CommentValue("8","6","2", false));
        mCommunityCommentValue.add(new Community_CommentValue("8","7","2", true));
        mCommunityCommentValue.add(new Community_CommentValue("8","8","2", true));
        mCommunityCommentValue.add(new Community_CommentValue("8","9","2", false));
        mNestedScroll.scrollTo(0, 0);
        mNestedScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                String TAG = "nested_sync";
                if (scrollY > oldScrollY) {
                    Log.i(TAG, "Scroll DOWN");
                    community_Bottom_Down_Ani();
                }
                if (scrollY < oldScrollY) {
                    Log.i(TAG, "Scroll UP");

                    community_Bottom_Up_Ani();

                }
                if (scrollY == 0) {
                    Log.i(TAG, "TOP SCROLL");
                }
                float scroll11Y = (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight());
                if (scrollY >= (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {

                    community_Bottom_Down_Ani();
                }
                else
                {
                    community_Bottom_Up_Ani();
                }
            }
        });
        mAdapter = new CommunityAdapter_Comment(mCommunityCommentValue);
        mRecycleview.setAdapter(mAdapter);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        mRecycleview.setNestedScrollingEnabled(false);
    }
    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    //    MenuInflater menuInflater = getMenuInflater();
    //    menuInflater.inflate(R.menu.menu_community_write, menu);
    //    return super.onCreateOptionsMenu(menu);
    //}
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void community_Bottom_Up_Ani()
    {
        isBottomDownCheck=false;
        if(!isBottomUpCheck)
        {
            community_Botton_Up();
            isBottomUpCheck=true;
        }
    }
    public void community_Bottom_Down_Ani()
    {
        isBottomUpCheck=false;
        if(!isBottomDownCheck) {
            community_Botton_Down();
            isBottomDownCheck=true;
        }
    }

    public void community_Botton_Up()
    {
        Animation bottomUp = AnimationUtils.loadAnimation(this,
                R.anim.community_bottom_up);
        mLinearLayout.startAnimation(bottomUp);
        mLinearLayout.setVisibility(View.VISIBLE);
    }
    public void community_Botton_Down()
    {
        Animation bottomDown = AnimationUtils.loadAnimation(this,
                R.anim.community_bottom_down);
        mLinearLayout.startAnimation(bottomDown);
        mLinearLayout.setVisibility(View.GONE);
    }


}
