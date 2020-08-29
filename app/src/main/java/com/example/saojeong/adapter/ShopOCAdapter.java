package com.example.saojeong.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.saojeong.GlideModule;
import com.example.saojeong.R;
import com.example.saojeong.model.ContactShopOC;
import com.example.saojeong.model.OnItemClickListener;

import java.util.List;

//public class ShopOCAdapter extends RecyclerView.Adapter<ShopOCAdapter.ViewHolder> implements OnItemClickListener<ShopOCAdapter.ViewHolder> {
public class ShopOCAdapter extends RecyclerView.Adapter<ShopOCAdapter.ViewHolder> {

    private final RequestManager glide;

    public OnItemClickListener<ShopOCAdapter.ViewHolder> mListener = null;

    public void setOnItemClickListener(OnItemClickListener<ShopOCAdapter.ViewHolder> mListener) {
        this.mListener = mListener;
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

        // Store ID 추가
        public int storeId;

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
                    if (!isNull(mListener)) {
                        mListener.onItemClick(ShopOCAdapter.ViewHolder.this);
                    }
                }

                boolean isNull(OnItemClickListener<ShopOCAdapter.ViewHolder> l) {
                    return l == null;
                }
            });

        }
    }

    private List<ContactShopOC> mContacts;

    public ShopOCAdapter(List<ContactShopOC> contacts) {
        mContacts = contacts;
        glide = null;
    }

    public ShopOCAdapter(RequestManager glide, List<ContactShopOC> contacts) {
        mContacts = contacts;
        this.glide = glide;
    }

    @Override
    public ShopOCAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_shop_oc, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ShopOCAdapter.ViewHolder holder, int position) {
        ContactShopOC contactShopOC = mContacts.get(position);

        TextView tv_shopnum = holder.shopnumTextView;
        TextView tv_shopname = holder.shopnameTextView;
        TextView tv_star = holder.starTextView;
        TextView tv_starscore = holder.starscoreTextView;
        TextView tv_evaluation = holder.evaluationTextView;
        TextView tv_selfintroduction = holder.selfintroductionTextView;
        ImageView iv_shop = holder.shopImageView;
        ImageView iv_favorate = holder.favorateImageView;

        // Store ID 바인딩
        holder.storeId = contactShopOC.getMShopId();
        tv_shopnum.setText(contactShopOC.getMShopnum());
        tv_shopname.setText(contactShopOC.getMShopname());
        tv_star.setText(contactShopOC.getMStar());
        tv_starscore.setText(Double.toString(contactShopOC.getMStarscore()));
        tv_evaluation.setText(contactShopOC.getMEvaluation());
        tv_selfintroduction.setText(contactShopOC.getMSelfintroduction());
        if (glide == null) {
            iv_shop.setImageResource(contactShopOC.getMImage());

        } else {
            glide.load(contactShopOC.get_mImage())
                    .transform(GlideModule.getCenterCropAndRoundedCorner(50))
                    .into(iv_shop);
        }

        if (contactShopOC.isMLike()) {
            iv_favorate.setImageResource(contactShopOC.getMFImage());
        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}