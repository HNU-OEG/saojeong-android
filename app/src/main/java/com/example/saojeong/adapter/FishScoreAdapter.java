package com.example.saojeong.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.model.ContactFishScore;
import com.example.saojeong.model.ContactFishStarScore;
import com.example.saojeong.model.RecyclerDecoration;

import java.util.ArrayList;
import java.util.List;

public class FishScoreAdapter extends RecyclerView.Adapter<FishScoreAdapter.ViewHolder> {

    View rating;

    TextView tv_kindscore;
    TextView tv_itemscore;
    TextView tv_pricescore;
    TextView tv_evaluate;

    ImageView iv_kindstar1;
    ImageView iv_kindstar2;
    ImageView iv_kindstar3;
    ImageView iv_kindstar4;
    ImageView iv_kindstar5;
    ImageView iv_itemstar1;
    ImageView iv_itemstar2;
    ImageView iv_itemstar3;
    ImageView iv_itemstar4;
    ImageView iv_itemstar5;
    ImageView iv_pricestar1;
    ImageView iv_pricestar2;
    ImageView iv_pricestar3;
    ImageView iv_pricestar4;
    ImageView iv_pricestar5;

    String S_kindscore;
    String S_itemscore;
    String S_pricescore;
    float kindscore;
    float itemscore;
    float pricescore;

    Context mContext;

    private RecyclerView recyclerFishStarScore;
    private FishStarScoreAdapter fishStarScoreAdapter;
    ArrayList<ContactFishStarScore> contactFishStarScores;

    RecyclerDecoration.RightDecoration rightDecoration = new RecyclerDecoration.RightDecoration(40);
    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(50);

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView evaluateTextView;
        public TextView kindscoreTextView;
        public TextView itemscoreTextView;
        public TextView pricescoreTextView;
        public Button scoreButton;

        public ViewHolder(View itemView) {
            super(itemView);
            evaluateTextView = (TextView) itemView.findViewById(R.id.tv_fish_evaluate);
            kindscoreTextView = (TextView) itemView.findViewById(R.id.tv_fish_kindscore);
            itemscoreTextView = (TextView) itemView.findViewById(R.id.tv_fish_itemscore);
            pricescoreTextView = (TextView) itemView.findViewById(R.id.tv_fish_pricescore);

            scoreButton = (Button) itemView.findViewById(R.id.btn_fish_score);

            //평점에 따라 변경되는 별 갯수
            //Id 불러오기
            tv_kindscore = (TextView) itemView.findViewById(R.id.tv_fish_kindscore);
            tv_itemscore = (TextView) itemView.findViewById(R.id.tv_fish_itemscore);
            tv_pricescore = (TextView) itemView.findViewById(R.id.tv_fish_pricescore);
            iv_kindstar1 = (ImageView) itemView.findViewById(R.id.iv_fish_kindstar1);
            iv_kindstar2 = (ImageView) itemView.findViewById(R.id.iv_fish_kindstar2);
            iv_kindstar3 = (ImageView) itemView.findViewById(R.id.iv_fish_kindstar3);
            iv_kindstar4 = (ImageView) itemView.findViewById(R.id.iv_fish_kindstar4);
            iv_kindstar5 = (ImageView) itemView.findViewById(R.id.iv_fish_kindstar5);
            iv_itemstar1 = (ImageView) itemView.findViewById(R.id.iv_fish_itemstar1);
            iv_itemstar2 = (ImageView) itemView.findViewById(R.id.iv_fish_itemstar2);
            iv_itemstar3 = (ImageView) itemView.findViewById(R.id.iv_fish_itemstar3);
            iv_itemstar4 = (ImageView) itemView.findViewById(R.id.iv_fish_itemstar4);
            iv_itemstar5 = (ImageView) itemView.findViewById(R.id.iv_fish_itemstar5);
            iv_pricestar1 = (ImageView) itemView.findViewById(R.id.iv_fish_pricestar1);
            iv_pricestar2 = (ImageView) itemView.findViewById(R.id.iv_fish_pricestar2);
            iv_pricestar3 = (ImageView) itemView.findViewById(R.id.iv_fish_pricestar3);
            iv_pricestar4 = (ImageView) itemView.findViewById(R.id.iv_fish_pricestar4);
            iv_pricestar5 = (ImageView) itemView.findViewById(R.id.iv_fish_pricestar5);

            recyclerFishStarScore = (RecyclerView) itemView.findViewById(R.id.recycler_fish_score);
        }
    }

    private List<ContactFishScore> mContacts;

    public FishScoreAdapter(List<ContactFishScore> contacts, Context context) {
        mContacts = contacts;
        mContext = context;
    }

    @Override
    public FishScoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_fish_score, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FishScoreAdapter.ViewHolder holder, int position) {
        ContactFishScore contactFishScore = mContacts.get(position);

        TextView tv_evaluate = holder.evaluateTextView;
        TextView tv_kindscore = holder.kindscoreTextView;
        TextView tv_itemscore = holder.itemscoreTextView;
        TextView tv_pricescore = holder.pricescoreTextView;

        Button btn_score = holder.scoreButton;


        tv_evaluate.setText(contactFishScore.getmEvaluate());
        tv_kindscore.setText(contactFishScore.getmKindscore());
        tv_itemscore.setText(contactFishScore.getmItemscore());
        tv_pricescore.setText(contactFishScore.getmPricescore());

        btn_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FishStarScoreAdapter adapter = (FishStarScoreAdapter) recyclerFishStarScore.getAdapter();
                List<Float> ratings = new ArrayList<>();
                for (ContactFishStarScore item : adapter.getmContacts()) {
                    ratings.add(item.getmRating());
                }
                // 통신할 때 이 세개 넘겨주기
            }
        });

        //평가하기
        contactFishStarScores = ContactFishStarScore.createContactsList(3);
        fishStarScoreAdapter = new FishStarScoreAdapter(contactFishStarScores);
        recyclerFishStarScore.addItemDecoration(rightDecoration);
        recyclerFishStarScore.addItemDecoration(bottomDecoration);
        recyclerFishStarScore.setLayoutManager((new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)));
        recyclerFishStarScore.setAdapter(fishStarScoreAdapter);

        //문자열로 받아오기
        S_kindscore = tv_kindscore.getText().toString();
        S_itemscore = tv_itemscore.getText().toString();
        S_pricescore = tv_pricescore.getText().toString();

        //float으로 변환
        kindscore = Float.parseFloat(S_kindscore);
        itemscore = Float.parseFloat(S_itemscore);
        pricescore = Float.parseFloat(S_pricescore);

        //평점에 따라 별 개수 변경(친절도)
        if (kindscore >= 0 && kindscore < 0.5) {
            iv_kindstar1.setImageResource(R.drawable.star_empty);
            iv_kindstar2.setImageResource(R.drawable.star_empty);
            iv_kindstar3.setImageResource(R.drawable.star_empty);
            iv_kindstar4.setImageResource(R.drawable.star_empty);
            iv_kindstar5.setImageResource(R.drawable.star_empty);
        } else if (kindscore >= 0.5 && kindscore < 1) {
            iv_kindstar1.setImageResource(R.drawable.star_half);
            iv_kindstar2.setImageResource(R.drawable.star_empty);
            iv_kindstar3.setImageResource(R.drawable.star_empty);
            iv_kindstar4.setImageResource(R.drawable.star_empty);
            iv_kindstar5.setImageResource(R.drawable.star_empty);
        } else if (kindscore >= 1 && kindscore < 1.5) {
            iv_kindstar1.setImageResource(R.drawable.star_fill);
            iv_kindstar2.setImageResource(R.drawable.star_empty);
            iv_kindstar3.setImageResource(R.drawable.star_empty);
            iv_kindstar4.setImageResource(R.drawable.star_empty);
            iv_kindstar5.setImageResource(R.drawable.star_empty);
        } else if (kindscore >= 1.5 && kindscore < 2) {
            iv_kindstar1.setImageResource(R.drawable.star_fill);
            iv_kindstar2.setImageResource(R.drawable.star_half);
            iv_kindstar3.setImageResource(R.drawable.star_empty);
            iv_kindstar4.setImageResource(R.drawable.star_empty);
            iv_kindstar5.setImageResource(R.drawable.star_empty);
        } else if (kindscore >= 2 && kindscore < 2.5) {
            iv_kindstar1.setImageResource(R.drawable.star_fill);
            iv_kindstar2.setImageResource(R.drawable.star_fill);
            iv_kindstar3.setImageResource(R.drawable.star_empty);
            iv_kindstar4.setImageResource(R.drawable.star_empty);
            iv_kindstar5.setImageResource(R.drawable.star_empty);
        } else if (kindscore >= 2.5 && kindscore < 3) {
            iv_kindstar1.setImageResource(R.drawable.star_fill);
            iv_kindstar2.setImageResource(R.drawable.star_fill);
            iv_kindstar3.setImageResource(R.drawable.star_half);
            iv_kindstar4.setImageResource(R.drawable.star_empty);
            iv_kindstar5.setImageResource(R.drawable.star_empty);
        } else if (kindscore >= 3 && kindscore < 3.5) {
            iv_kindstar1.setImageResource(R.drawable.star_fill);
            iv_kindstar2.setImageResource(R.drawable.star_fill);
            iv_kindstar3.setImageResource(R.drawable.star_fill);
            iv_kindstar4.setImageResource(R.drawable.star_empty);
            iv_kindstar5.setImageResource(R.drawable.star_empty);
        } else if (kindscore >= 3.5 && kindscore < 4) {
            iv_kindstar1.setImageResource(R.drawable.star_fill);
            iv_kindstar2.setImageResource(R.drawable.star_fill);
            iv_kindstar3.setImageResource(R.drawable.star_fill);
            iv_kindstar4.setImageResource(R.drawable.star_half);
            iv_kindstar5.setImageResource(R.drawable.star_empty);
        } else if (kindscore >= 4 && kindscore < 4.5) {
            iv_kindstar1.setImageResource(R.drawable.star_fill);
            iv_kindstar2.setImageResource(R.drawable.star_fill);
            iv_kindstar3.setImageResource(R.drawable.star_fill);
            iv_kindstar4.setImageResource(R.drawable.star_fill);
            iv_kindstar5.setImageResource(R.drawable.star_empty);
        } else if (kindscore >= 4.5 && kindscore < 5) {
            iv_kindstar1.setImageResource(R.drawable.star_fill);
            iv_kindstar2.setImageResource(R.drawable.star_fill);
            iv_kindstar3.setImageResource(R.drawable.star_fill);
            iv_kindstar4.setImageResource(R.drawable.star_fill);
            iv_kindstar5.setImageResource(R.drawable.star_half);
        } else if (kindscore == 5) {
            iv_kindstar1.setImageResource(R.drawable.star_fill);
            iv_kindstar2.setImageResource(R.drawable.star_fill);
            iv_kindstar3.setImageResource(R.drawable.star_fill);
            iv_kindstar4.setImageResource(R.drawable.star_fill);
            iv_kindstar5.setImageResource(R.drawable.star_fill);
        }

        //평점에 따라 별 개수 변경(상품성)
        if (itemscore >= 0 && itemscore < 0.5) {
            iv_itemstar1.setImageResource(R.drawable.star_empty);
            iv_itemstar2.setImageResource(R.drawable.star_empty);
            iv_itemstar3.setImageResource(R.drawable.star_empty);
            iv_itemstar4.setImageResource(R.drawable.star_empty);
            iv_itemstar5.setImageResource(R.drawable.star_empty);
        } else if (itemscore >= 0.5 && itemscore < 1) {
            iv_itemstar1.setImageResource(R.drawable.star_half);
            iv_itemstar2.setImageResource(R.drawable.star_empty);
            iv_itemstar3.setImageResource(R.drawable.star_empty);
            iv_itemstar4.setImageResource(R.drawable.star_empty);
            iv_itemstar5.setImageResource(R.drawable.star_empty);
        } else if (itemscore >= 1 && itemscore < 1.5) {
            iv_itemstar1.setImageResource(R.drawable.star_fill);
            iv_itemstar2.setImageResource(R.drawable.star_empty);
            iv_itemstar3.setImageResource(R.drawable.star_empty);
            iv_itemstar4.setImageResource(R.drawable.star_empty);
            iv_itemstar5.setImageResource(R.drawable.star_empty);
        } else if (itemscore >= 1.5 && itemscore < 2) {
            iv_itemstar1.setImageResource(R.drawable.star_fill);
            iv_itemstar2.setImageResource(R.drawable.star_half);
            iv_itemstar3.setImageResource(R.drawable.star_empty);
            iv_itemstar4.setImageResource(R.drawable.star_empty);
            iv_itemstar5.setImageResource(R.drawable.star_empty);
        } else if (itemscore >= 2 && itemscore < 2.5) {
            iv_itemstar1.setImageResource(R.drawable.star_fill);
            iv_itemstar2.setImageResource(R.drawable.star_fill);
            iv_itemstar3.setImageResource(R.drawable.star_empty);
            iv_itemstar4.setImageResource(R.drawable.star_empty);
            iv_itemstar5.setImageResource(R.drawable.star_empty);
        } else if (itemscore >= 2.5 && itemscore < 3) {
            iv_itemstar1.setImageResource(R.drawable.star_fill);
            iv_itemstar2.setImageResource(R.drawable.star_fill);
            iv_itemstar3.setImageResource(R.drawable.star_half);
            iv_itemstar4.setImageResource(R.drawable.star_empty);
            iv_itemstar5.setImageResource(R.drawable.star_empty);
        } else if (itemscore >= 3 && itemscore < 3.5) {
            iv_itemstar1.setImageResource(R.drawable.star_fill);
            iv_itemstar2.setImageResource(R.drawable.star_fill);
            iv_itemstar3.setImageResource(R.drawable.star_fill);
            iv_itemstar4.setImageResource(R.drawable.star_empty);
            iv_itemstar5.setImageResource(R.drawable.star_empty);
        } else if (itemscore >= 3.5 && itemscore < 4) {
            iv_itemstar1.setImageResource(R.drawable.star_fill);
            iv_itemstar2.setImageResource(R.drawable.star_fill);
            iv_itemstar3.setImageResource(R.drawable.star_fill);
            iv_itemstar4.setImageResource(R.drawable.star_half);
            iv_itemstar5.setImageResource(R.drawable.star_empty);
        } else if (itemscore >= 4 && itemscore < 4.5) {
            iv_itemstar1.setImageResource(R.drawable.star_fill);
            iv_itemstar2.setImageResource(R.drawable.star_fill);
            iv_itemstar3.setImageResource(R.drawable.star_fill);
            iv_itemstar4.setImageResource(R.drawable.star_fill);
            iv_itemstar5.setImageResource(R.drawable.star_empty);
        } else if (itemscore >= 4.5 && itemscore < 5) {
            iv_itemstar1.setImageResource(R.drawable.star_fill);
            iv_itemstar2.setImageResource(R.drawable.star_fill);
            iv_itemstar3.setImageResource(R.drawable.star_fill);
            iv_itemstar4.setImageResource(R.drawable.star_fill);
            iv_itemstar5.setImageResource(R.drawable.star_half);
        } else if (itemscore == 5) {
            iv_itemstar1.setImageResource(R.drawable.star_fill);
            iv_itemstar2.setImageResource(R.drawable.star_fill);
            iv_itemstar3.setImageResource(R.drawable.star_fill);
            iv_itemstar4.setImageResource(R.drawable.star_fill);
            iv_itemstar5.setImageResource(R.drawable.star_fill);
        }

        //평점에 따라 별 개수 변경(가격)
        if (pricescore >= 0 && pricescore < 0.5) {
            iv_itemstar1.setImageResource(R.drawable.star_empty);
            iv_itemstar2.setImageResource(R.drawable.star_empty);
            iv_itemstar3.setImageResource(R.drawable.star_empty);
            iv_itemstar4.setImageResource(R.drawable.star_empty);
            iv_itemstar5.setImageResource(R.drawable.star_empty);
        } else if (pricescore >= 0.5 && pricescore < 1) {
            iv_pricestar1.setImageResource(R.drawable.star_half);
            iv_pricestar2.setImageResource(R.drawable.star_empty);
            iv_pricestar3.setImageResource(R.drawable.star_empty);
            iv_pricestar4.setImageResource(R.drawable.star_empty);
            iv_pricestar5.setImageResource(R.drawable.star_empty);
        } else if (pricescore >= 1 && pricescore < 1.5) {
            iv_pricestar1.setImageResource(R.drawable.star_fill);
            iv_pricestar2.setImageResource(R.drawable.star_empty);
            iv_pricestar3.setImageResource(R.drawable.star_empty);
            iv_pricestar4.setImageResource(R.drawable.star_empty);
            iv_pricestar5.setImageResource(R.drawable.star_empty);
        } else if (pricescore >= 1.5 && pricescore < 2) {
            iv_pricestar1.setImageResource(R.drawable.star_fill);
            iv_pricestar2.setImageResource(R.drawable.star_half);
            iv_pricestar3.setImageResource(R.drawable.star_empty);
            iv_pricestar4.setImageResource(R.drawable.star_empty);
            iv_pricestar5.setImageResource(R.drawable.star_empty);
        } else if (pricescore >= 2 && pricescore < 2.5) {
            iv_pricestar1.setImageResource(R.drawable.star_fill);
            iv_pricestar2.setImageResource(R.drawable.star_fill);
            iv_pricestar3.setImageResource(R.drawable.star_empty);
            iv_pricestar4.setImageResource(R.drawable.star_empty);
            iv_pricestar5.setImageResource(R.drawable.star_empty);
        } else if (pricescore >= 2.5 && pricescore < 3) {
            iv_pricestar1.setImageResource(R.drawable.star_fill);
            iv_pricestar2.setImageResource(R.drawable.star_fill);
            iv_pricestar3.setImageResource(R.drawable.star_half);
            iv_pricestar4.setImageResource(R.drawable.star_empty);
            iv_pricestar5.setImageResource(R.drawable.star_empty);
        } else if (pricescore >= 3 && pricescore < 3.5) {
            iv_pricestar1.setImageResource(R.drawable.star_fill);
            iv_pricestar2.setImageResource(R.drawable.star_fill);
            iv_pricestar3.setImageResource(R.drawable.star_fill);
            iv_pricestar4.setImageResource(R.drawable.star_empty);
            iv_pricestar5.setImageResource(R.drawable.star_empty);
        } else if (pricescore >= 3.5 && pricescore < 4) {
            iv_pricestar1.setImageResource(R.drawable.star_fill);
            iv_pricestar2.setImageResource(R.drawable.star_fill);
            iv_pricestar3.setImageResource(R.drawable.star_fill);
            iv_pricestar4.setImageResource(R.drawable.star_half);
            iv_pricestar5.setImageResource(R.drawable.star_empty);
        } else if (pricescore >= 4 && pricescore < 4.5) {
            iv_pricestar1.setImageResource(R.drawable.star_fill);
            iv_pricestar2.setImageResource(R.drawable.star_fill);
            iv_pricestar3.setImageResource(R.drawable.star_fill);
            iv_pricestar4.setImageResource(R.drawable.star_fill);
            iv_pricestar5.setImageResource(R.drawable.star_empty);
        } else if (pricescore >= 4.5 && pricescore < 5) {
            iv_pricestar1.setImageResource(R.drawable.star_fill);
            iv_pricestar2.setImageResource(R.drawable.star_fill);
            iv_pricestar3.setImageResource(R.drawable.star_fill);
            iv_pricestar4.setImageResource(R.drawable.star_fill);
            iv_pricestar5.setImageResource(R.drawable.star_half);
        } else if (pricescore == 5) {
            iv_pricestar1.setImageResource(R.drawable.star_fill);
            iv_pricestar2.setImageResource(R.drawable.star_fill);
            iv_pricestar3.setImageResource(R.drawable.star_fill);
            iv_pricestar4.setImageResource(R.drawable.star_fill);
            iv_pricestar5.setImageResource(R.drawable.star_fill);
        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}