package com.example.saojeong.util;

import com.example.saojeong.R;

import java.util.List;

public class StringUtils {

    public static String joiner(String delimeter, List<String> elements) {
        StringBuilder builder = new StringBuilder();
        for (String e : elements) {
            builder.append(e);
            builder.append(delimeter);
        }
        return builder.toString();
    }

    public static String getServerUrl() {
        return "https://saojeong-dev.hnulinc.c11.kr/";
    }
}
