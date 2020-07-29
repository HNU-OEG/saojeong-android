package com.example.saojeong.model;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

public class ChartContact {
    private String mName;
    private ArrayList<Entry> mChartValue = new ArrayList<Entry>();

    public ChartContact(String mName) {
        this.mName = mName;
    }
    public ChartContact(String Name, ArrayList<Entry> value) {
        mName = Name;
        mChartValue = value;
    }
    public String GetName() {
        return mName;
    }

    public ArrayList<Entry> GetChartValue()
    {
        return mChartValue;
    }


}
