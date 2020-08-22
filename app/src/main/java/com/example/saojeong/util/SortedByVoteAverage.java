package com.example.saojeong.util;

import com.example.saojeong.model.ContactFruitOpen;
import com.example.saojeong.rest.dto.StoreDto;

import java.util.Comparator;

public class SortedByVoteAverage implements Comparator<ContactFruitOpen> {

//    @Override
//    public int compare(StoreDto a, StoreDto b) {
//        return b.getVoteGradeAverage().compareTo(a.getVoteGradeAverage());
//    }

    @Override
    public int compare(ContactFruitOpen a, ContactFruitOpen b) {
        return b.getmStarscore().compareTo(a.getmStarscore().compareTo());
    }
}
