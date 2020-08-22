package com.example.saojeong.fragment;

import android.os.Bundle;
import android.util.Log;
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

import com.bumptech.glide.Glide;
import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.VegetableCloseAdapter;
import com.example.saojeong.adapter.VegetableOpenAdapter;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.model.ContactVegetableClose;
import com.example.saojeong.model.ContactVegetableOpen;
import com.example.saojeong.model.RecyclerDecoration;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.StoreDto;
import com.example.saojeong.rest.dto.TypeStoreDto;
import com.example.saojeong.rest.service.StoreService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VegetableFragment extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private RecyclerView recyclerVegetableopen;
    private RecyclerView recyclerVegetableclose;
    private VegetableOpenAdapter vegetableOpenAdapter;
    private VegetableCloseAdapter vegetableCloseAdapter;
    List<ContactVegetableOpen> contactVegetableOpens;
    List<ContactVegetableClose> contactVegetableCloses;

    TextView selectedText;
    Spinner spinner_vegetable;
    String[] item_vegetable;

    private StoreService storeService;

    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(50);

    public static VegetableFragment newInstance() {
        return new VegetableFragment();
    }

    @SneakyThrows
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        storeService = ServiceGenerator.createService(StoreService.class, TokenCase.getToken());

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_vegetable, container, false);

        //순서 나열 Spinner
        spinner_vegetable = (Spinner) rootView.findViewById(R.id.spinner_vegetable);

        item_vegetable = new String[]{"평점 높은 순", "평점 많은 순", "이름 순"};
        ArrayAdapter<String> adapter_vegetableopen = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, item_vegetable);
        adapter_vegetableopen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_vegetable.setAdapter(adapter_vegetableopen);
        spinner_vegetable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG", item_vegetable[i] + "입니다");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //과일동 오픈가게 Recycler View
        recyclerVegetableopen = (RecyclerView) rootView.findViewById(R.id.recyclervegetableopen_fragment);
        //과일동 닫은가게 Recycler View
        recyclerVegetableclose = (RecyclerView) rootView.findViewById(R.id.recyclervegetableclose_fragment);
        loadStores(this);



        return rootView;

    }

    private void loadStores(VegetableFragment vegetableFragment) throws IOException {
        storeService.getTypeStore("vegetables", "count").enqueue(new Callback<TypeStoreDto>() {
            @Override
            public void onResponse(Call<TypeStoreDto> call, Response<TypeStoreDto> response) {
                if (response.code() != 201) {
                    contactVegetableOpens = ContactVegetableOpen._createContactsList(5);
                    vegetableOpenAdapter = new VegetableOpenAdapter(contactVegetableOpens);

                    contactVegetableCloses = ContactVegetableClose._createContactsList(3);
                    vegetableCloseAdapter = new VegetableCloseAdapter(contactVegetableCloses);
                } else {
                    TypeStoreDto body = response.body();
                    List<StoreDto> openStore = body.getOpenStore();
                    List<StoreDto> closedStore = body.getClosedStore();
                    Log.d("BEFORE", openStore.toString());
                    contactVegetableOpens = ContactVegetableOpen.createContactsList(openStore);
                    vegetableOpenAdapter = new VegetableOpenAdapter(Glide.with(vegetableFragment), contactVegetableOpens);

                    contactVegetableCloses = ContactVegetableClose.createContactsList(closedStore);
                    vegetableCloseAdapter = new VegetableCloseAdapter(Glide.with(vegetableFragment), contactVegetableCloses);
                }
                recyclerVegetableopen.addItemDecoration(bottomDecoration);
                recyclerVegetableopen.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
                recyclerVegetableopen.setAdapter(vegetableOpenAdapter);

                recyclerVegetableclose.addItemDecoration(bottomDecoration);
                recyclerVegetableclose.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
                recyclerVegetableclose.setAdapter(vegetableCloseAdapter);
            }

            @Override
            public void onFailure(Call<TypeStoreDto> call, Throwable t) {
                contactVegetableOpens = ContactVegetableOpen._createContactsList(5);
                vegetableOpenAdapter = new VegetableOpenAdapter(contactVegetableOpens);
                recyclerVegetableopen.addItemDecoration(bottomDecoration);
                recyclerVegetableopen.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
                recyclerVegetableopen.setAdapter(vegetableOpenAdapter);

                contactVegetableCloses = ContactVegetableClose._createContactsList(3);
                vegetableCloseAdapter = new VegetableCloseAdapter(contactVegetableCloses);
                recyclerVegetableclose.addItemDecoration(bottomDecoration);
                recyclerVegetableclose.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
                recyclerVegetableclose.setAdapter(vegetableCloseAdapter);
            }
        });
    }

}
