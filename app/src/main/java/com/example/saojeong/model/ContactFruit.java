package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;
import java.util.List;

public class ContactFruit {
    private String mFruit;
    private int mFrImage;

    public ContactFruit(String mFruit, int mFrImage) {
        this.mFruit = mFruit;
        this.mFrImage = mFrImage;
    }



    public String getmFruit() {
        return mFruit;
    }

    public int getmFrImage() {
        return mFrImage;
    }

    public static ArrayList<ContactFruit> createContactsList(int numContacts) {
        ArrayList<ContactFruit> contacts = new ArrayList<ContactFruit>();


        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactFruit("자두", R.drawable.logo_start));
        }

        return contacts;
    }
}
