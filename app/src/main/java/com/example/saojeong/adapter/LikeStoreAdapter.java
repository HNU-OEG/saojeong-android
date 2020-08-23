package com.example.saojeong.adapter;

import android.annotation.SuppressLint;
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
import com.example.saojeong.model.ContactShopOC;
import com.example.saojeong.model.LikeStore;
import com.example.saojeong.model.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class LikeStoreAdapter extends RecyclerView.Adapter<LikeStoreAdapter.ViewHolder>  implements OnItemClickListener<LikeStoreAdapter.ViewHolder> {
    private final RequestManager glide;

    OnItemClickListener listener;

    public void setOnItemClicklistener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(LikeStoreAdapter.ViewHolder holder, View view, int position) {
        if(listener != null) {
            listener.onItemClick(holder,view,position);
        }
    }


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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null) {
                        listener.onItemClick(LikeStoreAdapter.ViewHolder.this, view, position);
                    }
                }
            });
        }
    }

    private List<ContactShopOC> mLikeStore;

    public LikeStoreAdapter(List<ContactShopOC> stores) {
        this.mLikeStore = stores;
        glide = null;
    }

    public LikeStoreAdapter(RequestManager glide, List<ContactShopOC> stores) {
        this.mLikeStore = stores;
        this.glide = glide;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactShopOC likeStore = mLikeStore.get(position);

        ImageView image = holder.image;
        ImageView img_like = holder.img_like;
        TextView codeStore = holder.codeStore;
        TextView nameStore = holder.nameStore;
        TextView rateStore = holder.rateStore;
        TextView rateCountStore = holder.rateCountStore;

        if (glide == null) {
            image.setImageResource(likeStore.getMImage());
        } else {
            glide.load(likeStore.get_mImage()).into((image));
        }
        img_like.setImageResource(likeStore.isMLike()? R.drawable.like : R.drawable.unlike);
        codeStore.setText(likeStore.getMShopnum());
        nameStore.setText(likeStore.getMShopname());
        rateStore.setText(Double.toString(likeStore.getMStarscore()));
        rateCountStore.setText(likeStore.getMEvaluation());
    }

    @Override
    public int getItemCount() {
        return mLikeStore.size();
    }
}
