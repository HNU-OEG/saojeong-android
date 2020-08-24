package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.model.MyQnA;
import com.example.saojeong.model.OnItemClickListener;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.board.CommunityPostListDto;
import com.example.saojeong.rest.dto.board.CreatePostDto;
import com.example.saojeong.rest.dto.board.GetPostListArrayDto;
import com.example.saojeong.rest.dto.board.GetPostListDto;
import com.example.saojeong.rest.service.BoardService;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyQnAFragment extends Fragment {
    private final String LOG = "MyQnA";
    private BoardService boardService;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private RecyclerView recycler_myQnA;
    private MyQnAadapter myQnAadapter;

    List<MyQnA> myQnA;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();
        boardService = ServiceGenerator.createService(BoardService.class, TokenCase.getToken());

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_myqna_list, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_myqna_list);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.~~); // 뒤로가기 화살표 이미지 바꾸기
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("");
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.BLACK);

        recycler_myQnA = view.findViewById(R.id.recycler_myQnA);
//        myQnA = MyQnA.createMyQnA(20);
//        myQnAadapter = new MyQnAadapter(myQnA);
//        recycler_myQnA.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        recycler_myQnA.addItemDecoration(new DividerItemDecoration(view.getContext(), 1));
//        recycler_myQnA.setAdapter((myQnAadapter));

        loadData(view);

        return view;

    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        Log.d(LOG, "2");
//
//        myQnAadapter.setOnItemClicklistener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(Object holder, View view, int position) {
//                ((MainActivity) getActivity()).replaceFragment(MyQnAItemFragment.newInstance());
//            }
//        });
//    }

    private void loadData(View view) {

        boardService.getPostList(10001).enqueue(new Callback<GetPostListArrayDto>() {
            @Override
            public void onResponse(Call<GetPostListArrayDto> call, Response<GetPostListArrayDto> response) {
                if (response.code() == 201) {
                    GetPostListArrayDto body = response.body();
                    myQnA = MyQnA.createMyQnA(body.getNormal());
                    myQnAadapter = new MyQnAadapter(myQnA);
                    Log.d(LOG, "response OK");
                } else {
                    myQnA = MyQnA._createMyQnA(20);
                    myQnAadapter = new MyQnAadapter(myQnA);
                    Log.d(LOG, "response Fail " + response.message());

                }

                recycler_myQnA.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                recycler_myQnA.addItemDecoration(new DividerItemDecoration(view.getContext(), 1));
                recycler_myQnA.setAdapter((myQnAadapter));
            }

            @Override
            public void onFailure(Call<GetPostListArrayDto> call, Throwable t) {
                myQnA = MyQnA._createMyQnA(20);
                myQnAadapter = new MyQnAadapter(myQnA);
                recycler_myQnA.addItemDecoration(new DividerItemDecoration(view.getContext(), 1));
                recycler_myQnA.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recycler_myQnA.setAdapter(myQnAadapter);
                Log.d(LOG, t.getMessage());
            }

        });

    }
}
