package com.example.saojeong.rest.service;

import com.example.saojeong.rest.dto.board.ContentDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BoardService {
    @GET("api/board/{category}/content")
    Call<List<ContentDto>> getBoardList(@Path(value = "category", encoded = false) int category);

}
