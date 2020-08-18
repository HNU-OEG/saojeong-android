package com.example.saojeong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.adapter.FruitCloseAdapter;
import com.example.saojeong.model.ContactFruitClose;

import java.util.List;

public class testTutoPagerRecyclerAdapter extends RecyclerView.Adapter<testTutoPagerRecyclerAdapter.ViewHolder>{


    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rl_testTuto;
        LinearLayout ll_test0;
        LinearLayout ll_test1;
        Button login_facebook;
        Button login_kakaotalk;
        Button login_naver;
        Button login_google;

        public ViewHolder(View itemView) {
            super(itemView);
            rl_testTuto=itemView.findViewById(R.id.intro_background);
            ll_test0=itemView.findViewById(R.id.ll_test0);
            login_facebook=itemView.findViewById(R.id.login_facebook);
            login_kakaotalk=itemView.findViewById(R.id.login_kakaotalk);
            login_naver=itemView.findViewById(R.id.login_naver);
            login_google=itemView.findViewById(R.id.login_google);
        }
    }

    private List<TestTutomodel> mContacts;

    public testTutoPagerRecyclerAdapter(List<TestTutomodel> contacts) {
        mContacts = contacts;
    }

    @Override
    public testTutoPagerRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.testtutopage1, parent, false);

        testTutoPagerRecyclerAdapter.ViewHolder viewHolder = new testTutoPagerRecyclerAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(testTutoPagerRecyclerAdapter.ViewHolder holder, int position) {
        TestTutomodel testTutomodel = mContacts.get(position);
        holder.rl_testTuto.setBackgroundResource(testTutomodel.mImageSrc);
        if(!testTutomodel.GetLogin()) {
            holder.ll_test0.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}