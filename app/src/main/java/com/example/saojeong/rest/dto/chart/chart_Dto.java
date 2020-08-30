package com.example.saojeong.rest.dto.chart;

import com.example.saojeong.rest.dto.LoginResultDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class chart_Dto {
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("unit")
    @Expose
    public String unit;
    @SerializedName("rank")
    @Expose
    public String rank;
    @SerializedName("kindName")
    @Expose
    public String kindName;
}
