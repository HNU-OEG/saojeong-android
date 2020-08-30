package com.example.saojeong.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.LikeStoreAdapter;
import com.example.saojeong.adapter.StarStoreAdapter;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.model.ContactShopOC;
import com.example.saojeong.model.LikeStore;
import com.example.saojeong.model.MyPageGetData;
import com.example.saojeong.model.RecyclerDecoration;
import com.example.saojeong.model.StarStore;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.store.StoreDto;
import com.example.saojeong.rest.service.StoreService;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MyPageFragment extends Fragment {

    private RecyclerView recyclerShop;
    private RecyclerView recyclerStar;
    private LikeStoreAdapter likeStoreAdapter;
    private StarStoreAdapter starStoreAdapter;
    private ShopFragment shopFragment;

    private TextView tv_userId;
    private ImageView iv_profile;
    private SharedPreferences sharedPreferences;

    List<ContactShopOC> likeStores;
    List<ContactShopOC> starStores;

    RecyclerDecoration.LeftDecoration leftDecoration = new RecyclerDecoration.LeftDecoration(50);
    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(10);

    private StoreService storeService;

    private Button btn_profile;

    private final String TAG = this.getClass().getName();

    public static MyPageFragment newInstance() {
        return new MyPageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "start MyPageFragment");
        storeService = ServiceGenerator.createService(StoreService.class, TokenCase.getToken());

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);

        btn_profile = view.findViewById(R.id.btn_profile);
        recyclerShop = view.findViewById(R.id.recycler_likeStore);
        tv_userId = view.findViewById(R.id.tv_userId);
        iv_profile = view.findViewById(R.id.iv_profile);

        Context context = getContext();
        sharedPreferences = context.getSharedPreferences("UserInfo", MODE_PRIVATE);

        byte[] imgByte = getImage();
        if (imgByte == null) {
            Log.d("profile", "이미지 호출 실패");
        } else {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imgByte);
            Bitmap img = BitmapFactory.decodeStream(inputStream);
            iv_profile.setImageBitmap(img);
        }

        //String nickname = getName();
        String nickname = TokenCase.getUserResource("nickname");
        tv_userId.setText(nickname);
        Log.d(TAG, "닉네임: " + nickname);

        btn_profile.setOnClickListener((v) -> {
            ((MainActivity) getActivity()).replaceFragment(ProfileFragment.newInstance());
        });


        storeService.getStarredStoreList().enqueue(new Callback<List<StoreDto>>() {
            @Override
            public void onResponse(Call<List<StoreDto>> call, Response<List<StoreDto>> response) {
                if (response.code() == 201) {
                    List<StoreDto> body = response.body();
                    likeStores = ContactShopOC.createContactsList(body);
                    likeStoreAdapter = new LikeStoreAdapter(Glide.with(getActivity()), likeStores);
                } else {
                    likeStores = ContactShopOC._createContactsList(20);
                    likeStoreAdapter = new LikeStoreAdapter(likeStores);
                }
                recyclerShop.addItemDecoration(leftDecoration);
                recyclerShop.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));

                likeStoreAdapter.setOnItemClickListener(holder -> {
                    ShopFragment targetFragment = shopFragment.newInstance(holder.storeId);
                    MainActivity activity = (MainActivity) getActivity();
                    activity.replaceFragment(targetFragment);
                });

                recyclerShop.setAdapter(likeStoreAdapter);
            }

            @Override
            public void onFailure(Call<List<StoreDto>> call, Throwable t) {
                likeStores = ContactShopOC._createContactsList(20);
                likeStoreAdapter = new LikeStoreAdapter(likeStores);
                recyclerShop.addItemDecoration(leftDecoration);
                recyclerShop.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerShop.setAdapter(likeStoreAdapter);
            }
        });

        recyclerStar = view.findViewById(R.id.recycler_starStore);
        storeService.getVotedStoreList().enqueue(new Callback<List<StoreDto>>() {
            @Override
            public void onResponse(Call<List<StoreDto>> call, Response<List<StoreDto>> response) {
                if (response.code() == 201) {
                    List<StoreDto> body = response.body();
                    Log.d("BODY", body.toString());
                    starStores = ContactShopOC.createContactsList(body);
                    starStoreAdapter = new StarStoreAdapter(starStores);
                }
            }
            @Override
            public void onFailure(Call<List<StoreDto>> call, Throwable t) {

            }
        });

//        starStores = StarStore.createLikeStoreList(numStore);
//        starStoreAdapter = new StarStoreAdapter(starStores);

        recyclerStar.addItemDecoration(bottomDecoration);
        recyclerStar.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerStar.setAdapter(starStoreAdapter);

        return view;
    }

    private byte[] getImage() {

        String byteString = sharedPreferences.getString("profileImage", null);

        if (byteString != null) {
            String[] split = byteString.substring(1, byteString.length()-1).split(", ");
            byte[] imgByte = new byte[split.length];
            for (int i = 0; i < split.length; i++) {
                imgByte[i] = Byte.parseByte(split[i]);
            }
            return imgByte;
        }
        return null;
    }

    private String getName() {
        String name = sharedPreferences.getString("nickname", null);
        return name == null ? "" : name;
    }
}
