package io.beering.beering.Fragement;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import io.beering.beering.R;

public class MytasteFragment extends Fragment {
    TextView ibu_circle;
    TextView abu_circle;
    TextView kcal_circle;

    private float[] yData = {40, 60};
    private String[] xData = {"Larger", "Ale"};
    PieChart pieChart;


    public MytasteFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mytaste, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pieChart = (PieChart) getView().findViewById(R.id.pie_graph);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setHoleRadius(75f);
        addDataSet();

    }

    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }

        for(int i = 0; i < xData.length; i++) {
            xEntrys.add(xData[i]);
        }

        //DataSet 생성, 안보이게 함
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Beer Graph");
        pieDataSet.setSliceSpace(0);
        pieDataSet.setDrawValues(false);

        //Data 색 지정
        int[] colors = {Color.argb(255, 255, 186, 12), Color.argb(255, 255, 116, 21)};
        pieDataSet.setColors(colors);

        //Legend
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);

        //Description
        Description des = pieChart.getDescription();
        des.setEnabled(false);

        //pie 그래프 생성
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

}


