package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class QnAFragment extends Fragment {

    private EditText et_qna;
    private Button btn_qna_submit;
    private InputMethodManager imm;

    public static QnAFragment newInstance() {
        return new QnAFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_qna, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_qna);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.~~); // 뒤로가기 화살표 이미지 바꾸기
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("");
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.BLACK);

        imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        et_qna = view.findViewById(R.id.et_qna);
        btn_qna_submit = view.findViewById(R.id.btn_qna_submit);

        btn_qna_submit.setOnClickListener((v) -> {
            // TODO: 2020-08-22-022 서버로 전송
            ((MainActivity)getActivity()).replaceFragment(MyPageFragment.newInstance());
        });

        return view;
    }
}
