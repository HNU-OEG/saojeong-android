package com.example.saojeong.model;


import java.util.ArrayList;
import java.util.List;

public class CommunityValue {

    private String mBoard;
    private String mTitle;
    private String mName;
    private String mContents;
    private String mDate;
    private int mGoodRecommend;
    private int mBadRecommend;
    private List<Community_CommentValue> mComment=new ArrayList<>();

    public CommunityValue(String Board, String Title, String Name, String Date, String Contents, int GoodRecommend, int BadReCommend)
    {
        mBoard=Board;
        mTitle=Title;
        mName=Name;
        mDate=Date;
        mContents=Contents;
        mGoodRecommend=GoodRecommend;
        mBadRecommend=BadReCommend;
    }
    public void SetComment(List<Community_CommentValue> Comment)
    {
        mComment=Comment;
    }
    public String GetBoard() {
        return mBoard;
    }
    public String GetTitle() {
        return mTitle;
    }
    public String GetName() {
        return mName;
    }
    public String GetDate() {
        return mDate;
    }
    public String GetContents() { return mContents; }
    public int GetGoodCommend() { return mGoodRecommend; }
    public int GetBadCOmmend() { return mBadRecommend; }

    public List<Community_CommentValue> GetComment() {
        return mComment;
    }
}
