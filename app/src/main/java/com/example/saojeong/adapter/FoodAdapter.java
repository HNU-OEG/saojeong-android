package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.ContactFood;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView FoodTextView;
        public ImageView FoodImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            FoodTextView = (TextView) itemView.findViewById(R.id.tv_food);
            FoodImageView = (ImageView) itemView.findViewById(R.id.iv_food);
        }
    }

    private List<ContactFood> mContacts;

    public FoodAdapter(List<ContactFood> contacts) {
        mContacts = contacts;
    }

    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_food, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FoodAdapter.ViewHolder holder, int position) {
        ContactFood contactSeason = mContacts.get(position);

        TextView tv_food = holder.FoodTextView;
        tv_food.setText(contactSeason.getmFood());

        ImageView iv_food = holder.FoodImageView;
        iv_food.setImageResource(contactSeason.getmFrImage());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}