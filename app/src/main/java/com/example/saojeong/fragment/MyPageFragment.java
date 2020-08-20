package com.example.saojeong.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
import com.example.saojeong.model.LikeStore;
import com.example.saojeong.model.MyPageGetData;
import com.example.saojeong.model.RecyclerDecoration;
import com.example.saojeong.model.StarStore;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.StoreDto;
import com.example.saojeong.rest.service.StoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageFragment extends Fragment {

    private RecyclerView recyclerLikes;
    private RecyclerView recyclerStar;
    private LikeStoreAdapter likeStoreAdapter;
    private StarStoreAdapter starStoreAdapter;

    List<LikeStore> likeStores;
    ArrayList<StarStore> starStores;

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

        int numStore = MyPageGetData.getNumStore();

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);

        btn_profile = view.findViewById(R.id.btn_profile);
        btn_profile.setOnClickListener((v) -> {
            ((MainActivity) getActivity()).replaceFragment(ProfileFragment.newInstance());
        });

        recyclerLikes = view.findViewById(R.id.recycler_likeStore);
        loadStores(this);


        recyclerStar = view.findViewById(R.id.recycler_starStore);
        starStores = StarStore.createLikeStoreList(numStore);
        starStoreAdapter = new StarStoreAdapter(starStores);

        recyclerStar.addItemDecoration(bottomDecoration);
        recyclerStar.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerStar.setAdapter(starStoreAdapter);

        return view;
    }

    private void loadStores(MyPageFragment myPageFragment) {
        storeService.getStarredStoreList().enqueue(new Callback<List<StoreDto>>() {
            @Override
            public void onResponse(Call<List<StoreDto>> call, Response<List<StoreDto>> response) {
                if (response.isSuccessful()) {
                    likeStores = LikeStore.createLikeStoreList(Objects.requireNonNull(response.body()));
                    likeStoreAdapter = new LikeStoreAdapter(Glide.with(myPageFragment), likeStores);
                    recyclerLikes.addItemDecoration(leftDecoration);
                    recyclerLikes.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                    recyclerLikes.setAdapter(likeStoreAdapter);
                } else {
                    likeStores = LikeStore._createLikeStoreList(20);
                    likeStoreAdapter = new LikeStoreAdapter(likeStores);
                    recyclerLikes.addItemDecoration(leftDecoration);
                    recyclerLikes.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                    recyclerLikes.setAdapter(likeStoreAdapter);
                    Log.d(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<List<StoreDto>> call, Throwable t) {
                likeStores = LikeStore._createLikeStoreList(20);
                likeStoreAdapter = new LikeStoreAdapter(likeStores);
                recyclerLikes.addItemDecoration(leftDecoration);
                recyclerLikes.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recyclerLikes.setAdapter(likeStoreAdapter);
                Log.d(TAG, t.getMessage());
            }
        });
    }
}
