package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.ContactVegetableOpen;

import java.util.List;

public class VegetableOpenAdapter extends RecyclerView.Adapter<VegetableOpenAdapter.ViewHolder> {

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

    private List<ContactVegetableOpen> mContacts;

    public VegetableOpenAdapter(List<ContactVegetableOpen> contacts) {
        mContacts = contacts;
    }

    @Override
    public VegetableOpenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_vegetableopen, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VegetableOpenAdapter.ViewHolder holder, int position) {
        ContactVegetableOpen contactVegetableOpen = mContacts.get(position);

        TextView tv_shopnum = holder.shopnumTextView;
        TextView tv_shopname = holder.shopnameTextView;
        TextView tv_star = holder.starTextView;
        TextView tv_starscore = holder.starscoreTextView;
        TextView tv_evaluation = holder.evaluationTextView;
        TextView tv_selfintroduction = holder.selfintroductionTextView;
        ImageView iv_shop = holder.shopImageView;
        ImageView iv_favorate = holder.favorateImageView;

        tv_shopnum.setText(contactVegetableOpen.getmShopnum());
        tv_shopname.setText(contactVegetableOpen.getmShopname());
        tv_star.setText(contactVegetableOpen.getmStar());
        tv_starscore.setText(Double.toString(contactVegetableOpen.getmStarscore()));
        tv_evaluation.setText(contactVegetableOpen.getmEvaluation());
        tv_selfintroduction.setText(contactVegetableOpen.getmSelfintroduction());
        iv_shop.setImageResource(contactVegetableOpen.getmImage());
        iv_favorate.setImageResource(contactVegetableOpen.getmFImage());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}