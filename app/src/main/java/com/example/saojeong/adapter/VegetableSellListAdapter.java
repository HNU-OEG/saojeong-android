package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.ContactVegetableSellList;

import java.util.List;

public class VegetableSellListAdapter extends RecyclerView.Adapter<VegetableSellListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemnameTextView;
        public TextView weight1TextView;
        public TextView weight2TextView;
        public TextView weight3TextView;
        public TextView price1TextView;
        public TextView price2TextView;
        public ImageView vegetableImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemnameTextView = (TextView) itemView.findViewById(R.id.tv_vegetablename);
            weight1TextView = (TextView) itemView.findViewById(R.id.tv_weight1);
            weight2TextView = (TextView) itemView.findViewById(R.id.tv_weight2);
            weight3TextView = (TextView) itemView.findViewById(R.id.tv_weight3);
            price1TextView = (TextView) itemView.findViewById(R.id.tv_price_vegetable_shop1);
            price2TextView = (TextView) itemView.findViewById(R.id.tv_price_vegetable_shop2);
            vegetableImageView = (ImageView) itemView.findViewById(R.id.iv_shop);
        }
    }

    private List<ContactVegetableSellList> mContacts;

    public VegetableSellListAdapter(List<ContactVegetableSellList> contacts) {
        mContacts = contacts;
    }

    @Override
    public VegetableSellListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_vegetable_selllist, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VegetableSellListAdapter.ViewHolder holder, int position) {
        ContactVegetableSellList contactVegetableSellList = mContacts.get(position);

        TextView tv_itemname = holder.itemnameTextView;
        TextView tv_weight1 = holder.weight1TextView;
        TextView tv_weight2 = holder.weight2TextView;
        TextView tv_weight3 = holder.weight3TextView;
        TextView tv_price1 = holder.price1TextView;
        TextView tv_price2 = holder.price2TextView;
        ImageView iv_SellList = holder.vegetableImageView;

        tv_itemname.setText(contactVegetableSellList.getmItemname());
        tv_weight1.setText(contactVegetableSellList.getmWeight1());
        tv_weight2.setText(contactVegetableSellList.getmWeight2());
        tv_weight3.setText(contactVegetableSellList.getmWeight3());
        tv_price1.setText(contactVegetableSellList.getmPrice1());
        tv_price2.setText(contactVegetableSellList.getmPrice2());
        iv_SellList.setImageResource(contactVegetableSellList.getmIFmage());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}