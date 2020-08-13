package com.example.saojeong.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.Community_CommentValue;


import java.util.ArrayList;

public class CommunityAdapter_Comment extends RecyclerView.Adapter<CommunityAdapter_Comment.ViewHolder>{

    public RelativeLayout mLayout;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView_ID;
        public TextView mTextView_Date;
        public TextView mTextView_Content;
        public TextView mTextView_Btn_ReComment;
        public ImageView mImageView_Image;
        public LinearLayout mCommentLayout;
        public EditText mEditView_Recomment;
        public TextView mTextView_Btn_ReComment_Write;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView_ID = (TextView) itemView.findViewById(R.id.tv_community_comment_id);
            mTextView_Date = (TextView) itemView.findViewById(R.id.tv_community_comment_date);
            mTextView_Content = (TextView) itemView.findViewById(R.id.tv_community_comment_contents);
            mTextView_Btn_ReComment = (TextView) itemView.findViewById(R.id.tv_community_comment_btn_recomment);
            mImageView_Image=(ImageView)itemView.findViewById(R.id.iv_community_comment_image);
            mEditView_Recomment=itemView.findViewById(R.id.et_community_comment_recomment);
            mTextView_Btn_ReComment_Write=itemView.findViewById(R.id.tv_community_btn_comment_write);
            mLayout=itemView.findViewById(R.id.item_community_layout);
            mCommentLayout=itemView.findViewById(R.id.ll_community_recomment);
            mCommentLayout.setVisibility(View.GONE);

            //    btnLeft.setVisibility(View.VISIBLE);
            SpannableString content = new SpannableString(mTextView_Btn_ReComment.getText());
            content.setSpan(new UnderlineSpan(), 0, mTextView_Btn_ReComment.getText().length(), 0);
            mTextView_Btn_ReComment.setText(content);

            mTextView_Btn_ReComment.setOnClickListener(v -> {
                if(mCommentLayout.getVisibility()==View.VISIBLE) {
                    mCommentLayout.setVisibility(View.GONE);
                }
                else {
                    mCommentLayout.setVisibility(View.VISIBLE);
                }
                //답글쓰기버튼클릭시 작동
            });

            mTextView_Btn_ReComment_Write.setOnClickListener(v -> {
                String str=mEditView_Recomment.getText().toString(); //
                if(str.length()>0){

                }

            });
        }
    }

    private ArrayList<Community_CommentValue> mContacts;

    public CommunityAdapter_Comment(ArrayList<Community_CommentValue> contacts) {
        mContacts = contacts;
    }
    @Override
    public CommunityAdapter_Comment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_community_comment, parent, false);

        CommunityAdapter_Comment.ViewHolder viewHolder = new CommunityAdapter_Comment.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommunityAdapter_Comment.ViewHolder holder, int position) {
        Community_CommentValue contact = mContacts.get(position);
        holder.mTextView_ID.setText(contact.GetID());
        holder.mTextView_Date.setText(contact.GetDate());
        holder.mTextView_Content.setText(contact.GetComment());
        //holder.mTextView_Btn_ReComment.setText("["+contact.GetComment().size() + "]");
        CheckReComment(contact.GetReContents(), holder);
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }



    public void CheckReComment(boolean check, CommunityAdapter_Comment.ViewHolder holder) {
        if(check) {
            holder.mTextView_Btn_ReComment.setVisibility(View.GONE);
            holder.mImageView_Image.setVisibility(View.VISIBLE);
        }
        else {
            holder.mTextView_Btn_ReComment.setVisibility(View.VISIBLE);
            holder.mImageView_Image.setVisibility(View.GONE);
        }
    }



}

