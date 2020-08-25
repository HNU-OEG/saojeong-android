package com.example.saojeong.model;

import com.example.saojeong.R;
import com.example.saojeong.rest.dto.store.StoreDetailDto;
import com.example.saojeong.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContactShopDetail {
    // 핸드폰 번호가 가변적이어야함
    // 0개, 1개일 때와 3개 이상일 때도 동작할 수 있도록....
    private String mTime;
    private String mPhoneN1;
    private String mPhoneN2;

    public ContactShopDetail(String mTime, String mPhoneN1, String mPhoneN2) {
        this.mTime = mTime;
        this.mPhoneN1 = mPhoneN1;
        this.mPhoneN2 = mPhoneN2;

    }

    public ContactShopDetail(StoreDetailDto.StoreDetail dto) {
        List<StoreDetailDto.StoreDetail.Opening> times = dto.getOpening();
        List<String> _mTime = new ArrayList<>();
        for (StoreDetailDto.StoreDetail.Opening time : times) {
            _mTime.add(time.getJoinedString());
        }
        mTime = StringUtils.joiner("\n", _mTime).trim();

        List<String> phones =
                Arrays.asList(dto.getTelephone().split(","));
        if (phones.size() >= 2) {
            this.mPhoneN1 = phones.get(0);
            this.mPhoneN2 = phones.get(1);
        }
        this.mPhoneN1 = phones.get(0);
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

    public static List<ContactShopDetail> _createContactsList(int numContacts) {
        List<ContactShopDetail> contacts = new ArrayList<>();
        contacts.add(new ContactShopDetail(
                "월요일 09:00 ~ 18:00\n" +
                        "화요일 09:00 ~ 18:00\n" +
                        "수요일 09:00 ~ 18:00\n" +
                        "목요일 09:00 ~ 18:00\n" +
                        "금요일 09:00 ~ 18:00\n" +
                        "토요일 09:00 ~ 18:00\n" +
                        "일요일 09:00 ~ 18:00",
                "010-8995-1757",
                "010-1234-4123"));

        return contacts;
    }

    public static List<ContactShopDetail> createContactsList(StoreDetailDto.StoreDetail dto) {
        List<ContactShopDetail> contacts = new ArrayList<>();
        contacts.add(new ContactShopDetail(dto));
        return contacts;
    }
}