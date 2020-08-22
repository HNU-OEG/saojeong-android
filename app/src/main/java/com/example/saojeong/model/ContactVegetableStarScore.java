package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;
import java.util.List;

public class ContactVegetableStarScore {
    private String mQuestion;
    private float mRating = 0;
    public ContactVegetableStarScore(String mQuestion) {
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

    public static ArrayList<ContactVegetableStarScore> createContactsList(int numContacts) {
        ArrayList<ContactVegetableStarScore> contacts = new ArrayList<ContactVegetableStarScore>();

        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactVegetableStarScore("사장님은 친절하신가요?"));
        }

        return contacts;
    }
}