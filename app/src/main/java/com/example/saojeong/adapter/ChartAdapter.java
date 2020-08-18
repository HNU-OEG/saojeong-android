package com.example.saojeong.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.model.ChartContact;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChartAdapter extends  RecyclerView.Adapter<ChartAdapter.ViewHolder>  implements Filterable {
    private Context context;
    private List<ChartContact> mChartList;
    private List<ChartContact> mChartListAll;
    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
    private LineChart mLineChart;
    private TextView text;
    private TextView mSearch_Weekend;
    private TextView mSearch_ThreeWeekend;
    private int iWeekend;

    public void SetWeekend(int Weekend){
        iWeekend=Weekend;
    }

    public ChartAdapter(Context context, List<ChartContact> listvalue, int Weekend) {
        this.context = context;
        mChartList=listvalue;
        mChartListAll=new ArrayList<>(listvalue);
        iWeekend=Weekend;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter= new Filter() {
        //백그라운드 스레드동작
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ChartContact>filteredList=new ArrayList<>();
            if(charSequence.toString().isEmpty()) {
                filteredList.addAll(mChartListAll);
            }
            else{
                for(ChartContact chartlist:mChartListAll){
                    if(chartlist.GetName().toLowerCase().contains(charSequence.toString().toLowerCase()))
                        filteredList.add(chartlist);
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mChartList.clear();
            mChartList.addAll((Collection<? extends ChartContact>) filterResults.values);
            notifyDataSetChanged();

        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public Button messageButton;
        public ViewHolder(View itemView) {
            super(itemView);
            mLineChart = (LineChart) itemView.findViewById(R.id.chart);
            text = itemView.findViewById(R.id.tv_chart_name);
            //getAdapterPosition(); 이쪽으로 위치 확인해서 클릭리스너구현

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
        set1.setColor(Color.rgb(250, 143, 104));
        set1.setCircleColor(Color.rgb(250, 143, 104));
        set1.setValueTextSize(10f);
        set1.setValueTextColor(Color.rgb(250, 143, 104));
        set1.setLineWidth(2);
        set1.setFillAlpha(65);
        set1.setCircleRadius(5f);
        ArrayList<String> xAxisValues = new ArrayList<>();
        mLineChart.setExtraRightOffset(10f);
        mLineChart.setExtraLeftOffset(10f);
        //mLineChart.setExtraBottomOffset(f);
        //mLineChart.setBackgroundResource(R.drawable.rounded_edittext_gray);
        //mLineChart.setExtraTopOffset(10f);
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        mLineChart.getAxisRight().setEnabled(false);
        mLineChart.getAxisRight().setDrawGridLines(true);
        mLineChart.getXAxis().setDrawGridLines(false);

        for(int i=0; i<4; ++i) {
            xAxisValues.add("7월"+i+"일");
        }
        mLineChart.fitScreen();
        mLineChart.setExtraBottomOffset(-10f);
        XAxis xAxis = mLineChart.getXAxis();
        //xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setSpaceMin(0.4f);
        xAxis.setSpaceMax(0.4f);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(0f);
        xAxis.setEnabled(false);
        //xAxis.setLabelCount(xAxisValues.size(), true);
        xAxis.setAvoidFirstLastClipping(true);
        if(iWeekend==1) {
            set1.setDrawValues(true);
        }
        if(iWeekend==3) {
            set1.setDrawValues(true);
        }

        //xAxis.setAxisMinimum(1); 주차값변경
        //xAxis.setAxisMaximum(10);
        mLineChart.setTouchEnabled(true);
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        YAxis yAxisLeft = mLineChart.getAxisLeft();
        yAxisLeft.setTextSize(11f);
        yAxisLeft.setGranularity(10f);
        yAxisLeft.setLabelCount(4);
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
