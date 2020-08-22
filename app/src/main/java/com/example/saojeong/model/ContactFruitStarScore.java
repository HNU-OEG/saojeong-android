package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;
import java.util.List;

public class ContactFruitStarScore {
    private String mQuestion;
    private float mRating = 0;
    public ContactFruitStarScore(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public float getmRating() {
        return mRating;
    }

    public static ArrayList<ContactFruitStarScore> createContactsList(int numContacts) {
        ArrayList<ContactFruitStarScore> contacts = new ArrayList<ContactFruitStarScore>();


        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactFruitStarScore("사장님은 친절하신가요?"));
        }

        return contacts;
    }
}