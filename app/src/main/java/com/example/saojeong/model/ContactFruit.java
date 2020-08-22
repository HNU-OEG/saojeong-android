package com.example.saojeong.model;

import com.example.saojeong.R;
import com.example.saojeong.rest.dto.SeasonalFoodDto;

import java.util.ArrayList;
import java.util.List;

public class ContactFruit {
    private String mFruit;
    private int mFrImage;
    private String _mFrImage;

    public ContactFruit(String mFruit, int mFrImage) {
        this.mFruit = mFruit;
        this.mFrImage = mFrImage;
    }

    public ContactFruit(SeasonalFoodDto dto) {
        this.mFruit = dto.getName();
        this._mFrImage = dto.getImage();
    }

    public String getmFruit() {
        return mFruit;
    }

    public int getmFrImage() {
        return mFrImage;
    }

    public String get_mFrImage() {return _mFrImage;}

    public static ArrayList<ContactFruit> _createContactsList(int numContacts) {
        ArrayList<ContactFruit> contacts = new ArrayList<ContactFruit>();

        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactFruit("자두", R.drawable.logo_start));
        }
        return contacts;
    }

    public static List<ContactFruit> createContactsList(List<SeasonalFoodDto> response) {
        List<ContactFruit> contacts = new ArrayList<>();

        for (SeasonalFoodDto dto : response) {
            contacts.add(new ContactFruit(dto));
        }
        return contacts;
    }
}
