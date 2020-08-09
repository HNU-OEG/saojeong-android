package com.example.saojeong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;

public class SignOutFragment2 extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private MyPageFragment MyPageFragment;

    private Button btn_sign_out;
    private EditText et_sign_out;
    private String signOutReason;
    public static SignOutFragment2 newInstance() { return new SignOutFragment2(); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_sign_out2, container, false);

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        btn_sign_out = view.findViewById(R.id.btn_out2);
        et_sign_out = view.findViewById(R.id.et_sign_out);

        signOutReason = et_sign_out.getText().toString();

//        btn_sign_out.setOnClickListener((v) -> {
//            //todo 서버로 전송
//            //todo 프로필로 이동
//            ((MainActivity)getActivity()).replaceFragment(MyPageFragment.newInstance());
//        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        transaction = fragmentManager.beginTransaction();

        //todo 서버로 전송
        //todo 프로필로 이동

        view.findViewById(R.id.btn_out2).setOnClickListener((V) -> {
            ((MainActivity)getActivity()).replaceFragment(MyPageFragment.newInstance());
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            //transaction.add(R.id.fragment_container, Movies.newInstance()).commit();
        });
    }
}
