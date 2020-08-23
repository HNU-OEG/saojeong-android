package com.example.saojeong.model;


import com.example.saojeong.R;
import com.example.saojeong.rest.dto.StoreDto;
import com.example.saojeong.rest.dto.board.CommunityPostListDto;
import com.example.saojeong.rest.dto.board.CommunityPostListDto_1;

import java.util.ArrayList;
import java.util.List;

public class CommunityValue {

    public String title;
    public String author;
    public String createdAt;
    public int commentCount;
    public int votedCount;

    public CommunityValue(String title, String author, String createdAt, int commentCount, int votedCount)
    {
        this.title=title;
        this.author=author;
        this.createdAt=createdAt;
        this.commentCount=commentCount;
        this.votedCount=votedCount;
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
    public CommunityValue(CommunityPostListDto_1 dto) {
        this.title = dto.getTitle();
        this.author = dto.getAuthor();
        this.createdAt = dto.getCreatedAt();
        this.commentCount = dto.getCommentCount();
        this.votedCount = dto.getVotedCount();
    }

    public static List<CommunityValue> createContactsList(List<CommunityPostListDto_1> response) {
        List<CommunityValue> contacts = new ArrayList<>();

        for (CommunityPostListDto_1 dto : response) {
            contacts.add(new CommunityValue(dto));
        }
        return contacts;
    }

}
