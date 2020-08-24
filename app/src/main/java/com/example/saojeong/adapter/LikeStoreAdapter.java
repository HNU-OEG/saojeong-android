package com.example.saojeong.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.saojeong.R;
import com.example.saojeong.model.ContactShopOC;
import com.example.saojeong.model.LikeStore;
import com.example.saojeong.model.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class LikeStoreAdapter extends RecyclerView.Adapter<LikeStoreAdapter.ViewHolder> {
    private final RequestManager glide;


    /**
     * 이전 버전에서는 OnItemClickListener를 각 Adapter에서 Implements 했습니다.
     * 이번 버전에서는 OnItemClickListener를 어디에서도 Implements하지 않습니다.
     * 다만 HomeFragment.java/182번째 줄에서 선언한 것과 같이 각 Fragment 혹은 Activity 단에서
     * 익명 Interface (new A()와 같은 형태)로 구현체를 넘겨줍니다.
     *
     * 여기서 구현체는 바로 3줄 아래(LikeStoreAdapter.java/37번째 줄)에 있는 setOnItemClickListener(...)가
     * 넘겨받아서 각 Adapter에 선언한 멤버 변수(LikeStoreAdapter.java/35번째 줄)에게 할당합니다.
     *
     * 그리고 setOnItemClickListener로 등록한 OnItemClickListener를 각 아이템에 매핑합니다.
     * 각 아이템에 매핑하는 작업은 LikeStoreAdapter.java/64번째 줄에서 합니다.
     *
     * 위의 작업을 마치면 각 아이템 클릭 시 클릭당한 ViewHolder를 받을 수 있습니다. (HomeFragment.java/185번째 줄)
     * 넘겨받은 ViewHolder에서 id를 추출하기 위해서 멤버변수 id를 추가했습니다. (LikeStoreAdapter.java/58번째 줄)
     * 멤버변수에 id를 바인딩하는 코드는 LikeStoreAdapter.java/117번째 줄에 있습니다.
     *
     * 위에 작업들을 완료하면 LikeStoreAdapter의 작업은 끝입니다.
     * 이제 HomeFragment.java/185번째 줄로 와주세요.
     */
    public OnItemClickListener<LikeStoreAdapter.ViewHolder> mListener = null;

    public void setOnItemClickListener(OnItemClickListener<LikeStoreAdapter.ViewHolder> mListener) {
        this.mListener = mListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public ImageView img_like;
        public TextView codeStore;
        public TextView nameStore;
        public TextView rateStore;
        public TextView rateCountStore;

        // Store ID 추가
        public int storeId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.iv_shop);
            this.img_like = itemView.findViewById(R.id.iv_like);
            this.codeStore = itemView.findViewById(R.id.tv_shopnum);
            this.nameStore = itemView.findViewById(R.id.tv_shopname);
            this.rateStore = itemView.findViewById(R.id.tv_starscore);
            this.rateCountStore = itemView.findViewById(R.id.tv_evaluation);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isNull(mListener)) {
                        mListener.onItemClick(LikeStoreAdapter.ViewHolder.this);
                    }
                }

                boolean isNull(OnItemClickListener<LikeStoreAdapter.ViewHolder> l) {
                    return l == null;
                }
            });
        }
    }

    private List<ContactShopOC> mLikeStore;

    public LikeStoreAdapter(List<ContactShopOC> stores) {
        this.mLikeStore = stores;
        glide = null;
    }

    public LikeStoreAdapter(RequestManager glide, List<ContactShopOC> stores) {
        this.mLikeStore = stores;
        this.glide = glide;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactShopOC likeStore = mLikeStore.get(position);

        ImageView image = holder.image;
        ImageView img_like = holder.img_like;
        TextView codeStore = holder.codeStore;
        TextView nameStore = holder.nameStore;
        TextView rateStore = holder.rateStore;
        TextView rateCountStore = holder.rateCountStore;

        // Store ID 바인딩
        holder.storeId = likeStore.getMShopId();


        if (glide == null) {
            image.setImageResource(likeStore.getMImage());
        } else {
            glide.load(likeStore.get_mImage()).into((image));
        }
        img_like.setImageResource(likeStore.isMLike()? R.drawable.like : R.drawable.unlike);
        codeStore.setText(likeStore.getMShopnum());
        nameStore.setText(likeStore.getMShopname());
        rateStore.setText(Double.toString(likeStore.getMStarscore()));
        rateCountStore.setText(likeStore.getMEvaluation());
    }

    @Override
    public int getItemCount() {
        return mLikeStore.size();
    }
}
