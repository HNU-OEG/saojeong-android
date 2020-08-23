package com.example.saojeong.rest.service;

import com.example.saojeong.rest.dto.Login_Dto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Login_Guest {
    @POST("admin/api/guest/customers.json")
    Call<Login_Dto> CreateLogin();

    @GET("auth/facebook/token")
    Call<Login_Dto> FaceBookLogin();

    @GET("auth/kakao/token")
    Call<Login_Dto> kakaoLogin(@Query("access_token") String title);

    @GET("auth/naver/token")
    Call<Login_Dto> NaverLogin();

    @GET("auth/google/token")
    Call<Login_Dto> GoogleLogin();

    @GET("hello")
    Call<Login_Dto> hellotest();

}
