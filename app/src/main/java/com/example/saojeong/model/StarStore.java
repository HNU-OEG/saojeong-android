package com.example.saojeong.model;

import java.util.ArrayList;
import java.util.List;

public class StarStore {
    private int mCodeStore;
    private String mNameStore;
    private int mRateStore;

    public StarStore(int mCodeStore, String mNameStore, int mRateStore) {
        this.mCodeStore = mCodeStore;
        this.mNameStore = mNameStore;
        this.mRateStore = mRateStore;
    }

    public int getmCodeStore() {
        return mCodeStore;
    }

    public String getmNameStore() {
        return mNameStore;
    }

    public int getmRateStore() {
        return mRateStore;
    }

    public static ArrayList<StarStore> createLikeStoreList(int numStarStore) {
        ArrayList<StarStore> starStores = new ArrayList<StarStore>();

        for (int i = 1; i <= numStarStore; i++) {
            starStores.add(MyPageGetData.getStarStoreData());
            starStores.add(new StarStore(52, "서진수산시장", 2)); //test
        }

        return starStores;
    }
}
