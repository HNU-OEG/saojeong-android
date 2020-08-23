package com.example.saojeong.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Community_ModifiedBoardDto {
    @SerializedName("title")
    @Expose
    private String Title;
    @SerializedName("content")
    @Expose
    private String Content;

}
