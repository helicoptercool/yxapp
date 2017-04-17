package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.network.Result;
import com.ty.app.yxapp.dwcenter.network.RetrofitHelper;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.utils.SPManager;

public class SetActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvPhone;
    private EditText etPwd;
    private EditText etNewPwd;
    private Button btnResetPwd;
    private Button btnLogout;
//    private Button btnPhoneClear;
    private Button btnPwdClear;
    private Button btnNewPwdClear;
//    private TextWatcher phoneWatcher;
    private TextWatcher pwdWatcher;
    private TextWatcher rePwdWatcher;
    private boolean modifyPwd;
    private SPManager manager;

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        Intent intent = getIntent();
        if(intent.getStringExtra("from") != null && intent.getStringExtra("from").equals("forgetPwd")){
            modifyPwd = true;
        }
        actionBar.setVisibility(View.VISIBLE);
        actionBar.setCenterView(AndroidUtils.getString(R.string.set));
        actionBar.setLeftView("", R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        View view = getLayoutInflater().inflate(R.layout.activity_set,null);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        tvPhone = (TextView) view.findViewById(R.id.set_username);
        etPwd = (EditText) view.findViewById(R.id.original_password);
        etNewPwd = (EditText) view.findViewById(R.id.new_password);
        btnResetPwd = (Button) view.findViewById(R.id.reset_pwd);
        btnLogout = (Button) view.findViewById(R.id.logout);
        if(modifyPwd){
            btnLogout.setVisibility(View.INVISIBLE);
        }
//        btnPhoneClear = (Button) view.findViewById(R.id.bt_set_username_clear);
        btnPwdClear = (Button) view.findViewById(R.id.bt_original_pwd_clear);
        btnNewPwdClear = (Button) view.findViewById(R.id.bt_new_pwd_clear);
        btnResetPwd.setOnClickListener(this);
//        btnPhoneClear.setOnClickListener(this);
        btnPwdClear.setOnClickListener(this);
        btnNewPwdClear.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        initWatcher();
//        btnPhoneClear.addTextChangedListener(phoneWatcher);
        etPwd.addTextChangedListener(pwdWatcher);
        etNewPwd.addTextChangedListener(rePwdWatcher);
        manager = new SPManager();
        tvPhone.setText(manager.readSp(Constants.SP_USER_NAME));
    }

    private void initWatcher() {
        /*phoneWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                etPwd.setText("");
                etNewPwd.setText("");
                if (s.toString().length() > 0) {
                    btnPhoneClear.setVisibility(View.VISIBLE);
                } else {
                    btnPhoneClear.setVisibility(View.INVISIBLE);
                }
            }
        };*/
        pwdWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                etNewPwd.setText("");
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
                    btnNewPwdClear.setVisibility(View.VISIBLE);
                } else {
                    btnNewPwdClear.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           /* case R.id.bt_set_username_clear:
                tvPhone.setText("");
                etPwd.setText("");
                etNewPwd.setText("");
                break;*/
            case R.id.bt_original_pwd_clear:
                etPwd.setText("");
                etNewPwd.setText("");
                break;
            case R.id.bt_new_pwd_clear:
                etNewPwd.setText("");
                break;
            case R.id.reset_pwd:
                resetPwd();
                break;
            case R.id.logout:
                gotoLoginActivity();
                break;
            default:
                break;
        }
    }

    private void gotoLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void resetPwd() {
        if(TextUtils.isEmpty(etPwd.getText()) || TextUtils.isEmpty(etNewPwd.getText())){
            AndroidUtils.ShowToast(AndroidUtils.getString(R.string.fill_in_error));
        }else {
            RetrofitHelper.getInstance().setPassword(manager.readSp(Constants.SP_USER_NAME), etNewPwd.getText().toString(), etPwd.getText().toString(), new RetrofitHelper.OnResultListener() {
                @Override
                public void onResult(Result result) {
                    if(result.isOK()){
                        AndroidUtils.ShowToast(AndroidUtils.getString(R.string.reset_password_success));
                        gotoLoginActivity();
                    }else {
                        AndroidUtils.ShowToast(result.getMessage());
                    }
                }
            });
        }
    }
}
