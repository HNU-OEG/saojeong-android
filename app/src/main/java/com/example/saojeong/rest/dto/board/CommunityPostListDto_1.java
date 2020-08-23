package com.example.saojeong.rest.dto.board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommunityPostListDto_1 {
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("comment_count")
    @Expose
    public Integer commentCount;
    @SerializedName("voted_count")
    @Expose
    public Integer votedCount;

}
