package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.ContactFruit;


import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView FruitTextView;
        public ImageView FruitImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            FruitTextView = (TextView) itemView.findViewById(R.id.tv_fruit);
            FruitImageView = (ImageView) itemView.findViewById(R.id.iv_fruit);
        }
    }

    private List<ContactFruit> mContacts;

    public FruitAdapter(List<ContactFruit> contacts) {
        mContacts = contacts;
    }

    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_fruit, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FruitAdapter.ViewHolder holder, int position) {
        ContactFruit contactSeason = mContacts.get(position);

        TextView tv_fruit = holder.FruitTextView;
        tv_fruit.setText(contactSeason.getmFruit());

        ImageView iv_fruit = holder.FruitImageView;
        iv_fruit.setImageResource(contactSeason.getmFrImage());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}