package com.example.saojeong.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Community_CreateBoardPostDto {
    @SerializedName("title")
    @Expose
    private String Title;
    @SerializedName("content")
    @Expose
    private String Content;
}
