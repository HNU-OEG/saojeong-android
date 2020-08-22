package com.example.saojeong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.saojeong.R;

public class MyQnAItemFragment extends Fragment {

    private TextView title;
    private TextView status;
    private TextView date;
    private TextView content;
    private TextView comment;

    public static MyQnAItemFragment newInstance() { return new MyQnAItemFragment(); }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_myqna, container, false);

        title = view.findViewById(R.id.tv_QnA_title);
        status = view.findViewById(R.id.tv_QnA_status);
        date = view.findViewById(R.id.tv_QnA_title);
        content = view.findViewById(R.id.tv_QnA_content);
        comment = view.findViewById(R.id.tv_QnA_comment);

        title.setText("제목");
        status.setText("[답변완료]");
        date.setText("날짜");
        content.setText("본문");
        comment.setText("답변");
        return view;
    }
}
