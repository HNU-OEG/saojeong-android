package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;

public class LikeStore {
    private int mImage;
    private int mCodeStore;
    private String mNameStore;
    private Double mRateStore;
    private int mRateCountStore;
    private boolean mLike;

    public LikeStore(int mImage, int mCodeStore, String mNameStore, Double mRateStore, int mRateCountStore, boolean mLike) {
        this.mImage = mImage;
        this.mCodeStore = mCodeStore;
        this.mNameStore = mNameStore;
        this.mRateStore = mRateStore;
        this.mRateCountStore = mRateCountStore;
        this.mLike = mLike;
    }

    public int getmImage() {
        return mImage;
    }

    public int getmCodeStore() {
        return mCodeStore;
    }

    public String getmNameStore() {
        return mNameStore;
    }

    public Double getmRateStore() {
        return mRateStore;
    }

    public int getmRateCountStore() {
        return mRateCountStore;
    }

    public boolean ismLike() {
        return mLike;
    }

    public static ArrayList<LikeStore> createLikeStoreList(int numLikeStore) {
        ArrayList<LikeStore> likeStores = new ArrayList<LikeStore>();

        for (int i = 1; i <= numLikeStore; i++) {
            likeStores.add(MyPageGetData.getLikeStoreData());
            likeStores.add(new LikeStore(R.drawable.logo_no_circle, 52, "서진수산시장", 4.8, 30, false)); //test
        }
        return likeStores;
    }
}
