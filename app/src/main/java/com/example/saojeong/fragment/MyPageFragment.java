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
import com.example.saojeong.model.LikeStore;

import java.util.ArrayList;

public class MyPageFragment extends Fragment {

    private RecyclerView recyclerLikes;
    private RecyclerView recyclerStar;
    private LikeStoreAdapter likeStoreAdapter;
    ArrayList<LikeStore> likeStores;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);

        recyclerLikes = view.findViewById(R.id.recycler_likeStore);
        likeStores = LikeStore.createLikeStoreList(5);
        likeStoreAdapter = new LikeStoreAdapter(likeStores);

        recyclerLikes.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerLikes.setAdapter(likeStoreAdapter);

        return view;
    }
}
