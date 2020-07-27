package com.example.saojeong.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.Contact;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView shopnumTextView;
        public TextView shopnameTextView;
        public TextView starTextView;
        public TextView starscoreTextView;
        public TextView evaluationTextView;
        public ImageView shopImageView;
        public ImageView favorateImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            shopnumTextView = (TextView) itemView.findViewById(R.id.tv_shopnum);
            shopnameTextView = (TextView) itemView.findViewById(R.id.tv_shopname);
            starTextView = (TextView) itemView.findViewById(R.id.tv_star);
            starscoreTextView = (TextView) itemView.findViewById(R.id.tv_starscore);
            evaluationTextView = (TextView) itemView.findViewById(R.id.tv_evaluation);
            shopImageView = (ImageView) itemView.findViewById(R.id.iv_shop);
            favorateImageView = (ImageView) itemView.findViewById(R.id.iv_favorate);
        }
    }

    private List<Contact> mContacts;

    public ContactsAdapter(List<Contact> contacts) {
        mContacts = contacts;
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        Contact contact = mContacts.get(position);

        TextView tv_shopnum = holder.shopnumTextView;
        tv_shopnum.setText(contact.getmShopnum());
        TextView tv_shopname = holder.shopnameTextView;
        tv_shopname.setText(contact.getmShopname());
        TextView tv_star = holder.starTextView;
        tv_star.setText(contact.getmStar());
        TextView tv_starscore = holder.starscoreTextView;
        tv_starscore.setText(contact.getmStarscore());
        TextView tv_evaluation = holder.evaluationTextView;
        tv_evaluation.setText(contact.getmEvaluation());

        ImageView iv_shop = holder.shopImageView;
        iv_shop.setImageResource(contact.getmImage());
        ImageView iv_favorate = holder.favorateImageView;
        iv_favorate.setImageResource(contact.getmFImage());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}