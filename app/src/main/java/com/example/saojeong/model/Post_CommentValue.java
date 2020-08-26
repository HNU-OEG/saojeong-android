package com.example.saojeong.model;

import com.example.saojeong.rest.dto.board.CommentDto;
import com.example.saojeong.rest.dto.board.GetPostDto;
import com.example.saojeong.rest.dto.board.GetPostListDto;
import com.example.saojeong.rest.dto.board.RepliesDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Post_CommentValue {
    int comment_ID;
    String memberID;
    String author;
    String content;
    String createdAt;
    boolean reContent;
    List<Post_CommentValue> replies;

    public int getComment_ID() {
        return comment_ID;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public List<Post_CommentValue> getReplies() {
        return replies;
    }

    public boolean isReContent() {
        return reContent;
    }

    public Post_CommentValue(String memberID, String author, String content, String createdAt, List<Post_CommentValue> replies)
    {
        this.memberID=memberID;
        this.author=author;
        this.content=content;
        this.createdAt=createdAt;
        this.replies=replies;
    }

    public Post_CommentValue(RepliesDto dto) {
        this.comment_ID = dto.getReply_id();
        this.memberID = dto.getMemberId();
        this.author = dto.getAuthor();
        this.content = dto.getContent();
        this.createdAt = dto.getCreatedAt();
    }

    public Post_CommentValue(CommentDto dto) {
        this.comment_ID = dto.getComment_id();
        this.memberID = dto.getMemberId();
        this.author = dto.getAuthor();
        this.content = dto.getContent();
        this.createdAt = dto.getCreatedAt();
        this.replies=Post_CommentValue.createContactsList_replies(dto.getReplies());
    }
    public static List<Post_CommentValue> createContactsList(List<CommentDto> response) {
        List<Post_CommentValue> contacts = new ArrayList<>();

        for (CommentDto dto : response) {
            contacts.add(new Post_CommentValue(dto));
        }
        return contacts;
    }
    public static List<Post_CommentValue> createContactsList_replies(List<RepliesDto> response) {
        List<Post_CommentValue> contacts = new ArrayList<>();

        for (RepliesDto dto : response) {
            contacts.add(new Post_CommentValue(dto));
        }
        return contacts;
    }
}
