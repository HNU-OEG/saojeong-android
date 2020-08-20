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
import com.example.saojeong.model.ContactFruitScore;

import java.util.List;

public class FruitScoreAdapter extends RecyclerView.Adapter<FruitScoreAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView evaluateTextView;
        public TextView kindscoreTextView;
        public TextView itemscoreTextView;
        public TextView pricescoreTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            evaluateTextView = (TextView) itemView.findViewById(R.id.tv_fruit_evaluate);
            kindscoreTextView = (TextView) itemView.findViewById(R.id.tv_fruit_kindscore);
            itemscoreTextView = (TextView) itemView.findViewById(R.id.tv_fruit_itemscore);
            pricescoreTextView = (TextView) itemView.findViewById(R.id.tv_fruit_pricescore);
        }
    }

    private List<ContactFruitScore> mContacts;

    public FruitScoreAdapter(List<ContactFruitScore> contacts) {
        mContacts = contacts;
    }

    @Override
    public FruitScoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_fruit_score, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FruitScoreAdapter.ViewHolder holder, int position) {
        ContactFruitScore contactFruitScore = mContacts.get(position);

        TextView tv_evaluate = holder.evaluateTextView;
        TextView tv_kindscore = holder.kindscoreTextView;
        TextView tv_itemscore = holder.itemscoreTextView;
        TextView tv_pricescore = holder.pricescoreTextView;

        tv_evaluate.setText(contactFruitScore.getmEvaluate());
        tv_kindscore.setText(contactFruitScore.getmKindscore());
        tv_itemscore.setText(contactFruitScore.getmItemscore());
        tv_pricescore.setText(contactFruitScore.getmPricescore());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}