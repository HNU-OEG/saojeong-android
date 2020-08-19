package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;
import java.util.List;

public class ContactFullview {

    private int mFvImage;
    private int mFvLeft;
    private int mFvRight;

    public ContactFullview(int mFvLeft, int mFvRight, int mFvImage) {
        this.mFvLeft = mFvLeft;
        this.mFvRight = mFvRight;
        this.mFvImage = mFvImage;
    }

    public int getmFvLeft() {
        return mFvLeft;
    }

    public int getmFvRight() {
        return mFvRight;
    }

    public int getmFvImage() {
        return mFvImage;
    }

    public static ArrayList<ContactFullview> createContactsList(int numContacts) {
        ArrayList<ContactFullview> contacts = new ArrayList<ContactFullview>();


        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactFullview(R.drawable.ar_left, R.drawable.ar_right, R.drawable.fullview));
        }

        return contacts;
    }
}
