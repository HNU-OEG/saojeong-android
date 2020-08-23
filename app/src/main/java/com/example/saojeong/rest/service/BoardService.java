package com.example.saojeong.rest.service;

import com.example.saojeong.rest.dto.board.CommunityPostListDto;
import com.example.saojeong.rest.dto.board.CreatePostDto;
import com.example.saojeong.rest.dto.board.GetPostListDto;
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

public interface BoardService {


    //게시글 조회
    @GET("/api/board/{board_id}/content/{document_id}")
    Call<List<CommunityPostListDto>> getPost(@Path("board_id") int board_id, @Path("document_id") int document_id);

    //게시글 리스트 조회
    //URI: [GET, /api/board/{board_id}/content]
    @GET("/api/board/{board_id}/content")
    Call<List<GetPostListDto>> getPostList(@Path("board_id") int board_id);


//    //게시판 생성
//    @POST("/api/board")
//    Call<List<CommunityPostListDto>> createBoard(@Path());

    //게시글 생성
    @POST("/api/board/{board_id}/content")
    Call<CreatePostDto> createPost(@Body CreatePostDto parameters, @Path("board_id") int board_id);

    //게시글삭제
    @DELETE("/api/board/{board_id}/content/{document_id}")
    Call<List<ResponseBody>> deletePost(@Path("board_id") int board_id, @Path("document_id") int document_id);

    //게시글수정
    @PUT("/api/board/{board_id}/content/{document_id}")
    Call<List<CommunityPostListDto>> modifiedPost(@Body ModifiedPostDto parameters, @Path("board_id") int board_id, @Path("document_id") int document_id);

    //게시글 추천비추천
    @PATCH("/api/board/{board_id}/content/{document_id}?type=blame&task=down")
    //Call<List<CommunityPostListDto>> likePost(@Field("type") String title, @Field("task") String down);
    Call<List<CommunityPostListDto>> likePost(@Path("board_id") int board_id, @Path("document_id") int document_id, @Query("type") String type, @Query("task") String task);
}
