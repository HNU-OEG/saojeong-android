package com.example.saojeong.model;

import com.example.saojeong.R;
import com.example.saojeong.rest.dto.store.StoreDto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContactShopOC {
    private Integer mShopId;
    private String mShopnum;
    private String mShopname;
    private String mStar;
    private Double mStarscore;
    private String mEvaluation;
    private String mSelfintroduction;
    private int mImage;
    private int mFImage;
    private String _mImage;
    private boolean mLike;


    public ContactShopOC(String mShopnum, String mShopname, String mStar, Double mStarscore, String mEvaluation, String mSelfintroduction,int mImage, int mFImage) {
        this.mShopnum = mShopnum;
        this.mShopname = mShopname;
        this.mStar = mStar;
        this.mStarscore = mStarscore;
        this.mEvaluation = mEvaluation;
        this.mSelfintroduction = mSelfintroduction;
        this.mImage = mImage;
        this.mFImage = mFImage;
        this.mLike = true;
    }

    public ContactShopOC(StoreDto dto) {
        this.mShopId = dto.getStoreId();
        this.mShopnum = dto.getStoreNumber() + "번";
        this.mShopname = dto.getStoreName();
        this.mStar = "★";
        this.mStarscore = dto.getVoteGradeAverage();
        this.mEvaluation = dto.getVoteGradeCount() + "명이 평가하였습니다.";
        this.mSelfintroduction = dto.getStoreIntro();
        this.mFImage = R.drawable.like;
        this._mImage = dto.getStoreImage();
        this.mLike = dto.isStarred();
    }

    public static List<ContactShopOC> _createContactsList(int numContacts) {
        List<ContactShopOC> contacts = new ArrayList<>();

        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactShopOC(i+"번","서진 농산시장","★",4.9,(i+62)+"명이 평가하였습니다.", "오늘 하루도 좋은 일만 ^^*", R.drawable.logo_start, R.drawable.like));
        }
        return contacts;
    }

    public static List<ContactShopOC> createContactsList(List<StoreDto> response) {
        List<ContactShopOC> contacts = new ArrayList<>();

        for (StoreDto dto : response) {
            contacts.add(new ContactShopOC(dto));
        }
        return contacts;
    }
}
