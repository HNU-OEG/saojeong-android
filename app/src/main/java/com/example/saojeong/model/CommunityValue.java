package com.example.saojeong.model;


import com.example.saojeong.rest.dto.board.CommunityPostListDto_1;
import com.example.saojeong.rest.dto.board.GetPostListDto;

import java.util.ArrayList;
import java.util.List;

public class CommunityValue {

    public int document_id;
    public String title;
    public String author;
    public String createdAt;
    public int commentCount;
    public int votedCount;

    public CommunityValue(String title, String author, String createdAt, int commentCount, int votedCount, int document_id)
    {
        this.title=title;
        this.author=author;
        this.createdAt=createdAt;
        this.commentCount=commentCount;
        this.votedCount=votedCount;
        this.document_id=document_id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getVotedCount() {
        return votedCount;
    }

    public int getDocument_id() {
        return document_id;
    }

    public CommunityValue(GetPostListDto dto) {
        this.title = dto.getTitle();
        this.author = dto.getAuthor();
        this.createdAt = dto.getCreatedAt();
        this.commentCount = dto.getCommentCount();
        this.votedCount = dto.getVotedCount();
        this.document_id=dto.getDocumentId();
    }

    public static List<CommunityValue> createContactsList(List<GetPostListDto> response) {
        List<CommunityValue> contacts = new ArrayList<>();

        for (GetPostListDto dto : response) {
            contacts.add(new CommunityValue(dto));
        }
        return contacts;
    }

}
