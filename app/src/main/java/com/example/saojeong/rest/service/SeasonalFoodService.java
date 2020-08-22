package com.example.saojeong.rest.service;

import com.example.saojeong.rest.dto.SeasonalFoodDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SeasonalFoodService {
    @GET("api/foods/type/{type}/moth/{month}")
    Call<List<SeasonalFoodDto>> getSeasonalFood(@Path("type") String type,
                                                @Path("month") int month);
}
