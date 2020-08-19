package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;
import java.util.List;

public class ContactFish {
    private String mFish;
    private int mFiImage;

    public ContactFish(String mFish, int mFiImage) {
        this.mFish = mFish;
        this.mFiImage = mFiImage;
    }



    public String getmFish() {
        return mFish;
    }

    public int getmFiImage() {
        return mFiImage;
    }

    public static ArrayList<ContactFish> createContactsList(int numContacts) {
        ArrayList<ContactFish> contacts = new ArrayList<ContactFish>();


        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactFish("고등어", R.drawable.logo_start));
        }

        return contacts;
    }
}
