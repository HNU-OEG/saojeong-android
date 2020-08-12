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

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.FruitCloseAdapter;
import com.example.saojeong.adapter.FruitOpenAdapter;
import com.example.saojeong.model.ContactFruitClose;
import com.example.saojeong.model.ContactFruitOpen;
import com.example.saojeong.model.OnItemClickListener;
import com.example.saojeong.model.RecyclerDecoration;

import java.util.ArrayList;

public class FruitFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private FruitShopFragment fruitShopFragment;
    private RecyclerView recyclerFruitopen;
    private RecyclerView recyclerFruitclose;
    private FruitOpenAdapter fruitOpenAdapter;
    private FruitCloseAdapter fruitCloseAdapter;
    ArrayList<ContactFruitOpen> contactFruitOpens;
    ArrayList<ContactFruitClose> contactFruitCloses;

    TextView selectedText;
    Spinner spinner_fruit;
    String[] item_fruit;

    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(50);

    public static FruitFragment newInstance() {
        return new FruitFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_fruit, container, false);

        ((MainActivity)getActivity()).closeKeyBoard(rootView);

        //순서 나열 Spinner
        selectedText = (TextView) rootView.findViewById(R.id.selected_fruit);
        spinner_fruit = (Spinner) rootView.findViewById(R.id.spinner_fruit);

        item_fruit = new String[]{"평점 높은 순", "평점 많은 순", "이름 순"};
        ArrayAdapter<String> adapter_fruitopen = new ArrayAdapter<String >(getContext(), android.R.layout.simple_spinner_item, item_fruit);
        adapter_fruitopen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_fruit.setAdapter(adapter_fruitopen);

        //과일동 오픈가게 Recycler View
        recyclerFruitopen = (RecyclerView) rootView.findViewById(R.id.recyclerfruitopen_fragment);
        contactFruitOpens = ContactFruitOpen.createContactsList(5);
        fruitOpenAdapter = new FruitOpenAdapter(contactFruitOpens);
        recyclerFruitopen.addItemDecoration(bottomDecoration);
        recyclerFruitopen.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerFruitopen.setAdapter(fruitOpenAdapter);

        //과일동 닫은가게 Recycler View
        recyclerFruitclose = (RecyclerView) rootView.findViewById(R.id.recyclerfruitclose_fragment);
        contactFruitCloses = ContactFruitClose.createContactsList(3);
        fruitCloseAdapter = new FruitCloseAdapter(contactFruitCloses);
        recyclerFruitclose.addItemDecoration(bottomDecoration);
        recyclerFruitclose.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
        recyclerFruitclose.setAdapter(fruitCloseAdapter);

        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transaction = fragmentManager.beginTransaction();

        fruitOpenAdapter.setOnItemClicklistener(new OnItemClickListener() {
            @Override
            public void onItemClick(FruitOpenAdapter.ViewHolder holder, View view, int position) {
                ((MainActivity)getActivity()).replaceFragment(fruitShopFragment.newInstance());
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedText.setText(item_fruit[i]);
        if(selectedText.getText().toString().equals("선택하세요")) {
            selectedText.setText("");

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selectedText.setText("");

    }
}
