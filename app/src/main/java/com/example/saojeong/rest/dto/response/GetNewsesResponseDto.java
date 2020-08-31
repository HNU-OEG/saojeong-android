package com.example.saojeong.rest.dto.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetNewsesResponseDto {
    @SerializedName("document_id")
    @Expose
    public Integer documentId;
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
