package com.example.saojeong.adapter;

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
import com.example.saojeong.model.StarStore;

import java.util.ArrayList;

public class StarStoreAdapter extends RecyclerView.Adapter<StarStoreAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView codeStore;
        public TextView nameStore;
        public LinearLayout stars;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.codeStore = itemView.findViewById(R.id.codeStore);
            this.nameStore = itemView.findViewById(R.id.nameStore);
            this.stars = itemView.findViewById(R.id.stars);
        }
    }

    private ArrayList<StarStore> mStarStore;

    public StarStoreAdapter(ArrayList<StarStore> mStarStore) {
        this.mStarStore = mStarStore;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_starstore, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StarStore starStore = mStarStore.get(position);

        TextView codeStore = holder.codeStore;
        TextView nameStore = holder.nameStore;
        LinearLayout stars = holder.stars;

        int rate = starStore.getmRateStore();
        int i = 0;

        codeStore.setText(starStore.getmCodeStore()+"번");
        nameStore.setText(starStore.getmNameStore());

        while (i < 5) {
            ImageView star = new ImageView(stars.getContext());

            if (i < rate) {
                star.setImageResource(R.drawable.home);
                stars.addView(star);
            } else {
                star.setImageResource(R.drawable.price);
                stars.addView(star);
            }
            i+=1;
        }

    }

    @Override
    public int getItemCount() {
        return mStarStore.size();
    }
}
