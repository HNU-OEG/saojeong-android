package com.example.saojeong.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CommunityValue {

    private String mBoard;
    private String mTitle;
    private String mName;
    private String mContents;
    private String mDate;
    private List<Community_CommentValue> mComment=new ArrayList<>();

    public CommunityValue(String Board, String Title, String Name, String Date, String Contents)
    {
        mBoard=Board;
        mTitle=Title;
        mName=Name;
        mDate=Date;
        mContents=Contents;
    }
    public void SetComment(List<Community_CommentValue> Comment)
    {
        mComment=Comment;
    }
    public String GetBoard() {
        return mBoard;
    }public String GetTitle() {
        return mTitle;
    }public String GetName() {
        return mName;
    }public String GetDate() {
        return mDate;
    }public String GetContents() {
        return mContents;
    }public List<Community_CommentValue> GetComment() {
        return mComment;
    }
}
