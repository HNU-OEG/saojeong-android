package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.ContactShopDetail;

import java.util.List;

public class ShopDetailAdapter extends RecyclerView.Adapter<ShopDetailAdapter.ViewHolder> {

    // 번호가 하나일때는 변하도록...
    // 지금은 하나여도 더미 데이터가 나옴
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView timeTextView;
        public Button phonen1Button;
        public Button phonen2Button;


        public ViewHolder(View itemView) {
            super(itemView);
            timeTextView = (TextView) itemView.findViewById(R.id.tv_bh);
            phonen1Button = (Button) itemView.findViewById(R.id.btn_phonenum1);
            phonen2Button = (Button) itemView.findViewById(R.id.btn_phonenum2);
        }
    }

    private List<ContactShopDetail> mContacts;

    public ShopDetailAdapter(List<ContactShopDetail> contacts) {
        mContacts = contacts;
    }

    @Override
    public ShopDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_shop_detail, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShopDetailAdapter.ViewHolder holder, int position) {
        ContactShopDetail contactShopDetail = mContacts.get(position);

        TextView tv_bh = holder.timeTextView;
        Button btn_phonen1 = holder.phonen1Button;

        tv_bh.setText(contactShopDetail.getmTime());
        btn_phonen1.setText(contactShopDetail.getmPhoneN1());
        if (contactShopDetail.getmPhoneN2() != null) {
            Button btn_phonen2 = holder.phonen2Button;
            btn_phonen2.setText(contactShopDetail.getmPhoneN2());
        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}