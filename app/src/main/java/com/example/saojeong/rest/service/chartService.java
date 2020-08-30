package com.example.saojeong.rest.service;

import com.example.saojeong.rest.dto.board.CommunityPostListDto;
import com.example.saojeong.rest.dto.chart.chart_Dto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface chartService {

    @GET("/market-price/{category_id}")
    Call<List<chart_Dto>>getChartinfo(@Path("category_id") String category);


}
