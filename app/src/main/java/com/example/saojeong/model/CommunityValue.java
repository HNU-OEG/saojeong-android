package com.example.saojeong.model;


import java.util.ArrayList;
import java.util.List;

public class CommunityValue {

    private String mTitle;
    private String mName;
    private String mContents;
    private String mDate;
    private int mGoodRecommend;
    private int mBadRecommend;
    private List<Community_CommentValue> mComment=new ArrayList<>();
    private boolean mPopular;

    public CommunityValue(String Title, String Name, String Date, String Contents, int GoodRecommend, int BadReCommend, boolean Popular)
    {
        mTitle=Title;
        mName=Name;
        mDate=Date;
        mContents=Contents;
        mGoodRecommend=GoodRecommend;
        mBadRecommend=BadReCommend;
        mPopular=Popular;
    }
    public void SetComment(List<Community_CommentValue> Comment)
    {
        mComment=Comment;
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
    public int GetBadCommend() { return mBadRecommend; }
    public boolean GetPopular() { return mPopular; }

    public List<Community_CommentValue> GetComment() {
        return mComment;
    }
}
