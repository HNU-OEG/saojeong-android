package com.example.saojeong.rest.dto.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
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

    @Getter
    @Setter
    @ToString
    public class StoreMerchandise {
        // 서버 동작 정의 안됨
    }

    @Getter
    @Setter
    @ToString
    public static class StoreDetail {
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
        public String storeIntro;
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

        @Getter
        @Setter
        @ToString
        public static class Opening {
            @SerializedName("weekday")
            @Expose
            public Integer weekday;
            @SerializedName("start_hour")
            @Expose
            public String startHour;
            @SerializedName("end_hour")
            @Expose
            public String endHour;

            @AllArgsConstructor
            public enum WeekDay {
                MONDAY("월요일", 0),
                TUESDAY("화요일", 1),
                WEDNESDAY("수요일", 2),
                THURSTDAY("목요일", 3),
                FRIDAY("금요일", 4),
                SATURDAY("토요일", 5),
                SUNDAY("일요일", 6);

                private String view;
                private Integer code;

                public static String getView(Integer code) {
                    switch (code) {
                        case 0:
                            return MONDAY.view;
                        case 1:
                            return TUESDAY.view;
                        case 2:
                            return WEDNESDAY.view;
                        case 3:
                            return THURSTDAY.view;
                        case 4:
                            return FRIDAY.view;
                        case 5:
                            return SATURDAY.view;
                        case 6:
                            return SUNDAY.view;
                        default:
                            return "ERROR";
                    }
                }
            }

            public String getJoinedString() {
                return WeekDay.getView(weekday)
                        + " " + startHour
                        + " ~ " + endHour;
            }

        }
    }

    @Getter
    @Setter
    @ToString
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
}



