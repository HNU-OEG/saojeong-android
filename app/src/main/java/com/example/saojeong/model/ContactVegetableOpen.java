package com.example.saojeong.model;

import com.example.saojeong.R;
import com.example.saojeong.rest.dto.StoreDto;

import java.util.ArrayList;
import java.util.List;

public class ContactVegetableOpen {
    private String mShopnum;
    private String mShopname;
    private String mStar;
    private Double mStarscore;
    private String mEvaluation;
    private String mSelfintroduction;
    private int mImage;
    private int mFImage;
    private String _mImage;

    public ContactVegetableOpen(String mShopnum, String mShopname, String mStar, Double mStarscore, String mEvaluation, String mSelfintroduction,int mImage, int mFImage) {
        this.mShopnum = mShopnum;
        this.mShopname = mShopname;
        this.mStar = mStar;
        this.mStarscore = mStarscore;
        this.mEvaluation = mEvaluation;
        this.mSelfintroduction = mSelfintroduction;
        this.mImage = mImage;
        this.mFImage = mFImage;
    }

    public ContactVegetableOpen(StoreDto dto) {
        this.mShopnum = dto.getStoreNumber() + "번";
        this.mShopname = dto.getStoreName();
        this.mStar = "★";
        this.mStarscore = dto.getVoteGradeAverage();
        this.mEvaluation = dto.getVoteGradeCount() + "명이 평가하였습니다.";
        this.mSelfintroduction = dto.getStoreIntro();
        this._mImage = dto.getStoreImage();
        this.mFImage = R.drawable.like;
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

    public String get_mImage() {
        return _mImage;
    }

    public static List<ContactVegetableOpen> _createContactsList(int numContacts) {
        List<ContactVegetableOpen> contacts = new ArrayList<ContactVegetableOpen>();

        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactVegetableOpen(i+"번","서진 농산시장","★",4.9,(i+62)+"명이 평가하였습니다.", "오늘 하루도 좋은 일만 ^^*", R.drawable.logo_start, R.drawable.like));
        }
        return contacts;
    }

    public static List<ContactVegetableOpen> createContactsList(List<StoreDto> response) {
        List<ContactVegetableOpen> contacts = new ArrayList<ContactVegetableOpen>();

        for (StoreDto dto : response) {
            contacts.add(new ContactVegetableOpen(dto));
        }
        return contacts;
    }


}
