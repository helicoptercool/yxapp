package com.ty.app.yxapp.dwcenter.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.fragment.MainFirstPagerActivity;
import com.ty.app.yxapp.dwcenter.utils.EventAdapter;

public class AllEventActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = AllEventActivity.class.getSimpleName();

    private ListView eventLv;
    private Adapter mAdapter;


    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        View view = getLayoutInflater().inflate(R.layout.activity_all_event, null);
        eventLv = (ListView) view.findViewById(R.id.listview_event);
        mAdapter = new EventAdapter(this, MainFirstPagerActivity.allEvents);
        eventLv.setAdapter((ListAdapter) mAdapter);
        eventLv.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
