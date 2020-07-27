package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.ContactFullview;


import java.util.List;

public class FullviewAdapter extends RecyclerView.Adapter<FullviewAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView LeftImageView;
        public ImageView RightImageView;
        public ImageView FullviewImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            LeftImageView = (ImageView) itemView.findViewById(R.id.iv_left);
            RightImageView = (ImageView) itemView.findViewById(R.id.iv_right);
            FullviewImageView = (ImageView) itemView.findViewById(R.id.iv_fullview);
        }
    }

    private List<ContactFullview> mContacts;

    public FullviewAdapter(List<ContactFullview> contacts) {
        mContacts = contacts;
    }

    @Override
    public FullviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_fullview, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FullviewAdapter.ViewHolder holder, int position) {
        ContactFullview contactSeason = mContacts.get(position);

        ImageView iv_left = holder.LeftImageView;
        iv_left.setImageResource(contactSeason.getmFvLeft());

        ImageView iv_right = holder.RightImageView;
        iv_right.setImageResource(contactSeason.getmFvRight());

        ImageView iv_fullview = holder.FullviewImageView;
        iv_fullview.setImageResource(contactSeason.getmFvImage());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}