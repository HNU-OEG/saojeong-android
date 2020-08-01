package com.example.saojeong;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_read);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");
        mtitle = findViewById(R.id.tv_community_read_title);
        mboard = findViewById(R.id.tv_community_read_board);
        mdate = findViewById(R.id.tv_community_read_date);
        mname = findViewById(R.id.tv_community_read_name);
        mcontents = findViewById(R.id.tv_community_read_contents);
        mloginuser_name = findViewById(R.id.tv_community_loginuser_name);
        mloginuser_comment = findViewById(R.id.tv_community_loginuser_comment);
        mRecycleview=findViewById(R.id.testRecycle);

        mCommunityCommentValue= new ArrayList<>();

        mCommunityCommentValue.add(new Community_CommentValue("1","2","3", false));
        mCommunityCommentValue.add(new Community_CommentValue("8","2","2", false));
        mCommunityCommentValue.add(new Community_CommentValue("8","3","2", false));
        mCommunityCommentValue.add(new Community_CommentValue("8","4","2", false));
        mCommunityCommentValue.add(new Community_CommentValue("8","5","2", false));
        mCommunityCommentValue.add(new Community_CommentValue("8","6","2", false));
        mCommunityCommentValue.add(new Community_CommentValue("8","7","2", false));
        mCommunityCommentValue.add(new Community_CommentValue("8","8","2", false));
        mCommunityCommentValue.add(new Community_CommentValue("8","9","2", false));


        mAdapter = new CommunityAdapter_Comment(mCommunityCommentValue, 0);
        mRecycleview.setAdapter(mAdapter);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
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
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
