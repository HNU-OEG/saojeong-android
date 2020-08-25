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

   }
