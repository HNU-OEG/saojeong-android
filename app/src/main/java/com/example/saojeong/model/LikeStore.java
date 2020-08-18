package com.example.saojeong.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.saojeong.R;
import com.example.saojeong.rest.dto.StoreDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class LikeStore {
    private String mImage;
    private int mCodeStore;
    private String mNameStore;
    private Double mRateStore;
    private int mRateCountStore;
    private boolean mLike;

    public LikeStore(StoreDto dto) {
        this.mImage = dto.getStoreImage();
        this.mCodeStore = dto.getStoreNumber();
        this.mNameStore = dto.getStoreName();
        this.mRateStore = dto.getVoteGradeAverage();
        this.mRateCountStore = dto.getVoteGradeCount();
        this.mLike = dto.isStarred();
    }

    //
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<LikeStore> createLikeStoreList(List<StoreDto> response) {
        return response.stream()
                .map(LikeStore::new)
                .collect(Collectors.toList());
    }


}
