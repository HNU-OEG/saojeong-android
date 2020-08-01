package com.example.saojeong.model;


public class Community_CommentValue {

    private String mID;
    private String mDate;
    private String mComment;
    private boolean mReContent;

    public Community_CommentValue(String ID, String Date, String Comment, boolean reContent)
    {
        mID=ID;
        mDate=Date;
        mComment=Date;
        mReContent=reContent;
    }
    public String GetID() {
        return mID;
    }public String GetDate() {
        return mDate;
    }public String GetComment() {
        return mComment;
    }public boolean GetReContents() {
        return mReContent;
    }
}
