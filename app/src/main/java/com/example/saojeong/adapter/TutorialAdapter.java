package com.example.saojeong.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.fragment.HomeFragment;
import com.example.saojeong.model.ContactFruitClose;
import com.example.saojeong.model.ContactFruitOpen;
import com.example.saojeong.model.LikeStore;
import com.example.saojeong.model.LoginData;
import com.example.saojeong.model.TutorialValue;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.Login_GuestDto;
import com.example.saojeong.rest.dto.StoreDto;
import com.example.saojeong.rest.dto.TypeStoreDto;
import com.example.saojeong.rest.service.Login_Guest;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TutorialAdapter extends RecyclerView.Adapter<TutorialAdapter.ViewHolder>{
    Activity mActivity;

    private Login_Guest     Login_GuestService;
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RelativeLayout rl_testTuto;
        LinearLayout ll_test0;
        LinearLayout ll_test1;
        Button login_facebook;
        Button login_kakaotalk;
        Button login_naver;
        Button login_google;
        Button login_guest;

        public ViewHolder(View itemView) {
            super(itemView);
            rl_testTuto=itemView.findViewById(R.id.intro_background);
            ll_test0=itemView.findViewById(R.id.ll_test0);
            login_facebook=itemView.findViewById(R.id.login_facebook);
            login_kakaotalk=itemView.findViewById(R.id.login_kakaotalk);
            login_naver=itemView.findViewById(R.id.login_naver);
            login_google=itemView.findViewById(R.id.login_google);
            login_guest=itemView.findViewById(R.id.login_guest);
            Login_GuestService = ServiceGenerator.createService(Login_Guest.class, TokenCase.getToken());
            login_facebook.setOnClickListener(this);
            login_kakaotalk.setOnClickListener(this);
            login_naver.setOnClickListener(this);
            login_google.setOnClickListener(this);
            login_guest.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch(view.getId())
            {
                case R.id.login_facebook:
                    loadStores(mActivity);
                    //Intent intent = new Intent(mActivity, MainActivity.class);
                    //mActivity.startActivity(intent);
                    //mActivity.finish();
                    break;
                case R.id.login_kakaotalk:
                    loadStores(mActivity);
                    break;
                case R.id.login_naver:
                    loadStores(mActivity);
                    break;
                case R.id.login_google:
                    loadStores(mActivity);
                    break;
                case R.id.login_guest:
                    loadStores(mActivity);
                    break;
            }
        }
    }
    private void loadStores(Activity activity, String Type){
        if(Type=="kakao")
        {

        }
        if(Type=="google")
        {

        }
        if(Type=="naver")
        {

        }
        if(Type=="facebook")
        {

        }

        Login_GuestService.CreateLogin().enqueue(new Callback<Login_GuestDto>() {
            @Override
            public void onResponse(Call<Login_GuestDto> call, Response<Login_GuestDto> response) {

                LoginData dto= new LoginData(Objects.requireNonNull(response.body()));

                Login_GuestDto body = response.body();
                String str=body.getResult().get(0).memberId;
                String str2=body.getResult().get(0).memberId;
                String str3=body.getResult().get(0).memberId;
                String str4=body.accessToken;
                SharedPreferences pref=activity.getSharedPreferences("SHARE_PREF", activity.MODE_PRIVATE);
                SharedPreferences.Editor editer=pref.edit();
                editer.putString("AccessToken", str4);
                editer.apply();
                SharedPreferences pref2=activity.getSharedPreferences("SHARE_PREF", activity.MODE_PRIVATE);
                str3=pref2.getString("AccessToken", str);
                SharedPreferences pref3=activity.getSharedPreferences("SHARE_PREF", activity.MODE_PRIVATE);

            }

            @Override
            public void onFailure(Call<Login_GuestDto> call, Throwable t) {

            }
        });

    }


    private List<TutorialValue> mContacts;

    public TutorialAdapter(List<TutorialValue> contacts, Activity activity) {
        mContacts = contacts;
        mActivity=activity;
    }

    @Override
    public TutorialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_tutorial, parent, false);

        TutorialAdapter.ViewHolder viewHolder = new TutorialAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TutorialAdapter.ViewHolder holder, int position) {
        TutorialValue testTutomodel = mContacts.get(position);
        holder.rl_testTuto.setBackgroundResource(testTutomodel.GetImage());
        if(!testTutomodel.GetLogin()) {
            holder.ll_test0.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}