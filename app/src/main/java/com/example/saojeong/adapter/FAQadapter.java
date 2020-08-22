package com.example.saojeong.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.FAQ;

import java.util.ArrayList;

public class FAQadapter extends RecyclerView.Adapter<FAQadapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView tv_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.tv_faq_title);
            this.tv_content = itemView.findViewById(R.id.tv_faq_content);
        }

    }

    private ArrayList<FAQ> mFAQList;

    public FAQadapter(ArrayList<FAQ> mFAQ) { this.mFAQList = mFAQ; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_faq, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FAQ mFAQ = mFAQList.get(position);

        TextView title = holder.title;
        TextView tv_content = holder.tv_content;

        title.setText(mFAQ.getmTitle());
        tv_content.setText(mFAQ.getmContext());
    }

    @Override
    public int getItemCount() { return mFAQList.size(); }
}
