package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.ContactFruitOpen;


import java.util.List;

public class FruitOpenAdapter extends RecyclerView.Adapter<FruitOpenAdapter.ViewHolder> {

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

    private List<ContactFruitOpen> mContacts;

    public FruitOpenAdapter(List<ContactFruitOpen> contacts) {
        mContacts = contacts;
    }

    @Override
    public FruitOpenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_fruitopen, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FruitOpenAdapter.ViewHolder holder, int position) {
        ContactFruitOpen contactFruitOpen = mContacts.get(position);

        TextView tv_shopnum = holder.shopnumTextView;
        TextView tv_shopname = holder.shopnameTextView;
        TextView tv_star = holder.starTextView;
        TextView tv_starscore = holder.starscoreTextView;
        TextView tv_evaluation = holder.evaluationTextView;
        TextView tv_selfintroduction = holder.selfintroductionTextView;
        ImageView iv_shop = holder.shopImageView;
        ImageView iv_favorate = holder.favorateImageView;

        tv_shopnum.setText(contactFruitOpen.getmShopnum());
        tv_shopname.setText(contactFruitOpen.getmShopname());
        tv_star.setText(contactFruitOpen.getmStar());
        tv_starscore.setText(Double.toString(contactFruitOpen.getmStarscore()));
        tv_evaluation.setText(contactFruitOpen.getmEvaluation());
        tv_selfintroduction.setText(contactFruitOpen.getmSelfintroduction());
        iv_shop.setImageResource(contactFruitOpen.getmImage());
        iv_favorate.setImageResource(contactFruitOpen.getmFImage());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}