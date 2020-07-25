package com.example.saojeong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.adapter.LikeStoreAdapter;
import com.example.saojeong.adapter.StarStoreAdapter;
import com.example.saojeong.model.LikeStore;
import com.example.saojeong.model.MyPageGetData;
import com.example.saojeong.model.RecyclerDecoration;
import com.example.saojeong.model.StarStore;

import java.util.ArrayList;

public class MyPageFragment extends Fragment {

    private RecyclerView recyclerLikes;
    private RecyclerView recyclerStar;
    private LikeStoreAdapter likeStoreAdapter;
    private StarStoreAdapter starStoreAdapter;

    ArrayList<LikeStore> likeStores;
    ArrayList<StarStore> starStores;

    RecyclerDecoration.LeftDecoration leftDecoration = new RecyclerDecoration.LeftDecoration(15);
    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(10);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int numStore = MyPageGetData.getNumStore();

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);

        recyclerLikes = view.findViewById(R.id.recycler_likeStore);
        likeStores = LikeStore.createLikeStoreList(numStore);
        likeStoreAdapter = new LikeStoreAdapter(likeStores);

        recyclerLikes.addItemDecoration(leftDecoration);
        recyclerLikes.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerLikes.setAdapter(likeStoreAdapter);


        recyclerStar = view.findViewById(R.id.recycler_starStore);
        starStores = StarStore.createLikeStoreList(numStore);
        starStoreAdapter = new StarStoreAdapter(starStores);

        recyclerStar.addItemDecoration(leftDecoration);
        recyclerStar.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerStar.setAdapter(starStoreAdapter);

        return view;
    }
}
