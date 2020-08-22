package com.example.saojeong.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.ContactShopStarScore;

import java.util.List;

public class ShopStarScoreAdapter extends RecyclerView.Adapter<ShopStarScoreAdapter.ViewHolder> {

    float ratingBar_rating;
    private List<ContactShopStarScore> mContacts;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView questionTextView;
        public RatingBar ratingBar_starscore;

        public ViewHolder(View itemView) {
            super(itemView);
            questionTextView = (TextView) itemView.findViewById(R.id.tv_shop_starscore);
            ratingBar_starscore = (RatingBar) itemView.findViewById(R.id.ratingBar_starscore);
        }
    }

    public List<ContactShopStarScore> getmContacts() {
        return mContacts;
    }

    public ShopStarScoreAdapter(List<ContactShopStarScore> contacts) {
        mContacts = contacts;
    }

    @Override
    public ShopStarScoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_shop_starscore, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShopStarScoreAdapter.ViewHolder holder, int position) {
        ContactShopStarScore contactShopStarScore = mContacts.get(position);

        TextView tv_shop_starscore = holder.questionTextView;
        RatingBar ratingBar = holder.ratingBar_starscore;

        tv_shop_starscore.setText(contactShopStarScore.getmQuestion());

        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#f67043"), PorterDuff.Mode.SRC_ATOP);
        //레이팅 바 이벤트 처리
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar_rating = rating;
                tv_shop_starscore.setText(contactShopStarScore.getmQuestion()+" | " + ratingBar_rating + "점");
                contactShopStarScore.setmRating(ratingBar_rating);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}