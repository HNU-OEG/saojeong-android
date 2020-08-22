package com.example.saojeong.model;

import android.view.View;

import com.example.saojeong.adapter.FruitOpenAdapter;

public interface OnItemClickListener {
    public void onItemClick(FruitOpenAdapter.ViewHolder holder, View view, int position);


}