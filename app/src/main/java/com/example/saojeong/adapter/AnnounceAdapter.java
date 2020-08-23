package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.saojeong.R;
import com.example.saojeong.model.ContactAnnounce;
import com.example.saojeong.model.ContactFood;

import java.util.List;

public class AnnounceAdapter extends RecyclerView.Adapter<AnnounceAdapter.ViewHolder> {
    private RequestManager glide;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView AnnounceImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            AnnounceImageView = (ImageView) itemView.findViewById(R.id.iv_announce);
        }
    }

    private List<ContactAnnounce> mContacts;

    public AnnounceAdapter(List<ContactAnnounce> contacts) {
        mContacts = contacts;
        glide = null;
    }

    public AnnounceAdapter(RequestManager with, List<ContactAnnounce> contacts) {
        mContacts = contacts;
        glide = null;
    }

    @Override
    public AnnounceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_announce, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AnnounceAdapter.ViewHolder holder, int position) {
        ContactAnnounce contactSeason = mContacts.get(position);

        ImageView iv_announce = holder.AnnounceImageView;
        iv_announce.setImageResource(contactSeason.getMFvImage());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}