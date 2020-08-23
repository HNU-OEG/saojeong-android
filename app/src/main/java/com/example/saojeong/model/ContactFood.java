package com.example.saojeong.model;

import com.example.saojeong.R;
import com.example.saojeong.rest.dto.SeasonalFoodDto;

import java.util.ArrayList;
import java.util.List;

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

    public static List<ContactFood> _createContactsList(int numContacts) {
        List<ContactFood> contacts = new ArrayList<>();

        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactFood("자두", R.drawable.logo_start));
        }
        return contacts;
    }

    public static List<ContactFood> createContactsList(List<SeasonalFoodDto> response) {
        List<ContactFood> contacts = new ArrayList<>();

        for (SeasonalFoodDto dto : response) {
            contacts.add(new ContactFood(dto));
        }
        return contacts;
    }
}
