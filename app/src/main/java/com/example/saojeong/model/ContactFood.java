package com.example.saojeong.model;

import com.example.saojeong.R;
import com.example.saojeong.rest.dto.SeasonalFoodDto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContactFood {
    private String mFood;
    private int mFrImage;
    private String _mFrImage;

    public ContactFood(String mFood, int mFrImage) {
        this.mFood = mFood;
        this.mFrImage = mFrImage;
    }

    public ContactFood(SeasonalFoodDto dto) {
        this.mFood = dto.getName();
        this._mFrImage = dto.getImage();
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
