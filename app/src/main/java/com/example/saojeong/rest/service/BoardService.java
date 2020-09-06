package com.example.saojeong.rest.service;

import com.example.saojeong.rest.dto.board.CreateComentDto;
import com.example.saojeong.rest.dto.board.CreatePostDto;
import com.example.saojeong.rest.dto.board.GetPostDto;
import com.example.saojeong.rest.dto.board.GetPostListArrayDto;
import com.example.saojeong.rest.dto.board.GetPostListDto;
import com.example.saojeong.rest.dto.board.ModifiedPostDto;
import com.example.saojeong.rest.dto.board.RepliesDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BoardService {


    //게시글 조회
    //테스트 X
    @GET("/api/board/10004/content/user")
    Call<GetPostListArrayDto> userBoard();

    @GET("/search/?type=board&category=10004")
    Call<GetPostListArrayDto> searchBoard(@Query("value") String search);


    @GET("/api/board/{board_id}/content/{document_id}")
    Call<GetPostDto> getPost(@Path("board_id") int board_id, @Path("document_id") int document_id);

    @GET("/api/board/{board_id}/content")
    Call<List<GetPostListDto>> getNewses(@Path("board_id") int board_id);

    //게시글 리스트 조회
    //테스트 완료
    //URI: [GET, /api/board/{board_id}/content]
    @GET("/api/board/{board_id}/content")
    Call<GetPostListArrayDto> getPostList(@Path("board_id") int board_id);

    //게시글 리스트 조회 + ID
    @GET("/api/board/{board_id}/content/my")
    Call<List<GetPostListDto>> getPostListWithID(@Path("board_id") int board_id);

    //게시글 생성
    //테스트 완료
    @POST("/api/board/{board_id}/content")
    Call<CreatePostDto> createPost(@Body CreatePostDto parameters, @Path("board_id") int board_id);

    //게시글삭제
    //테스트 X
    @DELETE("/api/board/{board_id}/content/{document_id}")
    Call<Void> deletePost(@Path("board_id") int board_id, @Path("document_id") int document_id);

    //게시글수정
    //테스트 X
    @PUT("/api/board/{board_id}/content/{document_id}")
    Call<List<ModifiedPostDto>> modifiedPost(@Body ModifiedPostDto parameters, @Path("board_id") int board_id, @Path("document_id") int document_id);

    //게시글 추천비추천
    //테스트 X
    @PATCH("/api/board/{board_id}/content/{document_id}")
    //Call<List<CommunityPostListDto>> likePost(@Field("type") String title, @Field("task") String down);
    Call<GetPostDto> likePost(@Path("board_id") int board_id, @Path("document_id") int document_id, @Query("type") String type, @Query("task") String task);
//api/board/{category}/content/{document_id}/comment/{comment_id}
    @PUT("/api/board/{board_id}/content/{document_id}/comment/new")
    Call<CreateComentDto> createComment(@Body CreateComentDto parameters, @Path("board_id") int board_id, @Path("document_id") int document_id);

    @PUT("/api/board/{board_id}/content/{document_id}/comment/{comment_id}/new")
    Call<CreateComentDto> createRepliesComment(@Body CreateComentDto parameters, @Path("board_id") int board_id, @Path("document_id") int document_id, @Path("comment_id") int comment_id);
}
