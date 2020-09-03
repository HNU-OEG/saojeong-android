package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.saojeong.R;
import com.example.saojeong.model.CommunityValue;
import com.example.saojeong.model.ContactAnnounce;
import com.example.saojeong.model.ContactFood;

import java.util.List;

public class AnnounceAdapter extends RecyclerView.Adapter<AnnounceAdapter.ViewHolder> {
    private RequestManager glide;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView AnnounceImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            AnnounceImageView = itemView.findViewById(R.id.iv_announce);
        }
    }

    private List<CommunityValue> mContacts;

    public AnnounceAdapter(List<CommunityValue> contacts) {
        mContacts = contacts;
        glide = null;
    }

    public AnnounceAdapter(RequestManager glide, List<CommunityValue> contacts) {
        mContacts = contacts;
        this.glide = glide;
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
        CommunityValue contactSeason = mContacts.get(position);
        ImageView iv_announce = holder.AnnounceImageView;
        if (glide != null) {
            glide.load(contactSeason.getTitle()).into(iv_announce);
        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}