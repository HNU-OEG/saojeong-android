package com.example.saojeong.rest.service;

import com.example.saojeong.rest.dto.StoreDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StoreService {
    @GET("api/store")
    Call<List<StoreDto>> getStoreListOrderByGrade();

    @GET("api/store/starred")
    Call<List<StoreDto>> getStarredStoreList();
}
