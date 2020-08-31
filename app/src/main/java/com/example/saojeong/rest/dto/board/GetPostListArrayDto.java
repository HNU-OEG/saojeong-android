package com.example.saojeong.rest.dto.board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

@Getter
public class GetPostListArrayDto {
    @SerializedName("normal")
    @Expose
    public List<GetPostListDto> normal = null;
    @SerializedName("hot")
    @Expose
    public List<GetPostListDto> hot = null;
}
