package com.example.saojeong.model;

import com.example.saojeong.R;
import com.example.saojeong.rest.dto.home.AnnounceDto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContactAnnounce {

    private int mFvImage;
    private String _mFvImage;

    public ContactAnnounce(int mFvImage) {
        this.mFvImage = mFvImage;
    }

    public ContactAnnounce(AnnounceDto dto) {
        this._mFvImage = dto.getImage();
    }

    public static List<ContactAnnounce> _createContactsList(int numContacts) {
        List<ContactAnnounce> contacts = new ArrayList<>();

        for(int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactAnnounce(R.drawable.announce));
        }

        return contacts;
    }

    public static List<ContactAnnounce> createContactsList(List<AnnounceDto> response) {
        List<ContactAnnounce> contacts = new ArrayList<>();

        for(AnnounceDto dto : response) {
            contacts.add(new ContactAnnounce(dto));
        }
        return contacts;
    }
}
