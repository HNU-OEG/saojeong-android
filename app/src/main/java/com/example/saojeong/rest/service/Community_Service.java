package com.example.saojeong.rest.service;

import com.example.saojeong.rest.dto.board.CommunityPostListDto;
import com.example.saojeong.rest.dto.board.CreatePostDto;
import com.example.saojeong.rest.dto.board.ModifiedPostDto;

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


//    //게시글 조회
//    @GET("/api/board/{category}/content/{index}")
//    Call<List<CommunityPostListDto>> getPost(@Path("category") int board_id, @Path("index") int document_id);

    //게시글 리스트 조회
    //URI: [GET, /api/board/{category}/content]
    @GET("/api/board/{category}/content")
    Call<CommunityPostListDto> getPostList(@Path("category") String category);


//    //게시판 생성
//    @POST("/api/board")
//    Call<List<CommunityPostListDto>> createBoard(@Path());

//    //게시글 생성
//    @POST("/api/board/{category}/content")
//    Call<CreatePostDto> createPost(@Body CreatePostDto parameters, @Path("category") int board_id);
//
//    //게시글삭제
//    @DELETE("/api/board/{category}/content/{index}")
//    Call<List<ResponseBody>> deletePost(@Path("category") int board_id, @Path("index") int document_id);
//
//    //게시글수정
//    @PUT("/api/board/{category}/content/{index}")
//    Call<List<CommunityPostListDto>> modifiedPost(@Body ModifiedPostDto parameters, @Path("category") int board_id, @Path("index") int document_id);
//
//    //게시글 추천비추천
//    @PATCH("/api/board/{category}/content/{index}?type=blame&task=down")
//    //Call<List<CommunityPostListDto>> likePost(@Field("type") String title, @Field("task") String down);
//    Call<List<CommunityPostListDto>> likePost(@Path("category") int board_id, @Path("index") int document_id, @Query("type") String type, @Query("task") String task);
}
