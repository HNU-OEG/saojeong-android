package com.example.saojeong.util;

import com.example.saojeong.rest.dto.StoreDto;

import java.util.Comparator;

public class SortedByName implements Comparator<StoreDto> {

    @Override
    public int compare(StoreDto a, StoreDto b) {
        return b.getStoreName().compareTo(a.getStoreName());
    }
}
