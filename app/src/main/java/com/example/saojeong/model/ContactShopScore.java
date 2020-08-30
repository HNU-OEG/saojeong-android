package com.example.saojeong.model;

import android.content.Context;

import com.example.saojeong.R;
import com.example.saojeong.rest.dto.store.StoreDetailDto;

import java.util.ArrayList;
import java.util.List;

public class ContactShopScore {
    private String mEvaluate;
    private String mKindscore;
    private String mItemscore;
    private String mPricescore;
    private float userKindScore;
    private float userItemScore;
    private float userPriceScore;

    public ContactShopScore(String mEvaluate, String mKindscore, String mItemscore, String mPricescore) {
        this.mEvaluate = mEvaluate;
        this.mKindscore = mKindscore;
        this.mItemscore = mItemscore;
        this.mPricescore = mPricescore;
    }

    public ContactShopScore(StoreDetailDto.StoreGrade dto, Integer count) {
        this.mEvaluate = count.toString();
        this.mKindscore = dto.getKindnessAverage().toString();
        this.mItemscore = dto.getMerchandiseAverage().toString();
        this.mPricescore = dto.getPriceAverage().toString();
        this.userKindScore = dto.getMyKindness().floatValue();
        this.userItemScore = dto.getMyMerchandise().floatValue();
        this.userPriceScore = dto.getMyPrice().floatValue();
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

    public float getUserKindScore() {
        return userKindScore;
    }

    public float getUserItemScore() {
        return userItemScore;
    }

    public float getUserPriceScore() {
        return userPriceScore;
    }

    public static List<ContactShopScore> _createContactsList(int numContacts) {
        List<ContactShopScore> contacts = new ArrayList<>();

        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactShopScore("1004","4.7","3.9", "1.8"));
        }
        return contacts;
    }

    public static List<ContactShopScore> createContactsList(StoreDetailDto.StoreGrade dto, Integer count) {
        List<ContactShopScore> contacts = new ArrayList<>();
        contacts.add(new ContactShopScore(dto, count));
        return contacts;
    }
}