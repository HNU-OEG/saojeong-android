package com.example.saojeong.rest.dto.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoreDetailDto {
    @SerializedName("store_merchandise")
    @Expose
    public List<StoreMerchandise> storeMerchandise = null;
    @SerializedName("store_detail")
    @Expose
    public StoreDetail storeDetail;
    @SerializedName("store_grade")
    @Expose
    public StoreGrade storeGrade;

    class StoreMerchandise {
        // 서버 동작 정의 안됨
    }

    public class StoreGrade {
        @SerializedName("kindness_average")
        @Expose
        public Double kindnessAverage;
        @SerializedName("merchandise_average")
        @Expose
        public Double merchandiseAverage;
        @SerializedName("price_average")
        @Expose
        public Double priceAverage;
        @SerializedName("my_kindness")
        @Expose
        public Double myKindness;
        @SerializedName("my_merchandise")
        @Expose
        public Double myMerchandise;
        @SerializedName("my_price")
        @Expose
        public Double myPrice;
    }

    class StoreDetail {
        @SerializedName("store_image")
        @Expose
        public String storeImage;
        @SerializedName("store_indexholder")
        @Expose
        public Integer storeIndexholder;
        @SerializedName("store_name")
        @Expose
        public String storeName;
        @SerializedName("store_intro")
        @Expose
        public Object storeIntro;
        @SerializedName("vote_grade_count")
        @Expose
        public Integer voteGradeCount;
        @SerializedName("vote_grade_average")
        @Expose
        public Double voteGradeAverage;
        @SerializedName("telephone")
        @Expose
        public String telephone;
        @SerializedName("starred")
        @Expose
        public Integer starred;
        @SerializedName("opening")
        @Expose
        public List<Opening> opening = null;

        class Opening {
            @SerializedName("weekday")
            @Expose
            public Integer weekday;
            @SerializedName("start_hour")
            @Expose
            public String startHour;
            @SerializedName("end_hour")
            @Expose
            public String endHour;
        }
    }

}



