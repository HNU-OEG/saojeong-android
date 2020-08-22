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

import com.bumptech.glide.Glide;
import com.example.saojeong.MainActivity;
import com.example.saojeong.R;
import com.example.saojeong.adapter.FishCloseAdapter;
import com.example.saojeong.adapter.FishOpenAdapter;
import com.example.saojeong.adapter.FishCloseAdapter;
import com.example.saojeong.adapter.FishOpenAdapter;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.model.ContactFishClose;
import com.example.saojeong.model.ContactFishOpen;
import com.example.saojeong.model.ContactFishClose;
import com.example.saojeong.model.ContactFishOpen;
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

public class FishFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private RecyclerView recyclerFishopen;
    private RecyclerView recyclerFishclose;
    private FishOpenAdapter fishOpenAdapter;
    private FishCloseAdapter fishCloseAdapter;
    List<ContactFishOpen> contactFishOpens;
    List<ContactFishClose> contactFishCloses;

    TextView selectedText;
    Spinner spinner_fish;
    String[] item_fish;

    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(50);

    private StoreService storeService;
    public static FishFragment newInstance() {
        return new FishFragment();
    }

    @SneakyThrows
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        storeService = ServiceGenerator.createService(StoreService.class, TokenCase.getToken());

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
        //과일동 닫은가게 Recycler View
        recyclerFishclose = (RecyclerView) rootView.findViewById(R.id.recyclerfishclose_fragment);
        
        loadStores(this);
        
        return rootView;
    }

    private void loadStores(FishFragment fishFragment) throws IOException {
        storeService.getTypeStore("seafoods", "count").enqueue(new Callback<TypeStoreDto>() {
            @Override
            public void onResponse(Call<TypeStoreDto> call, Response<TypeStoreDto> response) {
                if (response.code() != 201) {
                    contactFishOpens = ContactFishOpen._createContactsList(5);
                    fishOpenAdapter = new FishOpenAdapter(contactFishOpens);

                    contactFishCloses = ContactFishClose._createContactsList(3);
                    fishCloseAdapter = new FishCloseAdapter(contactFishCloses);
                } else {
                    TypeStoreDto body = response.body();
                    List<StoreDto> openStore = body.getOpenStore();
                    List<StoreDto> closedStore = body.getClosedStore();

                    contactFishOpens = ContactFishOpen.createContactsList(openStore);
                    fishOpenAdapter = new FishOpenAdapter(Glide.with(fishFragment), contactFishOpens);

                    contactFishCloses = ContactFishClose.createContactsList(closedStore);
                    fishCloseAdapter = new FishCloseAdapter(Glide.with(fishFragment), contactFishCloses);
                }
                recyclerFishopen.addItemDecoration(bottomDecoration);
                recyclerFishopen.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
                recyclerFishopen.setAdapter(fishOpenAdapter);

                recyclerFishclose.addItemDecoration(bottomDecoration);
                recyclerFishclose.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
                recyclerFishclose.setAdapter(fishCloseAdapter);
            }

            @Override
            public void onFailure(Call<TypeStoreDto> call, Throwable t) {
                contactFishOpens = ContactFishOpen._createContactsList(5);
                fishOpenAdapter = new FishOpenAdapter(contactFishOpens);
                recyclerFishopen.addItemDecoration(bottomDecoration);
                recyclerFishopen.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
                recyclerFishopen.setAdapter(fishOpenAdapter);

                contactFishCloses = ContactFishClose._createContactsList(5);
                fishCloseAdapter = new FishCloseAdapter(contactFishCloses);
                recyclerFishclose.addItemDecoration(bottomDecoration);
                recyclerFishclose.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
                recyclerFishclose.setAdapter(fishCloseAdapter);
            }
        });
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
