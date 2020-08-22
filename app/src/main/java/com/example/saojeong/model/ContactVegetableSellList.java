package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;

public class ContactVegetableSellList {
    private String mItemname;
    private String mWeight1;
    private String mWeight2;
    private String mWeight3;
    private String mPrice1;
    private String mPrice2;
    private int mFImage;

    public ContactVegetableSellList(String mItemname, String mWeight1, String mWeight2, String mWeight3, String mPrice1, String mPrice2, int mFImage) {
        this.mItemname = mItemname;
        this.mWeight1 = mWeight1;
        this.mWeight2 = mWeight2;
        this.mWeight3 = mWeight3;
        this.mPrice1 = mPrice1;
        this.mPrice2 = mPrice2;
        this.mFImage = mFImage;
    }


    public String getmItemname() {
        return mItemname;
    }

    public String getmWeight1() {
        return mWeight1;
    }

    public String getmWeight2() {
        return mWeight2;
    }

    public String getmWeight3() {
        return mWeight3;
    }

    public String getmPrice1() {
        return mPrice1;
    }

    public String getmPrice2() {
        return mPrice2;
    }

    public int getmIFmage() {
        return mFImage;
    }

    public static ArrayList<ContactVegetableSellList> createContactsList(int numContacts) {
        ArrayList<ContactVegetableSellList> contacts = new ArrayList<ContactVegetableSellList>();


        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactVegetableSellList("딸기","1kg","2kg","박스당","10000원", "20000원", R.drawable.logo_start));
        }

        return contacts;
    }
}