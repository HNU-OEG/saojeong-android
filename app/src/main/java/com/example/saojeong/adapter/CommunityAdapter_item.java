package com.example.saojeong.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.CommunityReadActivity;
import com.example.saojeong.R;
import com.example.saojeong.model.CommunityValue;

import java.util.ArrayList;

public class CommunityAdapter_item extends RecyclerView.Adapter<CommunityAdapter_item.ViewHolder>{
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewTitle;
        public TextView mTextViewName;
        public TextView mTextViewDate;
        public TextView mTextViewCommentSize;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.tv_community_Title_item);
            mTextViewName = (TextView) itemView.findViewById(R.id.tv_community_name_item);
            mTextViewDate = (TextView) itemView.findViewById(R.id.tv_community_date_item);
            mTextViewCommentSize = (TextView) itemView.findViewById(R.id.tv_community_comment_size);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context=v.getContext();
                    Intent intent = new Intent(context, CommunityReadActivity.class);
                    //intent.putExtra();
                    context.startActivity(intent);
                }
            });
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
        CommunityValue contact = mContacts.get(position);

        holder.mTextViewTitle.setText(contact.GetTitle());
        holder.mTextViewName.setText(contact.GetName());
        holder.mTextViewDate.setText(contact.GetDate());
        holder.mTextViewCommentSize.setText(contact.GetComment().size()+"");


    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}
