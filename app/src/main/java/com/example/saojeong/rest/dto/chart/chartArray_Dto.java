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
public class chartArray_Dto {

    public List<chart_Dto> result = null;
}
