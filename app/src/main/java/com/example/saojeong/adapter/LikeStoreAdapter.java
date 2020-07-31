package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.LikeStore;

import java.util.ArrayList;

public class LikeStoreAdapter extends RecyclerView.Adapter<LikeStoreAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public ImageView img_like;
        public TextView codeStore;
        public TextView nameStore;
        public TextView rateStore;
        public TextView rateCountStore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.image);
            this.img_like = itemView.findViewById(R.id.img_like);
            this.codeStore = itemView.findViewById(R.id.codeStore);
            this.nameStore = itemView.findViewById(R.id.nameStore);
            this.rateStore = itemView.findViewById(R.id.rateStore);
            this.rateCountStore = itemView.findViewById(R.id.rateCountStore);
        }
    }

    private ArrayList<LikeStore> mLikeStore;

    public LikeStoreAdapter(ArrayList<LikeStore> stores) {
        this.mLikeStore = stores;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_likestore, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LikeStore likeStore = mLikeStore.get(position);

        ImageView image = holder.image;
        ImageView img_like = holder.img_like;
        TextView codeStore = holder.codeStore;
        TextView nameStore = holder.nameStore;
        TextView rateStore = holder.rateStore;
        TextView rateCountStore = holder.rateCountStore;

        image.setImageResource(likeStore.getmImage());
        img_like.setImageResource(likeStore.ismLike()? R.drawable.like : R.drawable.unlike);
        codeStore.setText(likeStore.getmCodeStore()+"번");
        nameStore.setText(likeStore.getmNameStore());
        rateStore.setText(Double.toString(likeStore.getmRateStore()));
        rateCountStore.setText(likeStore.getmRateCountStore()+"명이 평가하였습니다.");
    }

    @Override
    public int getItemCount() {
        return mLikeStore.size();
    }
}
