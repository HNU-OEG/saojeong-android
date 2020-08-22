package com.example.saojeong.rest.dto.board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("member_id")
    @Expose
    public String memberId;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("nickname")
    @Expose
    public String nickname;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("type")
    @Expose
    public Integer type;

}