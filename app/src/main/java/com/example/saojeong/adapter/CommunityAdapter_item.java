package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.CommunityValue;

import java.util.ArrayList;
import java.util.List;

public class CommunityAdapter_item extends RecyclerView.Adapter<CommunityAdapter_item.ViewHolder>{
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewTitle;
        public TextView mTextViewName;
        public TextView mTextViewDate;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.tv_community_Title_item);
            mTextViewName = (TextView) itemView.findViewById(R.id.tv_community_name_item);
            mTextViewDate = (TextView) itemView.findViewById(R.id.tv_community_date_item);
        }
    }

    private ArrayList<CommunityValue> mContacts;

    public CommunityAdapter_item(ArrayList<CommunityValue> contacts) {
        mContacts = contacts;
    }

    @Override
    public CommunityAdapter_item.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_community, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommunityAdapter_item.ViewHolder holder, int position) {
        //Contact contact = mContacts.get(position);

        holder.mTextViewDate.setText("이곳은 날짜");
        holder.mTextViewName.setText("이름");
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}
