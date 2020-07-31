package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.saojeong.R;

public class ProfileFragment extends Fragment {

    private Button btn_change_picture;
    private Button btn_save;
    private EditText et_new_name;
    private TextView tv_duplicate_err;

    private String currentName = "test";
    private String newName;
    private String text1 = "*중복되는 별명입니다. 다른 닉네임으로 작성해주세요.";
    private String text2 = "*사용가능한 별명입니다.";

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_edit_profile, container, false);
        btn_change_picture = view.findViewById(R.id.btn_change_picture);
        btn_save = view.findViewById(R.id.btn_save);
        et_new_name = view.findViewById(R.id.et_name);
        tv_duplicate_err = view.findViewById(R.id.tv_duplicate_err);

        btn_change_picture.setOnClickListener((v) -> {
            //사진변경
            // TODO: 2020-08-01-001 로컬에서 이미지 불러오기
            // TODO: 2020-08-01-002 서버에 이미지 전송
        });

        et_new_name.addTextChangedListener(new TextWatcher()  {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때
            }

            @Override
            public void afterTextChanged(Editable name) {
                // 입력이 끝났을 때
                newName = name.toString();
                if (currentName == newName) {
                    tv_duplicate_err.setText(text1);
                    tv_duplicate_err.setTextColor(Color.parseColor("#f68853"));
                    btn_save.setEnabled(true);
                } else {
                    tv_duplicate_err.setText(text2);
                    tv_duplicate_err.setTextColor(Color.parseColor("#85a0b3"));
                    btn_save.setEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에
            }
        });

        btn_save.setOnClickListener((v) -> {
            // TODO: 2020-08-01-003 서버에 변경된 이름 전송
        });


        return view;
    }

    public void signOut(View view) {
        Toast.makeText(view.getContext(),"test", Toast.LENGTH_LONG).show();
    }
}
