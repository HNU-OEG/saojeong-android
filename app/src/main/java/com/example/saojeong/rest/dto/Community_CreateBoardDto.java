package com.example.saojeong.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Community_CreateBoardDto {
    @SerializedName("name")
    @Expose
    private String Name;
}
