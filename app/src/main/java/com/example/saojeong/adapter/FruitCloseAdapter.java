package com.example.saojeong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.ContactFruitClose;
import com.example.saojeong.CommunityReadActivity;
import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.fragment.FruitShopFragment;
import com.example.saojeong.model.ContactFruitOpen;
import com.example.saojeong.model.OnItemClickListener;

import java.util.List;

public class FruitCloseAdapter extends RecyclerView.Adapter<FruitCloseAdapter.ViewHolder> implements OnItemClickListener {

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
        public TextView shopnumTextView;
        public TextView shopnameTextView;
        public TextView starTextView;
        public TextView starscoreTextView;
        public TextView evaluationTextView;
        public TextView selfintroductionTextView;
        public ImageView shopImageView;
        public ImageView favorateImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            shopnumTextView = (TextView) itemView.findViewById(R.id.tv_shopnum);
            shopnameTextView = (TextView) itemView.findViewById(R.id.tv_shopname);
            starTextView = (TextView) itemView.findViewById(R.id.tv_star);
            starscoreTextView = (TextView) itemView.findViewById(R.id.tv_starscore);
            evaluationTextView = (TextView) itemView.findViewById(R.id.tv_evaluation);
            selfintroductionTextView = (TextView) itemView.findViewById(R.id.tv_self_introduction);
            shopImageView = (ImageView) itemView.findViewById(R.id.iv_shop);
            favorateImageView = (ImageView) itemView.findViewById(R.id.iv_favorate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }
    }

    private List<ContactFruitClose> mContacts;

    public FruitCloseAdapter(List<ContactFruitClose> contacts) {
        mContacts = contacts;
    }

    @Override
    public FruitCloseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_fruitclose, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FruitCloseAdapter.ViewHolder holder, int position) {
        ContactFruitClose contactFruitClose = mContacts.get(position);

        TextView tv_shopnum = holder.shopnumTextView;
        TextView tv_shopname = holder.shopnameTextView;
        TextView tv_star = holder.starTextView;
        TextView tv_starscore = holder.starscoreTextView;
        TextView tv_evaluation = holder.evaluationTextView;
        TextView tv_selfintroduction = holder.selfintroductionTextView;
        ImageView iv_shop = holder.shopImageView;
        ImageView iv_favorate = holder.favorateImageView;

        tv_shopnum.setText(contactFruitClose.getmShopnum());
        tv_shopname.setText(contactFruitClose.getmShopname());
        tv_star.setText(contactFruitClose.getmStar());
        tv_starscore.setText(Double.toString(contactFruitClose.getmStarscore()));
        tv_evaluation.setText(contactFruitClose.getmEvaluation());
        tv_selfintroduction.setText(contactFruitClose.getmSelfintroduction());
        iv_shop.setImageResource(contactFruitClose.getmImage());
        iv_favorate.setImageResource(contactFruitClose.getmFImage());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}