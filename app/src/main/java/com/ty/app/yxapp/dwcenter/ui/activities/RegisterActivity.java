package com.ty.app.yxapp.dwcenter.ui.activities;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private EditText etPhone;
    private EditText etPwd;
    private EditText etRePwd;
    private Button btnRegister;
    private Button btnPhoneClear;
    private Button btnPwdClear;
    private Button btnRePwdClear;

    private TextWatcher phoneWatcher;
    private TextWatcher pwdWatcher;
    private TextWatcher rePwdWatcher;

    @Override
    public void onBeforeCreate() {}

    @Override
    public View onCreate() {
        View view = getLayoutInflater().inflate(R.layout.activity_register,null);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        etPhone = (EditText) view.findViewById(R.id.reg_username);
        etPwd = (EditText) view.findViewById(R.id.reg_password);
        etRePwd = (EditText) view.findViewById(R.id.reg_re_password);
        btnRegister = (Button) view.findViewById(R.id.btn_register);
        btnPhoneClear = (Button) view.findViewById(R.id.reg_username_clear);
        btnPwdClear = (Button) view.findViewById(R.id.reg_pwd_clear);
        btnRePwdClear = (Button) view.findViewById(R.id.reg_bt_pwd_clear);
        initWatcher();
        etPhone.addTextChangedListener(phoneWatcher);
        etPwd.addTextChangedListener(pwdWatcher);
        etRePwd.addTextChangedListener(rePwdWatcher);
        btnRegister.setOnClickListener(this);
        btnPhoneClear.setOnClickListener(this);
        btnPwdClear.setOnClickListener(this);
        btnRePwdClear.setOnClickListener(this);
    }

    private void initWatcher() {
        phoneWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                etPwd.setText("");
                etRePwd.setText("");
                if (s.toString().length() > 0) {
                    btnPhoneClear.setVisibility(View.VISIBLE);
                } else {
                    btnPhoneClear.setVisibility(View.INVISIBLE);
                }
            }
        };
        pwdWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                etRePwd.setText("");
                if (s.toString().length() > 0) {
                    btnPwdClear.setVisibility(View.VISIBLE);
                } else {
                    btnPwdClear.setVisibility(View.INVISIBLE);
                }
            }
        };
        rePwdWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    btnRePwdClear.setVisibility(View.VISIBLE);
                } else {
                    btnRePwdClear.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                if(TextUtils.isEmpty(etPhone.getText()) || TextUtils.isEmpty(etPwd.getText()) || TextUtils.isEmpty(etRePwd.getText())){
                    AndroidUtils.ShowToast(AndroidUtils.getString(R.string.fill_in_error));
                }else {
                    register();
                }
                break;
            case R.id.reg_username_clear:
                etPhone.setText("");
                etPwd.setText("");
                etRePwd.setText("");
                break;
            case R.id.reg_pwd_clear:
                etPwd.setText("");
                etRePwd.setText("");
                break;
            case R.id.reg_bt_pwd_clear:
                etRePwd.setText("");
                break;
            default:
                break;
        }

    }

    private void register() {

    }
}
