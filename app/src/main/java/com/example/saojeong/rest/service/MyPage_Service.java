package com.example.saojeong.rest.service;

import com.example.saojeong.rest.dto.mypage.Edit_ProfileDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MyPage_Service {
    @PUT("/admin/api/edit/customers/{member_id}.json")
    Call<List<Edit_ProfileDto>> ModifiedName(@Body Edit_ProfileDto parameters,
                                             @Path("member_id") String id);
}
