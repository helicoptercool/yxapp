package com.ty.app.yxapp.dwcenter.ui.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.BubbleChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.BubbleChartData;
import lecho.lib.hellocharts.model.BubbleValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.BubbleChartView;

public class MyTaskActivity extends BaseActivity {

    private static final int BUBBLES_NUM = 8;

    private BubbleChartView chart;
    private BubbleChartData data;
    private TextView tvChartExplain;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        actionBar.setVisibility(View.VISIBLE);
        actionBar.setCenterView(AndroidUtils.getString(R.string.my_task));
        actionBar.setLeftView("", R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        View view = getLayoutInflater().inflate(R.layout.activity_my_task, null);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        chart = (BubbleChartView) view.findViewById(R.id.bubble_chart);
        tvChartExplain = (TextView) view.findViewById(R.id.chart_explain);
        chart.setOnValueTouchListener(new ValueTouchListener());
        chart.setZoomEnabled(false);//设置是否支持缩放
        chart.setValueSelectionEnabled(true);//设置值选中后进行显示
        generateData();

    }

    private void generateData() {

        List<BubbleValue> values = new ArrayList<BubbleValue>();
        for (int i = 0; i < BUBBLES_NUM; ++i) {
            BubbleValue value = new BubbleValue(i, (float) Math.random() * 100, (float) Math.random() * 1000);
            value.setColor(ChartUtils.pickColor());
            value.setShape(shape);
            value.setLabel("哈哈");
            values.add(value);
        }

        data = new BubbleChartData(values);
        data.setHasLabels(hasLabels);
        data.setValueLabelsTextColor(Color.BLACK);
        data.setValueLabelTextSize(15);
        data.setHasLabelsOnlyForSelected(hasLabelForSelected);

        if (hasAxes) {
            Axis axisX = new Axis();
//            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
//                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(axisX);
//            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        chart.setBubbleChartData(data);

    }

    private void setCircles() {
        shape = ValueShape.CIRCLE;
        generateData();
    }

    private void setSquares() {
        shape = ValueShape.SQUARE;
        generateData();
    }

    private void toggleLabels() {
        hasLabels = !hasLabels;

        if (hasLabels) {
            hasLabelForSelected = false;
            chart.setValueSelectionEnabled(hasLabelForSelected);
        }

        generateData();
    }

    private void toggleLabelForSelected() {
        hasLabelForSelected = !hasLabelForSelected;

        chart.setValueSelectionEnabled(hasLabelForSelected);

        if (hasLabelForSelected) {
            hasLabels = false;
        }

        generateData();
    }

    private void toggleAxes() {
        hasAxes = !hasAxes;

        generateData();
    }

    private void toggleAxesNames() {
        hasAxesNames = !hasAxesNames;

        generateData();
    }

    private void prepareDataAnimation() {
        for (BubbleValue value : data.getValues()) {
            value.setTarget(value.getX() + (float) Math.random() * 4 * getSign(), (float) Math.random() * 100,
                    (float) Math.random() * 1000);
        }
    }

    private int getSign() {
        int[] sign = new int[]{-1, 1};
        return sign[Math.round((float) Math.random())];
    }

    private class ValueTouchListener implements BubbleChartOnValueSelectListener {

        @Override
        public void onValueSelected(int bubbleIndex, BubbleValue value) {
            tvChartExplain.setText("任务说明：" + value.toString());
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }
    }
}

