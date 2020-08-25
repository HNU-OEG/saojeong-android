package com.example.saojeong.rest.dto.board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateComentDto {

    @SerializedName("content")
    @Expose
    private String content;

}
