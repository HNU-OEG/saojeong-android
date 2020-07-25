package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;

public class LikeStore {
    private int mImage;
    private int mNumStore;
    private String mNameStore;
    private Double mRateStore;
    private int mRateCountStore;
    private boolean mLike;

    public LikeStore(int mImage, int mNumStore, String mNameStore, Double mRateStore, int mRateCountStore, boolean mLike) {
        this.mImage = mImage;
        this.mNumStore = mNumStore;
        this.mNameStore = mNameStore;
        this.mRateStore = mRateStore;
        this.mRateCountStore = mRateCountStore;
        this.mLike = mLike;
    }

    public int getmImage() {
        return mImage;
    }

    public int getmNumStore() {
        return mNumStore;
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
            likeStores.add(getData());
        }

        return likeStores;
    }

    private static LikeStore getData() {
        //서버에서 데이터를 받아온다.//
        int numStore = 72;
        String nameStore = "서진농산시장";
        double rateStore = 4.8;
        int rateCountStore = 82;
        boolean likes = true;

        LikeStore likeStore = new LikeStore(R.drawable.logo_no_circle, numStore, nameStore, rateStore, rateCountStore, likes);

        return likeStore;
    }
}
