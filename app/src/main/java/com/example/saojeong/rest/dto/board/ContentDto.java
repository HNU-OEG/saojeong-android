package com.example.saojeong.rest.dto.board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContentDto {
    @SerializedName("document_id")
    @Expose
    private Integer documentId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("voted_count")
    @Expose
    private Integer votedCount;
    @SerializedName("blamed_count")
    @Expose
    private Integer blamedCount;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("author")
    @Expose
    private String author;
}
