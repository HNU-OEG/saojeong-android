package com.example.saojeong.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResultDto {
    @SerializedName("member_id")
    @Expose
    public String memberId;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("nickname")
    @Expose
    public String nickname;
    @SerializedName("type")
    @Expose
    public Integer type;
    @SerializedName("provider")
    @Expose
    public String provider;
    @SerializedName("oauth_version")
    @Expose
    public String oauthVersion;
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;
    @SerializedName("expired_at")
    @Expose
    public Object expiredAt;
    @SerializedName("is_activated")
    @Expose
    public Integer isActivated;
}
