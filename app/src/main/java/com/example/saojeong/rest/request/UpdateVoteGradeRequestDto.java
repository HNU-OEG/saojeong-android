package com.example.saojeong.rest.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UpdateVoteGradeRequestDto {
    @SerializedName("kindness")
    @Expose
    public float kindness;
    @SerializedName("merchandise")
    @Expose
    public float merchandise;
    @SerializedName("price")
    @Expose
    public float price;
}
