package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.ContactFish;

import java.util.List;

public class FishAdapter extends RecyclerView.Adapter<FishAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView FishTextView;
        public ImageView FishImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            FishTextView = (TextView) itemView.findViewById(R.id.tv_fish);
            FishImageView = (ImageView) itemView.findViewById(R.id.iv_fish);
        }
    }

    private List<ContactFish> mContacts;

    public FishAdapter(List<ContactFish> contacts) {
        mContacts = contacts;
    }

    @Override
    public FishAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_fish, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FishAdapter.ViewHolder holder, int position) {
        ContactFish contactSeason = mContacts.get(position);

        TextView tv_fish = holder.FishTextView;
        tv_fish.setText(contactSeason.getmFish());

        ImageView iv_fish = holder.FishImageView;
        iv_fish.setImageResource(contactSeason.getmFiImage());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}