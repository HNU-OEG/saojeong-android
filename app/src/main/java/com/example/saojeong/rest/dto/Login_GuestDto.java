package com.example.saojeong.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Login_GuestDto {

    @SerializedName("result")
    @Expose
    public List<Login_Dto> result = null;
    @SerializedName("AccessToken")
    @Expose
    public String accessToken;
    @SerializedName("RefreshToken")
    @Expose
    public String refreshToken;

}