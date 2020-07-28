package com.example.saojeong;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CommunityReadActivity extends AppCompatActivity {

    TextView mtitle;
    TextView mboard;
    TextView mdate;
    TextView mname;
    TextView mcontents;
    TextView mloginuser_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_read);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mtitle = findViewById(R.id.tv_community_read_title);
        mboard = findViewById(R.id.tv_community_read_board);
        mdate = findViewById(R.id.tv_community_read_date);
        mname = findViewById(R.id.tv_community_read_name);
        mcontents = findViewById(R.id.tv_community_read_contents);
        mloginuser_name = findViewById(R.id.tv_community_loginuser_name);
    }
    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    //    MenuInflater menuInflater = getMenuInflater();
    //    menuInflater.inflate(R.menu.menu_community_write, menu);
    //    return super.onCreateOptionsMenu(menu);
    //}
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: //뒤로가기버튼
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
