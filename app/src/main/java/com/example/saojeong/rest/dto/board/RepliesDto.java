package com.example.saojeong.rest.dto.board;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RepliesDto {

    @SerializedName("commnet_id")
    @Expose
    private Integer reply_id;
    @SerializedName("member_id")
    @Expose
    public String memberId;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
}
