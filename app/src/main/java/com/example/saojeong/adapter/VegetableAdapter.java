package com.example.saojeong.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.saojeong.R;
import com.example.saojeong.model.ContactVegetable;

import java.util.List;

public class VegetableAdapter extends RecyclerView.Adapter<VegetableAdapter.ViewHolder> {
    private final RequestManager glide;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView VegetableTextView;
        public ImageView VegetableImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            VegetableTextView = (TextView) itemView.findViewById(R.id.tv_vegetable);
            VegetableImageView = (ImageView) itemView.findViewById(R.id.iv_vegetable);
        }
    }

    private List<ContactVegetable> mContacts;

    public VegetableAdapter(List<ContactVegetable> contacts) {
        mContacts = contacts;
        this.glide = null;
    }

    public VegetableAdapter(RequestManager glide, List<ContactVegetable> contacts) {
        mContacts = contacts;
        this.glide = glide;
    }

    @Override
    public VegetableAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_vegetable, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(VegetableAdapter.ViewHolder holder, int position) {
        ContactVegetable contactSeason = mContacts.get(position);

        TextView tv_vegetable = holder.VegetableTextView;
        tv_vegetable.setText(contactSeason.getmVegetable());

        ImageView iv_vegetable = holder.VegetableImageView;
        if (glide == null) {
            iv_vegetable.setImageResource(contactSeason.getmVeImage());
        } else {
            glide.load(contactSeason.get_mVeImage()).into(iv_vegetable);
        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}