package com.example.saojeong.rest.service;

import com.example.saojeong.rest.dto.CommunityDto;
import com.example.saojeong.rest.dto.Community_CreateBoardDto;
import com.example.saojeong.rest.dto.Community_CreateBoardPostDto;
import com.example.saojeong.rest.dto.Community_ModifiedBoardDto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Comunity_Service {

    //게시글조회
    @GET("/api/board/10001/content")
    Call<List<CommunityDto>> GetPost();

    //게시글리스트조회
    @GET("/api/board/10000/content")
    Call<List<CommunityDto>> GetPostList();

    //게시판 생성
    @POST("/api/board")
    Call<List<Community_CreateBoardDto>> CreateBoard();

    //게시글 생성
    @POST("/api/board/10000/content")
    Call<List<Community_CreateBoardPostDto>> CreatePost(@FieldMap Community_CreateBoardPostDto parameters);

    //게시글삭제
    @DELETE("/api/board/10000/content/10")
    Call<List<ResponseBody>> DeletePost(@Body CommunityDto parameters);

    //게시글수정
    @PUT("/api/board/10000/content/6")
    Call<List<CommunityDto>> ModifiedPost(@Body Community_ModifiedBoardDto parameters);

    //게시글 추천비추천
    @PATCH("/api/board/10000/content/18?type=blame&task=down")
    Call<List<CommunityDto>> RecommendPost(@Field("type") String title, @Field("task") String down);


}
