package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.saojeong.R;
import com.example.saojeong.model.ContactFishOpen;

import java.util.List;

public class FishOpenAdapter extends RecyclerView.Adapter<FishOpenAdapter.ViewHolder> {
    private final RequestManager glide;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView shopnumTextView;
        public TextView shopnameTextView;
        public TextView starTextView;
        public TextView starscoreTextView;
        public TextView evaluationTextView;
        public TextView selfintroductionTextView;
        public ImageView shopImageView;
        public ImageView favorateImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            shopnumTextView = (TextView) itemView.findViewById(R.id.tv_shopnum);
            shopnameTextView = (TextView) itemView.findViewById(R.id.tv_shopname);
            starTextView = (TextView) itemView.findViewById(R.id.tv_star);
            starscoreTextView = (TextView) itemView.findViewById(R.id.tv_starscore);
            evaluationTextView = (TextView) itemView.findViewById(R.id.tv_evaluation);
            selfintroductionTextView = (TextView) itemView.findViewById(R.id.tv_self_introduction);
            shopImageView = (ImageView) itemView.findViewById(R.id.iv_shop);
            favorateImageView = (ImageView) itemView.findViewById(R.id.iv_favorate);
        }
    }

    private List<ContactFishOpen> mContacts;

    public FishOpenAdapter(List<ContactFishOpen> contacts) {
        mContacts = contacts;
        glide = null;
    }

    public FishOpenAdapter(RequestManager glide, List<ContactFishOpen> contacts) {
        mContacts = contacts;
        this.glide = glide;
    }

    @Override
    public FishOpenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_fishopen, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FishOpenAdapter.ViewHolder holder, int position) {
        ContactFishOpen contactFishOpen = mContacts.get(position);

        TextView tv_shopnum = holder.shopnumTextView;
        TextView tv_shopname = holder.shopnameTextView;
        TextView tv_star = holder.starTextView;
        TextView tv_starscore = holder.starscoreTextView;
        TextView tv_evaluation = holder.evaluationTextView;
        TextView tv_selfintroduction = holder.selfintroductionTextView;
        ImageView iv_shop = holder.shopImageView;
        ImageView iv_favorate = holder.favorateImageView;

        tv_shopnum.setText(contactFishOpen.getmShopnum());
        tv_shopname.setText(contactFishOpen.getmShopname());
        tv_star.setText(contactFishOpen.getmStar());
        tv_starscore.setText(Double.toString(contactFishOpen.getmStarscore()));
        tv_evaluation.setText(contactFishOpen.getmEvaluation());
        tv_selfintroduction.setText(contactFishOpen.getmSelfintroduction());
        if (glide == null) {
            iv_shop.setImageResource(contactFishOpen.getmImage());
        } else {
            glide.load(contactFishOpen.get_mImage()).into(iv_shop);
        }
        iv_favorate.setImageResource(contactFishOpen.getmFImage());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}