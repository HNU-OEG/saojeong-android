package com.example.saojeong.model;

import com.example.saojeong.rest.dto.board.CommentDto;
import com.example.saojeong.rest.dto.board.ContentDto;
import com.example.saojeong.rest.dto.board.GetContentDto;
import com.example.saojeong.rest.dto.board.GetPostDto;
import com.example.saojeong.rest.dto.board.GetPostListDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class PostValue {

    int documentId;
    String title;
    String content;
    String createdAt;
    int votedCount;
    int blamedCount;
    String category;
    String author;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getVotedCount() {
        return votedCount;
    }

    public String getCategory() {
        return category;
    }

    public String getAuthor() {
        return author;
    }

    public int getBlamedCount() {
        return blamedCount;
    }

    public int getDocumentId() {
        return documentId;
    }

    public PostValue(GetContentDto dto) {
        this.documentId = dto.getDocumentId();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.createdAt = dto.getCreatedAt();
        this.blamedCount=dto.getBlamedCount();
        this.votedCount = dto.getVotedCount();
        this.category = dto.getCategory();
        this.author = dto.getAuthor();
    }
}
