package com.example.saojeong.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TypeStoreDto {
    @SerializedName("open_store")
    @Expose
    private List<StoreDto> openStore;
    @SerializedName("closed_store")
    @Expose
    private List<StoreDto> closedStore;
}
