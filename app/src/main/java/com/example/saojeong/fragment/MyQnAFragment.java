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
    private BoardService boardService;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private final String TAG = this.getClass().getName();

    private RecyclerView recycler_myQnA;
    private MyQnAadapter myQnAadapter;
    private MyQnAItemFragment myQnAItemFragment;

    List<MyQnA> myQnA;

    public static MyQnAFragment newInstance() { return new MyQnAFragment(); }

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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(MyPageFragment.newInstance());
            }
        });
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

        boardService.getPostListWithID(10001).enqueue(new Callback<List<GetPostListDto>>() {
            @Override
            public void onResponse(Call<List<GetPostListDto>> call, Response<List<GetPostListDto>> response) {
                if (response.code() == 201) {
                    List<GetPostListDto> body = response.body();
                    myQnA = MyQnA.createMyQnA(body);
                    myQnAadapter = new MyQnAadapter(myQnA);
                } else {
                    myQnA = MyQnA._createMyQnA(20);
                    myQnAadapter = new MyQnAadapter(myQnA);
                    Log.d(TAG, "response Fail " + response.message());

                }

                recycler_myQnA.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                recycler_myQnA.addItemDecoration(new DividerItemDecoration(view.getContext(), 1));

                myQnAadapter.setOnItemClickListener(new OnItemClickListener<MyQnAadapter.ViewHolder>() {
                    @Override
                    public void onItemClick(MyQnAadapter.ViewHolder holder) {
                        MyQnAItemFragment targetFragment = myQnAItemFragment.newInstance(holder.documentId);
                        MainActivity activity = (MainActivity) getActivity();
                        activity.replaceFragment(targetFragment);
                    }
                });

                recycler_myQnA.setAdapter((myQnAadapter));
            }

            @Override
            public void onFailure(Call<List<GetPostListDto>> call, Throwable t) {
                myQnA = MyQnA._createMyQnA(20);
                myQnAadapter = new MyQnAadapter(myQnA);
                recycler_myQnA.addItemDecoration(new DividerItemDecoration(view.getContext(), 1));
                recycler_myQnA.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)));
                recycler_myQnA.setAdapter(myQnAadapter);
                Log.d(TAG, "MyQnA " + t.getMessage());
            }

        });

    }
}
