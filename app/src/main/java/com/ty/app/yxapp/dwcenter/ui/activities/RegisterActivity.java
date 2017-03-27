package com.ty.app.yxapp.dwcenter.ui.activities;

import android.view.View;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;

public class RegisterActivity extends BaseActivity {

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        View view = getLayoutInflater().inflate(R.layout.activity_register,null);

        return view;
    }
}
