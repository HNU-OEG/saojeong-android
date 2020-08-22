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
import com.example.saojeong.adapter.FruitCloseAdapter;
import com.example.saojeong.adapter.FruitOpenAdapter;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.model.ContactFruitClose;
import com.example.saojeong.model.ContactFruitOpen;
import com.example.saojeong.model.RecyclerDecoration;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.StoreDto;
import com.example.saojeong.rest.dto.TypeStoreDto;
import com.example.saojeong.rest.service.StoreService;

import java.io.IOException;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FruitFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private RecyclerView recyclerFruitopen;
    private RecyclerView recyclerFruitclose;
    private FruitOpenAdapter fruitOpenAdapter;
    private FruitCloseAdapter fruitCloseAdapter;
    List<ContactFruitOpen> contactFruitOpens;
    List<ContactFruitClose> contactFruitCloses;

    TextView selectedText;
    Spinner spinner_fruit;
    String[] item_fruit;

    RecyclerDecoration.BottomDecoration bottomDecoration = new RecyclerDecoration.BottomDecoration(50);

    private StoreService storeService;

    public static FruitFragment newInstance() {
        return new FruitFragment();
    }

    @SneakyThrows
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        storeService = ServiceGenerator.createService(StoreService.class, TokenCase.getToken());

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_fruit, container, false);

        ((MainActivity) getActivity()).closeKeyBoard(rootView);

        //순서 나열 Spinner
        selectedText = (TextView) rootView.findViewById(R.id.selected_fruit);
        spinner_fruit = (Spinner) rootView.findViewById(R.id.spinner_fruit);

        item_fruit = new String[]{"평점 높은 순", "평점 많은 순", "이름 순"};
        ArrayAdapter<String> adapter_fruitopen = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, item_fruit);
        adapter_fruitopen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_fruit.setAdapter(adapter_fruitopen);

        //과일동 오픈가게 Recycler View
        recyclerFruitopen = (RecyclerView) rootView.findViewById(R.id.recyclerfruitopen_fragment);
        //과일동 닫은가게 Recycler View
        recyclerFruitclose = (RecyclerView) rootView.findViewById(R.id.recyclerfruitclose_fragment);
        loadStores(this);

        return rootView;
    }

    private void loadStores(FruitFragment fruitFragment) throws IOException {
        storeService.getTypeStore("fruits", "count").enqueue(new Callback<TypeStoreDto>() {
            @Override
            public void onResponse(Call<TypeStoreDto> call, Response<TypeStoreDto> response) {
                if (response.code() != 201) {
                    contactFruitOpens = ContactFruitOpen._createContactsList(5);
                    fruitOpenAdapter = new FruitOpenAdapter(contactFruitOpens);

                    contactFruitCloses = ContactFruitClose._createContactsList(3);
                    fruitCloseAdapter = new FruitCloseAdapter(contactFruitCloses);
                } else {
                    TypeStoreDto body = response.body();
                    List<StoreDto> openStore = body.getOpenStore();
                    List<StoreDto> closedStore = body.getClosedStore();

                    contactFruitOpens = ContactFruitOpen.createContactsList(openStore);
                    fruitOpenAdapter = new FruitOpenAdapter(Glide.with(fruitFragment), contactFruitOpens);

                    contactFruitCloses = ContactFruitClose.createContactsList(closedStore);
                    fruitCloseAdapter = new FruitCloseAdapter(Glide.with(fruitFragment), contactFruitCloses);
                }
                recyclerFruitopen.addItemDecoration(bottomDecoration);
                recyclerFruitopen.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
                recyclerFruitopen.setAdapter(fruitOpenAdapter);

                recyclerFruitclose.addItemDecoration(bottomDecoration);
                recyclerFruitclose.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
                recyclerFruitclose.setAdapter(fruitCloseAdapter);

                fruitOpenAdapter.setOnItemClicklistener((holder, view, position) ->
                        ((MainActivity) getActivity()).replaceFragment(FruitShopFragment.newInstance()));
            }

            @Override
            public void onFailure(Call<TypeStoreDto> call, Throwable t) {
                contactFruitOpens = ContactFruitOpen._createContactsList(5);
                fruitOpenAdapter = new FruitOpenAdapter(contactFruitOpens);
                recyclerFruitopen.addItemDecoration(bottomDecoration);
                recyclerFruitopen.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
                recyclerFruitopen.setAdapter(fruitOpenAdapter);

                contactFruitCloses = ContactFruitClose._createContactsList(3);
                fruitCloseAdapter = new FruitCloseAdapter(contactFruitCloses);
                recyclerFruitclose.addItemDecoration(bottomDecoration);
                recyclerFruitclose.setLayoutManager((new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)));
                recyclerFruitclose.setAdapter(fruitCloseAdapter);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedText.setText(item_fruit[i]);
        if (selectedText.getText().toString().equals("선택하세요")) {
            selectedText.setText("");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selectedText.setText("");
    }
}