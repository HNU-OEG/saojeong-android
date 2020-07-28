package com.example.saojeong.model;

import java.util.List;

public class Community_CommentValue {

    private String mName;
    private String mDate;
    private String mContents;

    public Community_CommentValue(String Title, String Name, String Date, String Contents)
    {
        mName=Name;
        mDate=Date;
        mContents=Contents;
    }
    public String GetName() {
        return mName;
    }public String GetDate() {
        return mDate;
    }public String GetContents() {
        return mContents;
    }
}
