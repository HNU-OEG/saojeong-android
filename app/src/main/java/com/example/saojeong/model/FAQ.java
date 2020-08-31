package com.example.saojeong.model;

import java.util.ArrayList;

public class FAQ {
    private String mTitle;
    private String mContext;

    public FAQ(String mTitle, String mContext) {
        this.mTitle = mTitle;
        this.mContext = mContext;
    }

    public String getmTitle() { return mTitle; }

    public String getmContext() { return mContext; }

    public static ArrayList<FAQ> createFAQ(int numFAQ) {
        ArrayList<FAQ> FAQs = new ArrayList<FAQ>();

        for (int i = 1; i <= numFAQ; i++) {
            FAQs.add(new FAQ("FAQ TITLE TEST", "FAQ CONTENT TEST / FAQ CONTENT TEST / FAQ CONTENT TEST / FAQ CONTENT TEST / FAQ CONTENT TEST / FAQ CONTENT TEST / FAQ CONTENT TEST / FAQ CONTENT TEST"));
        }

        return FAQs;
    }
}
