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

    public boolean isStarred() {
        return starred == 1;
    }

    // Lombok이 없었다면 아래의 코드가 필요합니다.

//    public Integer getStoreNumber() {
//        return storeNumber;
//    }
//
//    public void setStoreNumber(Integer storeNumber) {
//        this.storeNumber = storeNumber;
//    }
//
//    public String getStoreName() {
//        return storeName;
//    }
//
//    public void setStoreName(String storeName) {
//        this.storeName = storeName;
//    }
//
//    public Integer getVoteGradeAverage() {
//        return voteGradeAverage;
//    }
//
//    public void setVoteGradeAverage(Integer voteGradeAverage) {
//        this.voteGradeAverage = voteGradeAverage;
//    }
//
//    public Integer getVoteGradeCount() {
//        return voteGradeCount;
//    }
//
//    public void setVoteGradeCount(Integer voteGradeCount) {
//        this.voteGradeCount = voteGradeCount;
//    }
//
//    public Integer getStoreId() {
//        return storeId;
//    }
//
//    public void setStoreId(Integer storeId) {
//        this.storeId = storeId;
//    }
//
//    public Integer getStarred() {
//        return starred;
//    }
//
//    public void setStarred(Integer starred) {
//        this.starred = starred;
//    }

}
