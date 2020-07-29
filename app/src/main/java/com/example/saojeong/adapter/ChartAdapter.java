package com.example.saojeong.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.ChartContact;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ChartAdapter extends  RecyclerView.Adapter<ChartAdapter.ViewHolder>  {
    private Context context;
    private List<ChartContact> mChartList;
    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
    private LineChart mLineChart;
    private TextView text;
    private TextView mSearch_Weekend;
    private TextView mSearch_ThreeWeekend;
    public ChartAdapter(Context context, List<ChartContact> listvalue) {
        this.context = context;
        mChartList=listvalue;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public Button messageButton;
        public ViewHolder(View itemView) {
            super(itemView);
            mLineChart = (LineChart) itemView.findViewById(R.id.chart);
            text = itemView.findViewById(R.id.tv_chart_name);
            mSearch_Weekend=itemView.findViewById(R.id.tv_oneweekend);
            mSearch_ThreeWeekend=itemView.findViewById(R.id.tv_threeweekend);
        }
    }

    @Override
    public ChartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.adapter_chart, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChartAdapter.ViewHolder holder, int position) {
        LineDataSet set1 = new LineDataSet(mChartList.get(position).GetChartValue(), "라벨명1");

        mSearch_Weekend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearch_Weekend.setTextColor(Color.parseColor("#fa8f68"));
                mSearch_ThreeWeekend.setTextColor(Color.parseColor("#000000"));
            }
        });
        mSearch_ThreeWeekend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearch_ThreeWeekend.setTextColor(Color.parseColor("#fa8f68"));
                mSearch_Weekend.setTextColor(Color.parseColor("#000000"));

            }
        });


        set1.setColor(Color.rgb(250, 143, 104));
        set1.setCircleColor(Color.rgb(250, 143, 104));
        set1.setValueTextSize(10f);
        set1.setValueTextColor(Color.rgb(250, 143, 104));
        set1.setLineWidth(2);
        set1.setFillAlpha(65);
        set1.setCircleRadius(5f);
        ArrayList<String> xAxisValues = new ArrayList<>();
        mLineChart.setExtraRightOffset(40f);
        mLineChart.setExtraBottomOffset(60f);
        mLineChart.setBorderColor(Color.WHITE);
        mLineChart.setBackgroundColor(Color.WHITE);
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        mLineChart.getAxisRight().setEnabled(false);
        mLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mLineChart.getAxisRight().setDrawGridLines(true);
        mLineChart.getXAxis().setDrawGridLines(false);

        for(int i=0; i<4; ++i)
        {
            xAxisValues.add("얍"+i);
        }
        mLineChart.setScaleMinima(10f, 10f);
        mLineChart.fitScreen();
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(11f);
        xAxis.setLabelCount(xAxisValues.size(), true);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mLineChart.setTouchEnabled(true);
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        YAxis yAxisLeft = mLineChart.getAxisLeft();
        yAxisLeft.setXOffset(11f);
        yAxisLeft.setTextSize(14f);
        yAxisLeft.setGranularity(1f);
        //mLineChart.getAxisLeft().setAxisMaximum();
        //mLineChart.getAxisLeft().setAxisMinimum(0);
        yAxisLeft.setAxisLineColor(Color.WHITE);
        yAxisLeft.setDrawGridLines(false);

        mLineChart.getLegend().setEnabled(false);
        mLineChart.getDescription().setEnabled(false);
        dataSets.add(set1);
        text.setText(mChartList.get(position).GetName());
        LineData chartData = new LineData(dataSets);
        mLineChart.setData(chartData);
        mLineChart.invalidate();
    }
    @Override
    public int getItemCount() {
        return mChartList.size();
    }

}
