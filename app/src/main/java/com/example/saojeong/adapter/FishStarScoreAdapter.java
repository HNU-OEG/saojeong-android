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
import com.example.saojeong.model.ContactFishStarScore;

import java.util.List;

public class FishStarScoreAdapter extends RecyclerView.Adapter<FishStarScoreAdapter.ViewHolder> {

    float ratingBar_rating;
    private List<ContactFishStarScore> mContacts;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView questionTextView;
        public RatingBar ratingBar_starscore;

        public ViewHolder(View itemView) {
            super(itemView);
            questionTextView = (TextView) itemView.findViewById(R.id.tv_fish_starscore);
            ratingBar_starscore = (RatingBar) itemView.findViewById(R.id.ratingBar_starscore);
        }
    }

    public List<ContactFishStarScore> getmContacts() {
        return mContacts;
    }

    public FishStarScoreAdapter(List<ContactFishStarScore> contacts) {
        mContacts = contacts;
    }

    @Override
    public FishStarScoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_fish_starscore, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FishStarScoreAdapter.ViewHolder holder, int position) {
        ContactFishStarScore contactFishStarScore = mContacts.get(position);

        TextView tv_fish_starscore = holder.questionTextView;
        RatingBar ratingBar = holder.ratingBar_starscore;

        tv_fish_starscore.setText(contactFishStarScore.getmQuestion());

        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#f67043"), PorterDuff.Mode.SRC_ATOP);
        //레이팅 바 이벤트 처리
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar_rating = rating;
                tv_fish_starscore.setText(contactFishStarScore.getmQuestion()+" | " + ratingBar_rating + "점");
                contactFishStarScore.setmRating(ratingBar_rating);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}