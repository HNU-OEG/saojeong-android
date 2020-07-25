package com.example.saojeong.model;

import com.example.saojeong.R;

//서버에서 데이터를 받아온다.//
public class MyPageGetData {

    static int codeStore = 72;
    static String nameStore = "서진농산시장";
    static double rateStore = 4.8;
    static int rateCountStore = 82;
    static boolean likes = true;
    
    static int numStore = 5;

    public static int getNumStore() {
        return numStore;
    }

    protected static LikeStore getLikeStoreData() {
        return new LikeStore(R.drawable.logo_no_circle, codeStore, nameStore, rateStore, rateCountStore, likes);
    }

    protected static StarStore getStarStoreData() {
        return new StarStore(codeStore, nameStore, (int)rateStore);
    }
}
