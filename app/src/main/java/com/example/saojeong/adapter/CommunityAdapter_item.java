package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.fragment.Community_ReadFragment;
import com.example.saojeong.model.CommunityValue;

import java.util.ArrayList;

public class CommunityAdapter_item extends RecyclerView.Adapter<CommunityAdapter_item.ViewHolder>{
    private MainActivity RootActivity;
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle_Popularity;
        private TextView mTextViewTitle;
        private TextView mTextViewName;
        private TextView mTextViewDate;
        private TextView mTextViewComment;
        private TextView mTextViewReCommentSize;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewTitle_Popularity = (TextView) itemView.findViewById(R.id.tv_community_Title_popularity);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.tv_community_Title_item);
            mTextViewName = (TextView) itemView.findViewById(R.id.tv_community_name_item);
            mTextViewDate = (TextView) itemView.findViewById(R.id.tv_community_date_item);
            mTextViewComment = (TextView) itemView.findViewById(R.id.tv_community_Title_comment);
            mTextViewReCommentSize = (TextView) itemView.findViewById(R.id.tv_community_comment_recommend);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }


    private ArrayList<CommunityValue> mContacts;
    private int mBoard;

    public CommunityAdapter_item(ArrayList<CommunityValue> contacts, int Board, MainActivity activity) {
        mContacts = contacts;
        mBoard=Board;
        RootActivity=activity;
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

        CommunityValue contact = mContacts.get(position+mBoard*10);
        holder.mTextViewTitle.setText(contact.GetTitle());
        holder.mTextViewName.setText(contact.GetName());
        holder.mTextViewDate.setText(contact.GetDate());
        holder.mTextViewComment.setText("["+contact.GetComment().size() + "]");
        holder.mTextViewReCommentSize.setText(contact.GetGoodCommend()+ "");
        CheckPopularity(contact.GetPopular(), holder);
    }

    @Override
    public int getItemCount() {
        if(mContacts.size()-mBoard*10>=10)
            return 9;
        else {
            return ((mContacts.size() - mBoard * 10) % 10) ;
        }
    }

    public void UpBoard()
    {
        ++mBoard;
    }
    public void DownBoard() {
        if(mBoard!=0)
            --mBoard;
    }
    public void CheckPopularity(boolean check, CommunityAdapter_item.ViewHolder holder) {
        if(check) {
            holder.mTextViewTitle_Popularity.setVisibility(View.VISIBLE);
        }
        else {
            holder.mTextViewTitle_Popularity.setVisibility(View.GONE);
        }
    }

}
