package com.example.saojeong.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;


public class QnAFragment extends Fragment {

    private EditText et_qna;
    private Button btn_qna_submit;

    public static QnAFragment newInstance() {
        return new QnAFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_qna, container, false);

        et_qna = view.findViewById(R.id.et_qna);
        btn_qna_submit = view.findViewById(R.id.btn_qna_submit);

        btn_qna_submit.setOnClickListener((v) -> {
            // TODO: 2020-08-22-022 서버로 전송
            ((MainActivity)getActivity()).replaceFragment(MyPageFragment.newInstance());
        });

        et_qna.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // TODO: 2020-08-22-022 키보드 나오게 구현 
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        return view;
    }
}
