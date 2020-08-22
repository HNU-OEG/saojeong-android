package com.example.saojeong.model;

import android.content.Context;

import com.example.saojeong.R;

import java.util.ArrayList;
import java.util.List;

public class ContactShopScore {
    private String mEvaluate;
    private String mKindscore;
    private String mItemscore;
    private String mPricescore;

    public ContactShopScore(String mEvaluate, String mKindscore, String mItemscore, String mPricescore) {
        this.mEvaluate = mEvaluate;
        this.mKindscore = mKindscore;
        this.mItemscore = mItemscore;
        this.mPricescore = mPricescore;
    }

    public String getmEvaluate() {
        return mEvaluate;
    }

    public String getmKindscore() {
        return mKindscore;
    }

    public String getmItemscore() {
        return mItemscore;
    }

    public String getmPricescore() {
        return mPricescore;
    }


    public static ArrayList<ContactShopScore> createContactsList(int numContacts) {
        ArrayList<ContactShopScore> contacts = new ArrayList<ContactShopScore>();

        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactShopScore("1004","4.7","3.9", "1.8"));
        }

        return contacts;
    }
}