package com.example.saojeong.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoreDto {
    @SerializedName("store_number")
    @Expose
    private Integer storeNumber;
    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("store_image")
    @Expose
    private String storeImage;
    @SerializedName("vote_grade_average")
    @Expose
    private Double voteGradeAverage;
    @SerializedName("vote_grade_count")
    @Expose
    private Integer voteGradeCount;
    @SerializedName("store_id")
    @Expose
    private Integer storeId;
    @SerializedName("starred")
    @Expose
    private Integer starred;
    @SerializedName("store_intro")
    @Expose
    private String storeIntro;

    public boolean isStarred() {
        return starred == 1;
    }
}
