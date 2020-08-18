package com.example.saojeong.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.saojeong.R;
import com.example.saojeong.rest.dto.StoreDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class LikeStore {
    private int mImage;
    private int mCodeStore;
    private String mNameStore;
    private Double mRateStore;
    private int mRateCountStore;
    private boolean mLike;

    private String _mImage;

    public LikeStore(StoreDto dto) {
        this._mImage = dto.getStoreImage();
        this.mCodeStore = dto.getStoreNumber();
        this.mNameStore = dto.getStoreName();
        this.mRateStore = dto.getVoteGradeAverage();
        this.mRateCountStore = dto.getVoteGradeCount();
        this.mLike = dto.isStarred();
    }

    public LikeStore(int mImage, int mCodeStore, String mNameStore, Double mRateStore, int mRateCountStore, boolean mLike) {
        this.mImage = mImage;
        this.mCodeStore = mCodeStore;
        this.mNameStore = mNameStore;
        this.mRateStore = mRateStore;
        this.mRateCountStore = mRateCountStore;
        this.mLike = mLike;
    }

    public static ArrayList<LikeStore> createLikeStoreList(int numLikeStore) {
        ArrayList<LikeStore> likeStores = new ArrayList<LikeStore>();

        for (int i = 1; i <= numLikeStore; i++) {
            likeStores.add(MyPageGetData.getLikeStoreData());
            likeStores.add(new LikeStore(R.drawable.logo_no_circle, 52, "서진수산시장", 4.8, 30, false)); //test
        }

        return likeStores;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<LikeStore> _createLikeStoreList(List<StoreDto> response) {
        return response.stream()
                .map(LikeStore::new)
                .collect(Collectors.toList());
    }


}
