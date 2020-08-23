package com.example.saojeong.rest.dto.board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class GetPostListDto {
    @SerializedName("document_id")
    @Expose
    private Integer documentId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("voted_count")
    @Expose
    private Integer votedCount;

}
