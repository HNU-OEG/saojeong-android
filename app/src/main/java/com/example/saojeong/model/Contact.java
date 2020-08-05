package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;

public class Contact {
    private String mShopnum;
    private String mShopname;
    private String mStar;
    private Double mStarscore;
    private String mEvaluation;
    private int mImage;
    private int mFImage;

    public Contact(String mShopnum, String mShopname, String mStar, Double mStarscore, String mEvaluation, int mImage, int mFImage) {
        this.mShopnum = mShopnum;
        this.mShopname = mShopname;
        this.mStar = mStar;
        this.mStarscore = mStarscore;
        this.mEvaluation= mEvaluation;
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

    public int getmImage() {
        return mImage;
    }

    public int getmFImage() {
        return mFImage;
    }

    public static ArrayList<Contact> createContactsList(int numContacts) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();


        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new Contact(i+"번","서진 농산시장","★",4.9,(i+62)+"명이 평가하였습니다.", R.drawable.logo_start, R.drawable.like));
        }

        return contacts;
    }
}
