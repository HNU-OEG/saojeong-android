package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;
import java.util.List;

public class ContactFruitClose {
    private String mShopnum;
    private String mShopname;
    private String mStar;
    private Double mStarscore;
    private String mEvaluation;
    private String mSelfintroduction;
    private int mImage;
    private int mFImage;

    public ContactFruitClose(String mShopnum, String mShopname, String mStar, Double mStarscore, String mEvaluation, String mSelfintroduction,int mImage, int mFImage) {
        this.mShopnum = mShopnum;
        this.mShopname = mShopname;
        this.mStar = mStar;
        this.mStarscore = mStarscore;
        this.mEvaluation = mEvaluation;
        this.mSelfintroduction = mSelfintroduction;
        this.mImage = mImage;
        this.mFImage = mFImage;
    }



    public String getmShopnum() {
        return mShopnum;
    }

    public String getmShopname() {
        return mShopname;
    }

    public String getmStar() {
        return mStar;
    }

    public Double getmStarscore() {
        return mStarscore;
    }

    public String getmEvaluation() {
        return mEvaluation;
    }

    public String getmSelfintroduction() {
        return mSelfintroduction;
    }

    public int getmImage() {
        return mImage;
    }

    public int getmFImage() {
        return mFImage;
    }

    public static ArrayList<ContactFruitClose> createContactsList(int numContacts) {
        ArrayList<ContactFruitClose> contacts = new ArrayList<ContactFruitClose>();


        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactFruitClose(i+"번","서진 농산시장","★",4.9,(i+62)+"명이 평가하였습니다.", "오늘 하루도 좋은 일만 ^^*", R.drawable.logo_start, R.drawable.like));
        }

        return contacts;
    }
}
