package com.example.saojeong.model;

import com.example.saojeong.R;
import com.example.saojeong.rest.dto.SeasonalFoodDto;

import java.util.ArrayList;
import java.util.List;

public class ContactVegetable {
    private String mVegetable;
    private int mVeImage;
    private String _mVeImage;

    public ContactVegetable(String mVegetable, int mVeImage) {
        this.mVegetable = mVegetable;
        this.mVeImage = mVeImage;
    }

    public ContactVegetable(SeasonalFoodDto dto) {
        this.mVegetable = dto.getName();
        this._mVeImage = dto.getImage();
    }

    public String getmVegetable() {
        return mVegetable;
    }

    public int getmVeImage() {
        return mVeImage;
    }

    public String get_mVeImage() {
        return _mVeImage;
    }

    public static List<ContactVegetable> _createContactsList(int numContacts) {
        List<ContactVegetable> contacts = new ArrayList<>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactVegetable("양파", R.drawable.logo_start));
        }
        return contacts;
    }

    public static List<ContactVegetable> createContactsList(List<SeasonalFoodDto> response) {
        List<ContactVegetable> contacts = new ArrayList<>();

        for (SeasonalFoodDto dto : response) {
            contacts.add(new ContactVegetable(dto));
        }
        return contacts;
    }
}
