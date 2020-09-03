package com.example.saojeong.adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.model.ContactShopScore;
import com.example.saojeong.model.ContactShopStarScore;
import com.example.saojeong.model.RecyclerDecoration;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.response.UpdateVoteGradeResponseDto;
import com.example.saojeong.rest.request.UpdateVoteGradeRequestDto;
import com.example.saojeong.rest.service.StoreService;
import com.example.saojeong.util.AlertBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopScoreAdapter extends RecyclerView.Adapter<ShopScoreAdapter.ViewHolder> {

    int id;

    View rating;

    TextView tv_kindscore;
    TextView tv_itemscore;
    TextView tv_pricescore;
    TextView tv_evaluate;
    LinearLayout ll_shop_kind;
    LinearLayout ll_shop_item;
    LinearLayout ll_shop_price;

    String S_kindscore;
    String S_itemscore;
    String S_pricescore;
    float kindscore;
    float itemscore;
    float pricescore;

    Context mContext;

    private RecyclerView recyclerShopStarScore;
    private ShopStarScoreAdapter shopStarScoreAdapter;
    ArrayList<ContactShopStarScore> contactShopStarScores;

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
            evaluateTextView = itemView.findViewById(R.id.tv_shop_evaluate);
            kindscoreTextView = itemView.findViewById(R.id.tv_shop_kindscore);
            itemscoreTextView = itemView.findViewById(R.id.tv_shop_itemscore);
            pricescoreTextView = itemView.findViewById(R.id.tv_shop_pricescore);

            scoreButton = itemView.findViewById(R.id.btn_shop_score);

            //평점에 따라 변경되는 별 갯수
            //Id 불러오기
            tv_kindscore = itemView.findViewById(R.id.tv_shop_kindscore);
            tv_itemscore = itemView.findViewById(R.id.tv_shop_itemscore);
            tv_pricescore = itemView.findViewById(R.id.tv_shop_pricescore);
            ll_shop_kind = itemView.findViewById(R.id.ll_shop_kind);
            ll_shop_item = itemView.findViewById(R.id.ll_shop_item);
            ll_shop_price = itemView.findViewById(R.id.ll_shop_price);

            recyclerShopStarScore = itemView.findViewById(R.id.recycler_shop_score);
        }
    }

    private List<ContactShopScore> mContacts;

    public ShopScoreAdapter(List<ContactShopScore> contacts, Context context, int id) {
        mContacts = contacts;
        mContext = context;
        this.id = id;
    }

    public ShopScoreAdapter(List<ContactShopScore> contacts, Context context) {
        mContacts = contacts;
        mContext = context;
        this.id  = -1;
    }

    @Override
    public ShopScoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_shop_score, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ShopScoreAdapter.ViewHolder holder, int position) {
        ContactShopScore contactShopScore = mContacts.get(position);

        TextView tv_evaluate = holder.evaluateTextView;
        TextView tv_kindscore = holder.kindscoreTextView;
        TextView tv_itemscore = holder.itemscoreTextView;
        TextView tv_pricescore = holder.pricescoreTextView;

        Button btn_score = holder.scoreButton;


        tv_evaluate.setText(contactShopScore.getmEvaluate());
        tv_kindscore.setText(contactShopScore.getmKindscore());
        tv_itemscore.setText(contactShopScore.getmItemscore());
        tv_pricescore.setText(contactShopScore.getmPricescore());

        Log.d("SCORE CHECK", contactShopScore.getUserItemScore()+"");

        if (contactShopScore.getUserKindScore() == -1.0F) {
            Log.d("NEVER VOTED", btn_score.getText().toString());
            contactShopStarScores = ContactShopStarScore.createContactsList();
            shopStarScoreAdapter = new ShopStarScoreAdapter(contactShopStarScores);

            btn_score.setOnClickListener(view -> {
                ShopStarScoreAdapter adapter = (ShopStarScoreAdapter) recyclerShopStarScore.getAdapter();
                UpdateVoteGradeRequestDto requestBody = new UpdateVoteGradeRequestDto(
                        adapter.getmContacts().get(0).getmRating(),
                        adapter.getmContacts().get(1).getmRating(),
                        adapter.getmContacts().get(2).getmRating()
                );

                StoreService storeService = ServiceGenerator.createService(StoreService.class, TokenCase.getToken());
                storeService.createVoteGrade(id, requestBody).enqueue(new Callback<UpdateVoteGradeResponseDto>() {
                    @Override
                    public void onResponse(Call<UpdateVoteGradeResponseDto> call,
                                           Response<UpdateVoteGradeResponseDto> response) {
                        if (response.code() == 201) {
                            btn_score.setText("수정하기");
                            AlertBuilder.createDialog(mContext, "통신 성공", "평점 등록을 성공했습니다!")
                                    .show();
//                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//                            builder.setTitle("통신 성공").setMessage("평점 등록을 완료했습니다!");
//                            AlertDialog alertDialog = builder.create();
//                            alertDialog.show();
                        } else {
                            AlertBuilder.createDialog(mContext, "서버 오류", "평점 수정을 실패했습니다!")
                                    .show();
//                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//                            builder.setTitle("서버 오류").setMessage("평점 등록을 실패했습니다!");
//                            AlertDialog alertDialog = builder.create();
//                            alertDialog.show();
                        }


                    }

                    @Override
                    public void onFailure(Call<UpdateVoteGradeResponseDto> call, Throwable t) {
                        AlertBuilder.createDialog(mContext, "통신 실패", "평점 등록을 실패했습니다!")
                                .show();
//                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//                        builder.setTitle("통신 실패").setMessage("평점 등록을 실패했습니다!");
//                        AlertDialog alertDialog = builder.create();
//                        alertDialog.show();
                    }
                });
            });

        } else {
            Log.d("VOTED", btn_score.getText().toString());
            contactShopStarScores = ContactShopStarScore.createContactsList(
                    contactShopScore.getUserKindScore(),
                    contactShopScore.getUserItemScore(),
                    contactShopScore.getUserPriceScore()
            );
            shopStarScoreAdapter = new ShopStarScoreAdapter(contactShopStarScores);
            btn_score.setText("수정하기");

            btn_score.setOnClickListener(view -> {
                ShopStarScoreAdapter adapter = (ShopStarScoreAdapter) recyclerShopStarScore.getAdapter();
                UpdateVoteGradeRequestDto requestBody = new UpdateVoteGradeRequestDto(
                        adapter.getmContacts().get(0).getmRating(),
                        adapter.getmContacts().get(1).getmRating(),
                        adapter.getmContacts().get(2).getmRating()
                );

                StoreService storeService = ServiceGenerator.createService(StoreService.class, TokenCase.getToken());
                storeService.updateVoteGrade(id, requestBody).enqueue(new Callback<UpdateVoteGradeResponseDto>() {
                    @Override
                    public void onResponse(Call<UpdateVoteGradeResponseDto> call,
                                           Response<UpdateVoteGradeResponseDto> response) {
                        if (response.code() == 201) {
                            btn_score.setText("수정하기");
                            AlertBuilder.createDialog(mContext, "통신 성공", "평점 수정 성공!!")
                                    .show();
//                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//                            builder.setTitle("통신 성공").setMessage("평점 수정 성공!!");
//                            AlertDialog alertDialog = builder.create();
//                            alertDialog.show();
                        } else {
                            AlertBuilder.createDialog(mContext, "서버 오류", "평점 수정을 실패했습니다!")
                                    .show();
//                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//                            builder.setTitle("서버 오류").setMessage("평점 수정을 실패했습니다!");
//                            AlertDialog alertDialog = builder.create();
//                            alertDialog.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateVoteGradeResponseDto> call, Throwable t) {
                        AlertBuilder.createDialog(mContext, "통신 실패", "평점 수정을 실패했습니다!")
                                .show();
                    }
                });
            });
        }

        recyclerShopStarScore.addItemDecoration(rightDecoration);
        recyclerShopStarScore.addItemDecoration(bottomDecoration);
        recyclerShopStarScore.setLayoutManager((new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)));
        recyclerShopStarScore.setAdapter(shopStarScoreAdapter);

        //문자열로 받아오기
        S_kindscore = tv_kindscore.getText().toString();
        S_itemscore = tv_itemscore.getText().toString();
        S_pricescore = tv_pricescore.getText().toString();

        //float으로 변환
        kindscore = Float.parseFloat(S_kindscore);
        itemscore = Float.parseFloat(S_itemscore);
        pricescore = Float.parseFloat(S_pricescore);

        markingStar(kindscore, ll_shop_kind);
        markingStar(itemscore, ll_shop_item);
        markingStar(pricescore, ll_shop_price);
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public void markingStar(float scoreOrigin, LinearLayout stars) {
        int i = 0;
        int integerScore = (int) scoreOrigin;   //float형 점수를 int형으로 바꿔주면서 정수부분 추출

        //원본과 변환된 값을 빼주면서 소수부분 추출
        //origin = 3.3이면 integer는 3.0이고 hasPoint는 0.3이 된다.
        float hasPoint = scoreOrigin - integerScore;

        while (i < 5) {
            ImageView star = new ImageView(stars.getContext());

            if (i < integerScore) {
                star.setImageResource(R.drawable.star_fill);

            } else if (i == integerScore && i != 0) {   //마지막 인덱스에서 수수부분 값에 따라 이미지 변화
                if (hasPoint == 0) star.setImageResource(R.drawable.star_fill);
                else if (hasPoint < 0.5) star.setImageResource(R.drawable.star_empty);
                else if (hasPoint >= 0.5) star.setImageResource(R.drawable.star_half);

            } else {
                star.setImageResource(R.drawable.star_empty);
            }
            stars.addView(star);
            i+=1;
        }
    }
}