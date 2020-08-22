package com.example.saojeong.rest.dto;

import com.example.saojeong.rest.dto.board.LoginResultDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Login_Dto {
    @SerializedName("result")
    @Expose
    public List<LoginResultDto> result = null;

    @SerializedName("AccessToken")
    @Expose
    public String accessToken;
    @SerializedName("RefreshToken")
    @Expose
    public String refreshToken;

}
