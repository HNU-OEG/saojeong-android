package com.example.saojeong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.adapter.MyQnAadapter;
import com.example.saojeong.model.MyQnA;

import java.util.ArrayList;

public class MyQnAFragment extends Fragment {

    private RecyclerView recycler_myQnA;
    private MyQnAadapter myQnAadapter;

    ArrayList<MyQnA> myQnA;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_myqna_list, container, false);

        recycler_myQnA = view.findViewById(R.id.recycler_myQnA);
        myQnA = MyQnA.createMyQnA(20);
        myQnAadapter = new MyQnAadapter(myQnA);
        recycler_myQnA.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler_myQnA.addItemDecoration(new DividerItemDecoration(view.getContext(), 1));
        recycler_myQnA.setAdapter((myQnAadapter));

        return view;

    }
}
