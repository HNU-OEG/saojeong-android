package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;

public class ContactFood {
    private String mFood;
    private int mFrImage;

    public ContactFood(String mFood, int mFrImage) {
        this.mFood = mFood;
        this.mFrImage = mFrImage;
    }



    public String getmFood() {
        return mFood;
    }

    public int getmFrImage() {
        return mFrImage;
    }

    public static ArrayList<ContactFood> createContactsList(int numContacts) {
        ArrayList<ContactFood> contacts = new ArrayList<ContactFood>();


        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactFood("자두", R.drawable.logo_start));
        }

        return contacts;
    }
}
