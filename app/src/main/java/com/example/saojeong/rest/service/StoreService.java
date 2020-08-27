package com.example.saojeong.rest.service;

import com.example.saojeong.rest.dto.response.StarUnstarResponseDto;
import com.example.saojeong.rest.dto.response.UpdateVoteGradeResponseDto;
import com.example.saojeong.rest.dto.store.StoreDetailDto;
import com.example.saojeong.rest.dto.store.StoreDto;
import com.example.saojeong.rest.dto.TypeStoreDto;
import com.example.saojeong.rest.request.UpdateVoteGradeRequestDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StoreService {
    @GET("api/store")
    Call<List<StoreDto>> getStoreListOrderByGrade();

    @GET("api/store/starred")
    Call<List<StoreDto>> getStarredStoreList();

    @GET("api/store/voted")
    Call<List<StoreDto>> getVotedStoreList();

    @GET("api/store/type/{type}/orderby/{orderby}")
    Call<TypeStoreDto> getTypeStore(@Path("type") String type,
                                    @Path("orderby") String orderby);

    @GET("api/store/{id}")
    Call<StoreDetailDto> getStoreDetail(@Path("id") int id);

    //    localhost:3000/api/store/1/votegrade
    @PUT("api/store/{id}/votegrade")
    Call<UpdateVoteGradeResponseDto> updateVoteGrade(@Path("id") int id,
                                                     @Body UpdateVoteGradeRequestDto ratings);

    @POST("api/store/{id}/star")
    Call<StarUnstarResponseDto> startThisStore(@Path("id") int id);

    @DELETE("api/store/{id}/star")
    Call<StarUnstarResponseDto> unstartThisStore(@Path("id") int id);
}
