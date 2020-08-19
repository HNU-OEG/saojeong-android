package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;
import java.util.List;

public class ContactVegetable {
    private String mVegetable;
    private int mVeImage;

    public ContactVegetable(String mVegetable, int mVeImage) {
        this.mVegetable = mVegetable;
        this.mVeImage = mVeImage;
    }



    public String getmVegetable() {
        return mVegetable;
    }

    public int getmVeImage() {
        return mVeImage;
    }

    public static ArrayList<ContactVegetable> createContactsList(int numContacts) {
        ArrayList<ContactVegetable> contacts = new ArrayList<ContactVegetable>();


        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactVegetable("양파", R.drawable.logo_start));
        }

        return contacts;
    }
}
