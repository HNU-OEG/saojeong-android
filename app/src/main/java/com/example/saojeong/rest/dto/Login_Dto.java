package com.example.saojeong.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
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
