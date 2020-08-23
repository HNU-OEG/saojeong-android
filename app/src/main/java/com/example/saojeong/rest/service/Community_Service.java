package com.example.saojeong.rest.service;

import com.example.saojeong.rest.dto.board.CommunityPostListDto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Community_Service {


    @GET("/api/board/10001/content")
    Call<List<CommunityPostListDto>> GetPost();

    //URI: [GET, /api/board/{category}/content]
    @GET("/api/board/{category}/content")
    Call<CommunityPostListDto> getPostList(@Path("category") String category);


    //게시판 생성
    @POST("/api/board")
    Call<List<CommunityPostListDto>> CreateBoard();

    //게시글 생성
    @POST("/api/board/10000/content")
    Call<List<CommunityPostListDto>> CreatePost(@Query("title")String title);

    //게시글삭제
    @DELETE("/api/board/10000/content/10")
    Call<List<ResponseBody>> DeletePost(@Body CommunityPostListDto parameters);

    //게시글수정
    @PUT("/api/board/10000/content/6")
    Call<List<CommunityPostListDto>> ModifiedPost(@Body CommunityPostListDto parameters);

    //게시글 추천비추천
    @PATCH("/api/board/10000/content/18?type=blame&task=down")
    Call<List<CommunityPostListDto>> LikePost(@Field("type") String title, @Field("task") String down);
}
