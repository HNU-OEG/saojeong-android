package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.MyQnAadapter;
import com.example.saojeong.model.MyQnA;
import com.example.saojeong.model.OnItemClickListener;

import java.util.ArrayList;

public class MyQnAFragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private RecyclerView recycler_myQnA;
    private MyQnAadapter myQnAadapter;

    ArrayList<MyQnA> myQnA;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_myqna_list, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_myqna_list);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.~~); // 뒤로가기 화살표 이미지 바꾸기
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("");
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.BLACK);

        recycler_myQnA = view.findViewById(R.id.recycler_myQnA);
        myQnA = MyQnA.createMyQnA(20);
        myQnAadapter = new MyQnAadapter(myQnA);
        recycler_myQnA.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler_myQnA.addItemDecoration(new DividerItemDecoration(view.getContext(), 1));
        recycler_myQnA.setAdapter((myQnAadapter));

        return view;

    }

//    @Overr
}
