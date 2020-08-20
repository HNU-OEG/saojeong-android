package com.example.saojeong.model;

import com.example.saojeong.R;
import com.example.saojeong.rest.dto.SeasonalFoodDto;

import java.util.ArrayList;
import java.util.List;

public class ContactFish {
    private String mFish;
    private int mFiImage;
    private String _mFiImage;

    public ContactFish(String mFish, int mFiImage) {
        this.mFish = mFish;
        this.mFiImage = mFiImage;
    }

    public ContactFish(SeasonalFoodDto dto) {
        this.mFish = dto.getName();
        this._mFiImage = dto.getImage();
    }

    public String getmFish() {
        return mFish;
    }

    public int getmFiImage() {
        return mFiImage;
    }

    public String get_mFiImage() {
        return _mFiImage;
    }

    public static List<ContactFish> _createContactsList(int numContacts) {
        List<ContactFish> contacts = new ArrayList<>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactFish("고등어", R.drawable.logo_start));
        }
        return contacts;
    }

    public static List<ContactFish> createContactsList(List<SeasonalFoodDto> response) {
        List<ContactFish> contacts = new ArrayList<>();

        for (SeasonalFoodDto dto : response) {
            contacts.add(new ContactFish(dto));
        }
        return contacts;
    }
}
