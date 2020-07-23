package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;

public class Contact_Shop {
    private String mShopnum;
    private String mShopname;
    private String mStar;
    private String mStarscore;
    private String mEvaluation;
    private int mImage;

    public Contact_Shop(String mShopnum, String mShopname, String mStar, String mStarscore, String mEvaluation, int mImage) {
        this.mShopnum = mShopnum;
        this.mShopname = mShopname;
        this.mStar = mStar;
        this.mStarscore = mStarscore;
        this.mEvaluation= mEvaluation;
        this.mImage = mImage;
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

   public String getmStarscore() {
        return mStarscore;
    }

   public String getmEvaluation() {
        return mEvaluation;
    }

    public int getmImage() {
        return mImage;
    }

//    private static int lastContactId = 0;

    public static ArrayList<Contact_Shop> createContactsList(int numContacts) {
        ArrayList<Contact_Shop> contacts = new ArrayList<Contact_Shop>();


        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new Contact_Shop(i+"번","서진 농산시장","★","4.9",(i+62)+"명이 평가하였습니다.", R.drawable.logo_start));
        }

        return contacts;
    }
}
