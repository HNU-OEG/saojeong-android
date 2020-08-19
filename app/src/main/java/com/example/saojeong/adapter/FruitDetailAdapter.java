package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.ContactFruitDetail;

import java.util.List;

public class FruitDetailAdapter extends RecyclerView.Adapter<FruitDetailAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView timeTextView;
        public TextView phonen1TextView;
        public TextView phonen2TextView;


        public ViewHolder(View itemView) {
            super(itemView);
            timeTextView = (TextView) itemView.findViewById(R.id.tv_bh);
            phonen1TextView = (TextView) itemView.findViewById(R.id.tv_phonenum1);
            phonen2TextView = (TextView) itemView.findViewById(R.id.tv_phonenum2);
        }
    }

    private List<ContactFruitDetail> mContacts;

    public FruitDetailAdapter(List<ContactFruitDetail> contacts) {
        mContacts = contacts;
    }

    @Override
    public FruitDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_fruit_detail, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FruitDetailAdapter.ViewHolder holder, int position) {
        ContactFruitDetail contactFruitDetail = mContacts.get(position);

        TextView tv_bh = holder.timeTextView;
        TextView tv_phonen1 = holder.phonen1TextView;
        TextView tv_phonen2 = holder.phonen2TextView;

        tv_bh.setText(contactFruitDetail.getmTime());
        tv_phonen1.setText(contactFruitDetail.getmPhoneN1());
        tv_phonen2.setText(contactFruitDetail.getmPhoneN2());

    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}