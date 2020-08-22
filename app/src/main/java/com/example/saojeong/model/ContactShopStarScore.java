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

    public String getmQuestion() {
        return mQuestion;
    }

    public float getmRating() {
        return mRating;
    }

    public void setmRating(float mRating) {
        this.mRating = mRating;
    }

    public static ArrayList<ContactShopStarScore> createContactsList(int numContacts) {
        ArrayList<ContactShopStarScore> contacts = new ArrayList<ContactShopStarScore>();

        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactShopStarScore("사장님은 친절하신가요?"));
        }

        return contacts;
    }
}