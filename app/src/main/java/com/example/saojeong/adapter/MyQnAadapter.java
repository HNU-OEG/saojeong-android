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

import java.util.ArrayList;

public class MyQnAadapter extends RecyclerView.Adapter<MyQnAadapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView status;
        public TextView date;
        public TextView tv_content;
        public TextView comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.tv_QnA_title);
            this.status = itemView.findViewById(R.id.tv_QnA_status);
            this.date = itemView.findViewById(R.id.tv_QnA_date);
            this.tv_content = itemView.findViewById(R.id.tv_QnA_content);
            this.comment = itemView.findViewById(R.id.tv_QnA_comment);
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
        TextView tv_content = holder.tv_content;
        TextView comment = holder.comment;

        title.setText(myQnA.getmTitle());
        status.setText(myQnA.getmStatus());
        date.setText(myQnA.getmDate());
        tv_content.setText(myQnA.getmContent());
        comment.setText(myQnA.getmComment());
    }

    @Override
    public int getItemCount() { return mMyQnA.size(); }
}
