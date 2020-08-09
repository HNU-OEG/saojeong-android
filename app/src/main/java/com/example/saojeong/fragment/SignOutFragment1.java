package com.example.saojeong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;

public class SignOutFragment1 extends Fragment {

    private Button btn_sign_out;

    public static SignOutFragment1 newInstance() {
        return new SignOutFragment1();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_sign_out1, container, false);
        btn_sign_out = view.findViewById(R.id.btn_out1);

        btn_sign_out.setOnClickListener((v) -> {
            ((MainActivity)getActivity()).replaceFragment(SignOutFragment2.newInstance());
        });

        return view;
    }
}
