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
import com.example.saojeong.rest.service.StoreService;

import java.util.ArrayList;
import java.util.List;

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


        recyclerStar = view.findViewById(R.id.recycler_starStore);
        starStores = StarStore.createLikeStoreList(numStore);
        starStoreAdapter = new StarStoreAdapter(starStores);

        recyclerStar.addItemDecoration(bottomDecoration);
        recyclerStar.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerStar.setAdapter(starStoreAdapter);

        return view;
    }
}
