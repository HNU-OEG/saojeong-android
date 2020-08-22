package com.example.saojeong.model;

import com.example.saojeong.R;
import com.example.saojeong.rest.dto.board.ContentDto;

import java.util.ArrayList;
import java.util.List;

public class ContactFullview {

    private int mFvImage;
    private String _mFvImage;
    private int mFvLeft;
    private int mFvRight;

    public ContactFullview(int mFvLeft, int mFvRight, int mFvImage) {
        this.mFvLeft = mFvLeft;
        this.mFvRight = mFvRight;
        this.mFvImage = mFvImage;
    }

    public ContactFullview(ContentDto dto) {
        this.mFvLeft = R.drawable.ar_left;
        this.mFvRight = R.drawable.ar_right;
        this._mFvImage = dto.getTitle();
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
    public String get_mFvImage() {
        return _mFvImage;
    }

    public static List<ContactFullview> _createContactsList(int numContacts) {
        List<ContactFullview> contacts = new ArrayList<>();


        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactFullview(R.drawable.ar_left, R.drawable.ar_right, R.drawable.fullview));
        }
        return contacts;
    }

    public static List<ContactFullview> createContactsList(List<ContentDto> response) {
        List<ContactFullview> contacts = new ArrayList<>();


        for (ContentDto dto : response) {
            contacts.add(new ContactFullview(dto));
        }
        return contacts;
    }
}
