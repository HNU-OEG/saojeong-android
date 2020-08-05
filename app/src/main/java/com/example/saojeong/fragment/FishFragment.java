package com.example.saojeong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.adapter.FishCloseAdapter;
import com.example.saojeong.adapter.FishOpenAdapter;
import com.example.saojeong.model.ContactFishClose;
import com.example.saojeong.model.ContactFishOpen;
import com.example.saojeong.model.RecyclerDecoration;

import java.util.ArrayList;

public class FishFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private RecyclerView recyclerFishopen;
    private RecyclerView recyclerFishclose;
    private FishOpenAdapter fishOpenAdapter;
    private FishCloseAdapter fishCloseAdapter;
    ArrayList<ContactFishOpen> contactFishOpens;
    ArrayList<ContactFishClose> contactFishCloses;

    TextView selectedText;
    Spinner spinner_fish;
    String[] item_fish;

    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(50);

    public static FishFragment newInstance() {
        return new FishFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_fish, container, false);

        //순서 나열 Spinner
        selectedText = (TextView) rootView.findViewById(R.id.selected_fish);
        spinner_fish = (Spinner) rootView.findViewById(R.id.spinner_fish);

        item_fish = new String[]{"평점 높은 순", "평점 많은 순", "이름 순"};
        ArrayAdapter<String> adapter_fishopen = new ArrayAdapter<String >(getContext(), android.R.layout.simple_spinner_item, item_fish);
        adapter_fishopen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_fish.setAdapter(adapter_fishopen);

        //과일동 오픈가게 Recycler View
        recyclerFishopen = (RecyclerView) rootView.findViewById(R.id.recyclerfishopen_fragment);
        contactFishOpens = ContactFishOpen.createContactsList(5);
        fishOpenAdapter = new FishOpenAdapter(contactFishOpens);
        recyclerFishopen.addItemDecoration(bottomDecoration);
        recyclerFishopen.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerFishopen.setAdapter(fishOpenAdapter);

        //과일동 닫은가게 Recycler View
        recyclerFishclose = (RecyclerView) rootView.findViewById(R.id.recyclerfishclose_fragment);
        contactFishCloses = ContactFishClose.createContactsList(3);
        fishCloseAdapter = new FishCloseAdapter(contactFishCloses);
        recyclerFishclose.addItemDecoration(bottomDecoration);
        recyclerFishclose.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerFishclose.setAdapter(fishCloseAdapter);



        return rootView;

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedText.setText(item_fish[i]);
        if(selectedText.getText().toString().equals("선택하세요")) {
            selectedText.setText("");

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selectedText.setText("");

    }
}
