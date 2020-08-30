package com.example.saojeong.rest.service;

import com.example.saojeong.rest.dto.Login_Dto;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Login_Guest {
    @POST("admin/api/guest/customers.json")
    Call<Login_Dto> CreateLogin();

    @GET("auth/facebook/token")
    Observable<Login_Dto> FaceBookLogin();

    @GET("auth/kakao/token")
    Observable<Login_Dto> kakaoLogin(@Query("access_token") String title);

    @GET("auth/naver/token")
    Observable<Login_Dto> NaverLogin();

    @GET("auth/google/token")
    Observable<Login_Dto> GoogleLogin();

    @PUT("auth/login")
    Observable<Login_Dto> UpdateToken(@Body HashMap refreshToken);
    @GET("hello")
    Call<Login_Dto> hellotest();

    @PUT("admin/api/edit/customers/{member_id}.json")
    Observable<Login_Dto> editUserNickname(@Body HashMap nickname, @Path("member_id") String member_id);


    @DELETE("admin/api/remove/guest/{member_id}.json")
    Observable<Login_Dto> DeleteUser(@Path("member_id") String member_id);
}
