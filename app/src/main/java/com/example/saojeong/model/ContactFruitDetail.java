package com.example.saojeong.model;

import com.example.saojeong.R;

import java.util.ArrayList;
import java.util.List;

public class ContactFruitDetail {
    private String mTime;
    private String mPhoneN1;
    private String mPhoneN2;


    public ContactFruitDetail(String mTime, String mPhoneN1, String mPhoneN2) {
        this.mTime = mTime;
        this.mPhoneN1 = mPhoneN1;
        this.mPhoneN2 = mPhoneN2;

    }

    public String getmTime() {
        return mTime;
    }

    public String getmPhoneN1() {
        return mPhoneN1;
    }

    public String getmPhoneN2() {
        return mPhoneN2;
    }

    public static ArrayList<ContactFruitDetail> createContactsList(int numContacts) {
        ArrayList<ContactFruitDetail> contacts = new ArrayList<ContactFruitDetail>();


        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactFruitDetail("월요일 09:00 ~ 18:00\n화요일 09:00 ~ 18:00\n수요일 09:00 ~ 18:00\n목요일 09:00 ~ 18:00\n금요일 09:00 ~ 18:00\n토요일 09:00 ~ 18:00\n일요일 09:00 ~ 18:00","010-8995-1757","010-1234-4123"));
        }

        return contacts;
    }
}