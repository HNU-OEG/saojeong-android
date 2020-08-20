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
import com.example.saojeong.model.ContactFruitDetail;

import java.util.List;

public class FruitDetailAdapter extends RecyclerView.Adapter<FruitDetailAdapter.ViewHolder> {


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
        Button btn_phonen1 = holder.phonen1Button;
        Button btn_phonen2 = holder.phonen2Button;

        tv_bh.setText(contactFruitDetail.getmTime());
        btn_phonen1.setText(contactFruitDetail.getmPhoneN1());
        btn_phonen2.setText(contactFruitDetail.getmPhoneN2());

    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}