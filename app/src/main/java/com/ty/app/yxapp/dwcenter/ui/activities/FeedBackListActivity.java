package com.ty.app.yxapp.dwcenter.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.widget.EmptyView;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

public class FeedBackListActivity extends BaseActivity {

    private EmptyView emptyView;

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        actionBar.setVisibility(View.VISIBLE);
        actionBar.setCenterView(AndroidUtils.getString(R.string.feedback_list));
        actionBar.setLeftView("", R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        RelativeLayout view = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_feed_back_list,null);
        emptyView = new EmptyView(this);
        view.addView(emptyView,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            emptyView.setText(AndroidUtils.getString(R.string.no_data));
            }
        },2000);
        return view;
    }
}
