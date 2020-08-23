package com.example.saojeong.rest.dto.board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommunityPostListDto {
    @SerializedName("normal")
    @Expose
    public List<CommunityPostListDto_1> normal = null;
    @SerializedName("hot")
    @Expose
    public List<CommunityPostListDto_1> hot = null;
}
