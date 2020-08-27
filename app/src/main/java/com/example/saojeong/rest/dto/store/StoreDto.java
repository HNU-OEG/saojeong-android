package com.example.saojeong.rest.dto.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoreDto implements Comparable<StoreDto> {
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
    private Integer starred = 0;
    @SerializedName("store_intro")
    @Expose
    private String storeIntro;

    public StoreDto (Double voteGradeAverage, Integer voteGradeCount, String storeName) {
        this.voteGradeAverage = voteGradeAverage;
        this.voteGradeCount = voteGradeCount;
        this.storeName = storeName;
    }

    public boolean isStarred() {
        return starred == 1;
    }

    @Override
    public int compareTo(StoreDto storeDto) {
        return 0;
    }
}
