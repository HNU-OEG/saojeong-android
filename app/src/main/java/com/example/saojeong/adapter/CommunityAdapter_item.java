package com.example.saojeong.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.fragment.Community_ReadFragment;
import com.example.saojeong.model.CommunityValue;
import com.example.saojeong.model.PostValue;
import com.example.saojeong.rest.service.BoardService;

import java.util.List;

public class CommunityAdapter_item extends RecyclerView.Adapter<CommunityAdapter_item.ViewHolder>{
    public MainActivity RootActivity;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public int document_Id;
        public TextView mTextViewTitle_Popularity;
        public TextView mTextViewTitle;
        public TextView mTextViewName;
        public TextView mTextViewDate;
        public TextView mTextViewComment;
        public TextView mTextViewvote;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewTitle_Popularity = itemView.findViewById(R.id.tv_community_Title_popularity);
            mTextViewTitle = itemView.findViewById(R.id.tv_community_Title_item);
            mTextViewName = itemView.findViewById(R.id.tv_community_name_item);
            mTextViewDate = itemView.findViewById(R.id.tv_community_date_item);
            mTextViewComment = itemView.findViewById(R.id.tv_community_Title_comment);
            mTextViewvote = itemView.findViewById(R.id.tv_community_comment_vote);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Community_ReadFragment fragment= new Community_ReadFragment(document_Id);
                    //RootActivity.getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // 백스택 모두 지우기
                    RootActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_main, fragment) // frameLayout에 커뮤니티 Fragment 호출
                            .addToBackStack(null)
                            .commitAllowingStateLoss();


                }
            });
        }
    }

    private List<CommunityValue> mHotContacts;
    private List<CommunityValue> mNormalContacts;
    private int mBoard;

    public CommunityAdapter_item(List<CommunityValue> HotContact, List<CommunityValue> NormalContact, MainActivity activity) {
        if(HotContact!=null)
            mHotContacts = HotContact;
        mNormalContacts = NormalContact;
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
        if(mHotContacts.size()>position && mBoard==0)
        {
            CommunityValue contact = mHotContacts.get(position);
            holder.mTextViewTitle.setText(contact.getTitle());
            holder.mTextViewName.setText(contact.getAuthor());
            holder.mTextViewDate.setText(contact.getCreatedAt());
            holder.mTextViewComment.setText("["+contact.getCommentCount() + "]");
            holder.mTextViewvote.setText(contact.getVotedCount()+ "");
            CheckPopularity(true, holder);
            holder.document_Id=contact.getDocument_id();
        }
        else
        {
            CommunityValue contact = mNormalContacts.get((mBoard*10)+position);
            holder.mTextViewTitle.setText(contact.getTitle());
            holder.mTextViewName.setText(contact.getAuthor());
            holder.mTextViewDate.setText(contact.getCreatedAt());
            holder.mTextViewComment.setText("["+contact.getCommentCount() + "]");
            holder.mTextViewvote.setText(contact.getVotedCount()+ "");
            CheckPopularity(false, holder);
            holder.document_Id=contact.getDocument_id();
        }

    }

    @Override
    public int getItemCount() {
        int a=mHotContacts.size()+mNormalContacts.size();
        if(mBoard==0) {
            if (mHotContacts.size() + mNormalContacts.size() >= 12)
                return 12;
        }
        else
        {
            if(mNormalContacts.size()-(mBoard*10)>=10)
                return 10;
            else
                return mNormalContacts.size()%10;
        }
        return mHotContacts.size()+mNormalContacts.size();
    }

    //public int getItemCount() {
    //    return mHotContacts.size()+mNormalContacts.size();
    //}

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
