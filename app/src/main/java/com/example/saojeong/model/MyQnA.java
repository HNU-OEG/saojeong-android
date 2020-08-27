package com.example.saojeong.model;

import android.util.Log;
import android.widget.TextView;

import com.example.saojeong.R;
import com.example.saojeong.rest.dto.board.CommunityPostListDto_1;
import com.example.saojeong.rest.dto.board.CreatePostDto;
import com.example.saojeong.rest.dto.board.GetPostListArrayDto;
import com.example.saojeong.rest.dto.board.GetPostListDto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class MyQnA {

    private int id;
    private String mTitle;  //QnA 제목
    private int mStatus;   //QnA 답변완료 여부
    private String mDate;
    int test;
//    private String mContent;    //QnA 답변
//    private String mComment;


    public MyQnA(String mTitle, int mStatus, String mDate) {
        this.mTitle = mTitle;
        this.mStatus = mStatus;
        this.mDate = mDate;
//        this.mContent = mContent;
//        this.mComment = mComment;
    }

    public MyQnA(GetPostListDto dto) {
        this.id = dto.getDocumentId();
        this.mTitle = dto.getTitle();
        this.mStatus = dto.getCommentCount();
        this.mDate = dto.getCreatedAt();
        this.test = dto.getDocumentId();
        Log.d("my", "MyQnA: " + dto.getDocumentId());
    }

    public static List<MyQnA> _createMyQnA(int numMyQnA) {
        List<MyQnA> myQnAs = new ArrayList<MyQnA>();

        for (int i = 1; i <= numMyQnA; i++) {
            myQnAs.add(new MyQnA("제목", 0, "날짜")); //test
        }

        return myQnAs;
    }

    public static List<MyQnA> createMyQnA(List<GetPostListDto> response) {
        List<MyQnA> myQnAs = new ArrayList<>();
        for (GetPostListDto dto : response) {
            myQnAs.add(new MyQnA(dto));
        }

        return myQnAs;
    }
}
