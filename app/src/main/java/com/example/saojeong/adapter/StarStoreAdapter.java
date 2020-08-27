package com.example.saojeong.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.ContactShopOC;
import com.example.saojeong.model.StarStore;

import java.util.ArrayList;
import java.util.List;

public class StarStoreAdapter extends RecyclerView.Adapter<StarStoreAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView codeStore;
        public TextView nameStore;
        public TextView rate;
        public LinearLayout stars;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.codeStore = itemView.findViewById(R.id.codeStore);
            this.nameStore = itemView.findViewById(R.id.nameStore);
            this.stars = itemView.findViewById(R.id.stars);
            this.rate = itemView.findViewById(R.id.rate);
        }
    }

    private List<ContactShopOC> mStarStore;

    public StarStoreAdapter(List<ContactShopOC> mStarStore) {
        this.mStarStore = mStarStore;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_starstore, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactShopOC starStore = mStarStore.get(position);

        TextView codeStore = holder.codeStore;
        TextView nameStore = holder.nameStore;
        TextView rateStore = holder.rate;
        LinearLayout stars = holder.stars;

        Double rate = starStore.getMStarscore();

        codeStore.setText(starStore.getMShopnum());
        nameStore.setText(starStore.getMShopname());
        rateStore.setText(rate.toString() + "점");

        markingStar(rate.floatValue(), stars);
    }

    @Override
    public int getItemCount() {
        return mStarStore.size();
    }

    public void markingStar(float scoreOrigin, LinearLayout stars) {
        int i = 0;
        int integerScore = (int) scoreOrigin;

        float hasPoint = scoreOrigin - integerScore;

        while (i < 5) {
            ImageView star = new ImageView(stars.getContext());

            if (i < integerScore) {
                star.setImageResource(R.drawable.star_fill);

            } else if (i == integerScore && i != 0) {   //마지막 인덱스에서 수수부분 값에 따라 이미지 변화
                if (hasPoint == 0) star.setImageResource(R.drawable.star_fill);
                else if (hasPoint < 0.5) star.setImageResource(R.drawable.star_empty);
                else if (hasPoint >= 0.5) star.setImageResource(R.drawable.star_half);

            } else {
                star.setImageResource(R.drawable.star_empty);
            }
            stars.addView(star);
            i+=1;
        }
    }
}
