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
import com.example.saojeong.model.ContactFishClose;

import java.util.List;

public class FishCloseAdapter extends RecyclerView.Adapter<FishCloseAdapter.ViewHolder> {
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

    public FishCloseAdapter(List<ContactFishClose> contacts) {
        mContacts = contacts;
        glide = null;
    }

    public FishCloseAdapter(RequestManager glide, List<ContactFishClose> contacts) {
        mContacts = contacts;
        this.glide = glide;
    }

    private List<ContactFishClose> mContacts;

    @Override
    public FishCloseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_fishclose, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FishCloseAdapter.ViewHolder holder, int position) {
        ContactFishClose contactFishClose = mContacts.get(position);

        TextView tv_shopnum = holder.shopnumTextView;
        TextView tv_shopname = holder.shopnameTextView;
        TextView tv_star = holder.starTextView;
        TextView tv_starscore = holder.starscoreTextView;
        TextView tv_evaluation = holder.evaluationTextView;
        TextView tv_selfintroduction = holder.selfintroductionTextView;
        ImageView iv_shop = holder.shopImageView;
        ImageView iv_favorate = holder.favorateImageView;

        tv_shopnum.setText(contactFishClose.getmShopnum());
        tv_shopname.setText(contactFishClose.getmShopname());
        tv_star.setText(contactFishClose.getmStar());
        tv_starscore.setText(Double.toString(contactFishClose.getmStarscore()));
        tv_evaluation.setText(contactFishClose.getmEvaluation());
        tv_selfintroduction.setText(contactFishClose.getmSelfintroduction());
        if (glide == null) {
            iv_shop.setImageResource(contactFishClose.getmImage());
        } else {
            glide.load(contactFishClose.get_mImage()).into(iv_shop);
        }
        iv_favorate.setImageResource(contactFishClose.getmFImage());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}