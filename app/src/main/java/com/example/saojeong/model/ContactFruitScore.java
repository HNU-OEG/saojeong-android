package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;
import java.util.List;

public class ContactFruitScore {
    private String mEvaluate;
    private String mKindscore;
    private String mItemscore;
    private String mPricescore;

    public ContactFruitScore(String mEvaluate, String mKindscore, String mItemscore, String mPricescore) {
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

    public static ArrayList<ContactFruitScore> createContactsList(int numContacts) {
        ArrayList<ContactFruitScore> contacts = new ArrayList<ContactFruitScore>();


        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactFruitScore("1004","4.3","3.2", "1.2"));
        }

        return contacts;
    }
}