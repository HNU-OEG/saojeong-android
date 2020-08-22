package com.example.saojeong.adapter;


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

import com.example.saojeong.R;
import com.example.saojeong.model.TutorialValue;

import java.util.List;

public class TutorialAdapter extends RecyclerView.Adapter<TutorialAdapter.ViewHolder>{


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

    private List<TutorialValue> mContacts;

    public TutorialAdapter(List<TutorialValue> contacts) {
        mContacts = contacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_tutorial, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TutorialValue testTutomodel = mContacts.get(position);
        holder.rl_testTuto.setBackgroundResource(testTutomodel.GetImage());
        if(!testTutomodel.GetLogin()) {
            holder.ll_test0.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}