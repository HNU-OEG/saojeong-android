package com.example.saojeong.rest.dto.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateVoteGradeResponseDto {
    @SerializedName("store_id")
    @Expose
    public Integer storeId;
    @SerializedName("member_id")
    @Expose
    public String memberId;
    @SerializedName("status")
    @Expose
    public Object status;
    @SerializedName("question1")
    @Expose
    public float question1;
    @SerializedName("question2")
    @Expose
    public float question2;
    @SerializedName("question3")
    @Expose
    public float question3;
    @SerializedName("is_available")
    @Expose
    public Integer isAvailable;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("removed_at")
    @Expose
    public Object removedAt;
}
