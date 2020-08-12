package com.example.saojeong.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.CommunityReadActivity;
import com.example.saojeong.R;
import com.example.saojeong.fragment.FruitShopFragment;
import com.example.saojeong.model.ContactFruitShop;


import java.util.List;

public class FruitSellListAdapter extends RecyclerView.Adapter<FruitSellListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemnameTextView;
        public TextView weight1TextView;
        public TextView weight2TextView;
        public TextView weight3TextView;
        public TextView price1TextView;
        public TextView price2TextView;
        public ImageView fruitImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemnameTextView = (TextView) itemView.findViewById(R.id.tv_fruitname);
            weight1TextView = (TextView) itemView.findViewById(R.id.tv_weight1);
            weight2TextView = (TextView) itemView.findViewById(R.id.tv_weight2);
            weight3TextView = (TextView) itemView.findViewById(R.id.tv_weight3);
            price1TextView = (TextView) itemView.findViewById(R.id.tv_price_fruit_shop1);
            price2TextView = (TextView) itemView.findViewById(R.id.tv_price_fruit_shop2);
            fruitImageView = (ImageView) itemView.findViewById(R.id.iv_shop);
        }
    }

    private List<ContactFruitShop> mContacts;

    public FruitSellListAdapter(List<ContactFruitShop> contacts) {
        mContacts = contacts;
    }

    @Override
    public FruitSellListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_fruit_selllist, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FruitSellListAdapter.ViewHolder holder, int position) {
        ContactFruitShop contactFruitShop = mContacts.get(position);

        TextView tv_itemname = holder.itemnameTextView;
        TextView tv_weight1 = holder.weight1TextView;
        TextView tv_weight2 = holder.weight2TextView;
        TextView tv_weight3 = holder.weight3TextView;
        TextView tv_price1 = holder.price1TextView;
        TextView tv_price2 = holder.price2TextView;
        ImageView iv_shop = holder.fruitImageView;

        tv_itemname.setText(contactFruitShop.getmItemname());
        tv_weight1.setText(contactFruitShop.getmWeight1());
        tv_weight2.setText(contactFruitShop.getmWeight2());
        tv_weight3.setText(contactFruitShop.getmWeight3());
        tv_price1.setText(contactFruitShop.getmPrice1());
        tv_price2.setText(contactFruitShop.getmPrice2());
        iv_shop.setImageResource(contactFruitShop.getmIFmage());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}