package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.ContactShopSellList;

import java.util.List;

public class ShopSellListAdapter extends RecyclerView.Adapter<ShopSellListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemnameTextView;
        public TextView weight1TextView;
        public TextView weight2TextView;
        public TextView weight3TextView;
        public TextView price1TextView;
        public TextView price2TextView;
        public ImageView shopImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemnameTextView = (TextView) itemView.findViewById(R.id.tv_shopname);
            weight1TextView = (TextView) itemView.findViewById(R.id.tv_weight1);
            weight2TextView = (TextView) itemView.findViewById(R.id.tv_weight2);
            weight3TextView = (TextView) itemView.findViewById(R.id.tv_weight3);
            price1TextView = (TextView) itemView.findViewById(R.id.tv_price_shop_shop1);
            price2TextView = (TextView) itemView.findViewById(R.id.tv_price_shop_shop2);
            shopImageView = (ImageView) itemView.findViewById(R.id.iv_shop);
        }
    }

    private List<ContactShopSellList> mContacts;

    public ShopSellListAdapter(List<ContactShopSellList> contacts) {
        mContacts = contacts;
    }

    @Override
    public ShopSellListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_shop_selllist, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShopSellListAdapter.ViewHolder holder, int position) {
        ContactShopSellList contactShopSellList = mContacts.get(position);

        TextView tv_itemname = holder.itemnameTextView;
        TextView tv_weight1 = holder.weight1TextView;
        TextView tv_weight2 = holder.weight2TextView;
        TextView tv_weight3 = holder.weight3TextView;
        TextView tv_price1 = holder.price1TextView;
        TextView tv_price2 = holder.price2TextView;
        ImageView iv_SellList = holder.shopImageView;

        tv_itemname.setText(contactShopSellList.getmItemname());
        tv_weight1.setText(contactShopSellList.getmWeight1());
        tv_weight2.setText(contactShopSellList.getmWeight2());
        tv_weight3.setText(contactShopSellList.getmWeight3());
        tv_price1.setText(contactShopSellList.getmPrice1());
        tv_price2.setText(contactShopSellList.getmPrice2());
        iv_SellList.setImageResource(contactShopSellList.getmIFmage());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}