package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.MyQnA;
import com.example.saojeong.model.OnItemClickListener;

import java.util.ArrayList;

public class MyQnAadapter extends RecyclerView.Adapter<MyQnAadapter.ViewHolder> implements OnItemClickListener<MyQnAadapter.ViewHolder> {

    OnItemClickListener listener;

    public void setOnItemClicklistener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null) {
            listener.onItemClick(holder,view,position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView status;
        public TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.tv_QnA_title_list);
            this.status = itemView.findViewById(R.id.tv_QnA_status_list);
            this.date = itemView.findViewById(R.id.tv_QnA_date_list);

            itemView.setOnClickListener((v) -> {
                int position = getAdapterPosition();
                if(listener != null) {
                    listener.onItemClick(ViewHolder.this, v, position);
                }
            });
        }
    }

    private ArrayList<MyQnA> mMyQnA;

    public MyQnAadapter(ArrayList<MyQnA> mMyQnA) { this.mMyQnA = mMyQnA; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_myqna, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyQnA myQnA = mMyQnA.get(position);

        TextView title = holder.title;
        TextView status = holder.status;
        TextView date = holder.date;

        title.setText(myQnA.getmTitle());
        status.setText(myQnA.getmStatus());
        date.setText(myQnA.getmDate());
    }

    @Override
    public int getItemCount() { return mMyQnA.size(); }
}
