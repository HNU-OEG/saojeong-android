package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.saojeong.R;
import com.example.saojeong.model.LikeStore;

import java.util.ArrayList;
import java.util.List;

import lombok.ToString;

@ToString
public class LikeStoreAdapter extends RecyclerView.Adapter<LikeStoreAdapter.ViewHolder> {
    private final RequestManager glide;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public ImageView img_like;
        public TextView codeStore;
        public TextView nameStore;
        public TextView rateStore;
        public TextView rateCountStore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.iv_shop);
            this.img_like = itemView.findViewById(R.id.iv_like);
            this.codeStore = itemView.findViewById(R.id.tv_shopnum);
            this.nameStore = itemView.findViewById(R.id.tv_shopname);
            this.rateStore = itemView.findViewById(R.id.tv_starscore);
            this.rateCountStore = itemView.findViewById(R.id.tv_evaluation);
        }
    }

    private List<LikeStore> mLikeStore;

    public LikeStoreAdapter(ArrayList<LikeStore> stores) {
        this.mLikeStore = stores;
        glide = null;
    }

    public LikeStoreAdapter(RequestManager glide, List<LikeStore> stores) {
        this.glide = glide;
        this.mLikeStore = stores;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
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

        glide.load(likeStore.get_mImage()).into((image));
        img_like.setImageResource(likeStore.isMLike() ? R.drawable.like : R.drawable.unlike);
        codeStore.setText(likeStore.getMCodeStore() + "번");
        nameStore.setText(likeStore.getMNameStore());
        rateStore.setText(Double.toString(likeStore.getMRateStore()));
        rateCountStore.setText(likeStore.getMRateCountStore() + "명이 평가하였습니다.");
    }

    @Override
    public int getItemCount() {
        return mLikeStore.size();
    }
}
