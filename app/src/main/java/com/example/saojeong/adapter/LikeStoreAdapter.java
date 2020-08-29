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
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.saojeong.R;
import com.example.saojeong.model.ContactShopOC;
import com.example.saojeong.model.LikeStore;
import com.example.saojeong.model.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class LikeStoreAdapter extends RecyclerView.Adapter<LikeStoreAdapter.ViewHolder> {
    private final RequestManager glide;

    public OnItemClickListener<LikeStoreAdapter.ViewHolder> mListener = null;

    public void setOnItemClickListener(OnItemClickListener<LikeStoreAdapter.ViewHolder> mListener) {
        this.mListener = mListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public ImageView img_like;
        public TextView codeStore;
        public TextView nameStore;
        public TextView rateStore;
        public TextView rateCountStore;

        // Store ID 추가
        public int storeId;

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
                    if (!isNull(mListener)) {
                        mListener.onItemClick(LikeStoreAdapter.ViewHolder.this);
                    }
                }

                boolean isNull(OnItemClickListener<LikeStoreAdapter.ViewHolder> l) {
                    return l == null;
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

        // Store ID 바인딩
        holder.storeId = likeStore.getMShopId();


        if (glide == null) {
            image.setImageResource(likeStore.getMImage());
        } else {
            glide.load(likeStore.get_mImage())
                    .transform(new MultiTransformation<>(new CenterCrop(), new RoundedCorners(50)))
                    .into((image));
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
