package com.example.saojeong.rest.dto.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StarUnstarResponseDto {
    @SerializedName("member_id")
    @Expose
    public String memberId;
    @SerializedName("store_id")
    @Expose
    public Integer storeId;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("removed_at")
    @Expose
    public String removedAt;
    @SerializedName("is_visible")
    @Expose
    public Integer isVisible;
}