package com.example.saojeong.model;

import android.content.Context;

import com.example.saojeong.R;

import java.util.ArrayList;
import java.util.List;

public class ContactVegetableScore {
    private String mEvaluate;
    private String mKindscore;
    private String mItemscore;
    private String mPricescore;

    public ContactVegetableScore(String mEvaluate, String mKindscore, String mItemscore, String mPricescore) {
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


    public static ArrayList<ContactVegetableScore> createContactsList(int numContacts) {
        ArrayList<ContactVegetableScore> contacts = new ArrayList<ContactVegetableScore>();

        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactVegetableScore("1004","4.7","3.9", "1.8"));
        }

        return contacts;
    }
}