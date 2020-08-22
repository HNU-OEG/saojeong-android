package com.example.saojeong.rest.service;

import com.example.saojeong.rest.dto.Login_GuestDto;
import com.example.saojeong.rest.dto.board.ContentDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Login_Guest {
    @POST("api/guest/customers.json")
    Call<Login_GuestDto> CreateLogin();
}
