package com.example.saojeong.model;

import android.widget.TextView;

import com.example.saojeong.R;

import java.util.ArrayList;

public class MyQnA {

    private String mTitle;  //QnA 제목
    private String mStatus;   //QnA 답변완료 여부
    private String mDate;
    private String mContent;    //QnA 답변
    private String mComment;


    public MyQnA(String mTitle, String mStatus, String mDate, String mContent, String mComment) {
        this.mTitle = mTitle;
        this.mStatus = mStatus;
        this.mDate = mDate;
        this.mContent = mContent;
        this.mComment = mComment;
    }

    public String getmTitle() { return mTitle; }

    public String getmStatus() { return mStatus; }

    public String getmDate() { return mDate; }

    public String getmContent() { return mContent; }

    public String getmComment() { return mComment; }

    public static ArrayList<MyQnA> createMyQnA(int numMyQnA) {
        ArrayList<MyQnA> myQnAs = new ArrayList<MyQnA>();

        for (int i = 1; i <= numMyQnA; i++) {
            //myQnAs.add(MyPageGetData.getMyQnAData());
            myQnAs.add(new MyQnA("제목", "답변완료", "날짜", "QnA", "답변")); //test
        }

        return myQnAs;
    }
}
