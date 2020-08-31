package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;
import java.util.List;

public class ContactShopStarScore {
    private String mQuestion;
    private float mRating = 0;
    public ContactShopStarScore(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public ContactShopStarScore(String mQuestion, float rating) {
        this.mQuestion = mQuestion;
        this.mRating = rating;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public float getmRating() {
        return mRating;
    }

    public void setmRating(float mRating) {
        this.mRating = mRating;
    }

    public static ArrayList<ContactShopStarScore> createContactsList() {
        ArrayList<ContactShopStarScore> contacts = new ArrayList<>();

        contacts.add(new ContactShopStarScore("사장님은 친절하신가요?"));
        contacts.add(new ContactShopStarScore("상품의 품질은 어땠나요?"));
        contacts.add(new ContactShopStarScore("품질 대비 가격은 어땠나요?"));

        return contacts;
    }

    public static ArrayList<ContactShopStarScore> createContactsList(float kind, float item, float price) {
        ArrayList<ContactShopStarScore> contacts = new ArrayList<>();

        contacts.add(new ContactShopStarScore("사장님은 친절하신가요?", kind));
        contacts.add(new ContactShopStarScore("상품의 품질은 어땠나요?", item));
        contacts.add(new ContactShopStarScore("품질 대비 가격은 어땠나요?", price));

        return contacts;
    }
}