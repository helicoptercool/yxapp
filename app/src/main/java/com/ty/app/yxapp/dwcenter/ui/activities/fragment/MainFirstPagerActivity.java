package com.ty.app.yxapp.dwcenter.ui.activities.fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.network.RequestServer;
import com.ty.app.yxapp.dwcenter.ui.activities.EventActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.BasicMapActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.PersonalCenterActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.SetActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseFragment;
import com.ty.app.yxapp.dwcenter.ui.widget.ImageButtonCell;
import com.ty.app.yxapp.dwcenter.ui.widget.LooperImgCell;
import com.ty.app.yxapp.dwcenter.utils.GetWeatherListener;
import com.ty.app.yxapp.dwcenter.network.MapService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kss on 2017/3/26.
 */

public class MainFirstPagerActivity extends BaseFragment implements View.OnClickListener, GetWeatherListener {
    private static final String TAG = MainFirstPagerActivity.class.getSimpleName();
    private static String weatherInfo = "";
    private Context context;
    private LooperImgCell looperImgCell;
    private ImageButtonCell tlBtn;
    private ImageButtonCell trBtn;
    private ImageButtonCell blBtn;
    private ImageButtonCell brBtn;
    private TextView weather;
    private RequestServer mReqServer;



    @Override
    public View onCreate() {
        context = getContext();
        ScrollView scrollView = new ScrollView(context);

        LinearLayout cont = new LinearLayout(context);
        cont.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(cont, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        actionBar.setVisibility(View.VISIBLE);
        actionBar.setCenterView(AndroidUtils.getString(R.string.my_work));
        actionBar.setRightView("", R.mipmap.right_top_set, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PersonalCenterActivity.class);
                startActivity(intent);
            }
        });

        looperImgCell = new LooperImgCell(context);
        cont.addView(looperImgCell, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                AndroidUtils.dp(200)));

        weather = new TextView(context);
        weather.setText(weatherInfo);
        weather.setPadding(AndroidUtils.dp(10), AndroidUtils.dp(10), AndroidUtils.dp(10), AndroidUtils.dp(10));
        LinearLayout.LayoutParams weatherLL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        cont.addView(weather, weatherLL);

        LinearLayout selectCon = new LinearLayout(context);
        selectCon.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams selectConLL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        selectConLL.setMargins(0, AndroidUtils.dp(20), 0, 0);
        cont.addView(selectCon, selectConLL);

        LinearLayout topCon = new LinearLayout(context);
        topCon.setOrientation(LinearLayout.HORIZONTAL);
        selectCon.addView(topCon, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        tlBtn = new ImageButtonCell(context, AndroidUtils.getString(R.string.to_do_event));
        tlBtn.setOnClickListener(this);
        LinearLayout.LayoutParams tlbLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        tlbLL.weight = 1;
        tlBtn.setPicture(R.mipmap.todo);
        topCon.addView(tlBtn, tlbLL);

        trBtn = new ImageButtonCell(context, AndroidUtils.getString(R.string.already_completed_event));
        trBtn.setOnClickListener(this);
        LinearLayout.LayoutParams trbLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        trbLL.weight = 1;
        trBtn.setPicture(R.mipmap.yiwancheng);
        topCon.addView(trBtn, trbLL);


        LinearLayout bottomCon = new LinearLayout(context);
        bottomCon.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams bcLL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        bcLL.setMargins(0, AndroidUtils.dp(25), 0, 0);
        selectCon.addView(bottomCon, bcLL);

        blBtn = new ImageButtonCell(context, AndroidUtils.getString(R.string.all_event));
        blBtn.setOnClickListener(this);
        LinearLayout.LayoutParams blbLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        blbLL.weight = 1;
        blBtn.setPicture(R.mipmap.all_event);
        bottomCon.addView(blBtn, blbLL);

        brBtn = new ImageButtonCell(context, AndroidUtils.getString(R.string.time_location));
        brBtn.setOnClickListener(this);
        LinearLayout.LayoutParams brbLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        brbLL.weight = 1;
        brBtn.setPicture(R.mipmap.map);
        bottomCon.addView(brBtn, brbLL);

        init();
        return scrollView;
    }

    @Override
    public void onBeforeCreate() {
    }

    private void init() {
        List<Integer> resList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            resList.add(R.drawable.timg);
        }
        looperImgCell.setResList(resList);
        MapService.setGetWeatherListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent eventIntent = new Intent(context,EventActivity.class);
        if (view == tlBtn) {
            eventIntent.putExtra("eventType", Constants.EVENT_TO_DO_INDEX);
            startActivity(eventIntent);
        } else if (view == trBtn) {
            eventIntent.putExtra("eventType", Constants.EVENT_ALREADY_COMPLETED);
            startActivity(eventIntent);
        } else if (view == blBtn) {
            eventIntent.putExtra("eventType", Constants.EVENT_ALL_INDEX);
            startActivity(eventIntent);
        } else if (view == brBtn) {
            Intent intent = new Intent(context, BasicMapActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onGetWeather(String weatherStr) {
        Log.e("MainFirstPagerActivity", weatherStr);
        weatherInfo = weatherStr;
        weather.setText(weatherInfo);
    }
}
