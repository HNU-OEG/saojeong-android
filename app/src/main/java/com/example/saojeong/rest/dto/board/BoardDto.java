package com.example.saojeong.rest.dto.board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class BoardDto {
    @SerializedName("content")
    @Expose
    private ContentDto contentDto;
    @SerializedName("comments")
    @Expose
    private List<CommentDto> comments = null;

    @Getter
    @Setter
    @ToString
    class CommentDto {
        @SerializedName("member_id")
        @Expose
        private String memberId;
        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("replies")
        @Expose
        private List<CommentDto> replies = null;
    }
}
