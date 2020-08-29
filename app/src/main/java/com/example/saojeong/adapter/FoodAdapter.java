package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.saojeong.GlideModule;
import com.example.saojeong.R;
import com.example.saojeong.model.ContactFood;
import com.example.saojeong.model.OnItemClickListener;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{
    private RequestManager glide;

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
        glide = null;
    }

    public FoodAdapter(RequestManager glide,List<ContactFood> contacts) {
        mContacts = contacts;
        this.glide = glide;
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
        tv_food.setText(contactSeason.getMFood());

        ImageView iv_food = holder.FoodImageView;
        if (glide == null) {
            iv_food.setImageResource(contactSeason.getMFrImage());
        } else {
            glide.load(contactSeason.get_mFrImage())
                    .transform(GlideModule.getCenterCropAndRoundedCorner(50))
                    .into(iv_food);
        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}