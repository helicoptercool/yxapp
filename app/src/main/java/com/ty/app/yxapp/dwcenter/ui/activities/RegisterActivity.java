package com.ty.app.yxapp.dwcenter.ui.activities;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;

public class RegisterActivity extends BaseActivity {

    private EditText etPhone;
    private EditText etPwd;
    private EditText etRePwd;
    private Button btnRegister;

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        View view = getLayoutInflater().inflate(R.layout.activity_register,null);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

    }
}
