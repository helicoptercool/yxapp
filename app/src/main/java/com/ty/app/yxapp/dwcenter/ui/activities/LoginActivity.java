package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText etName, etPass;
    private Button mLoginButton, mLoginError, mRegister;
    boolean isReLogin = false;


    private Button btUsernameClear;
    private Button btPwdClear;
    private Button btPwdEye;
    private TextWatcher usernameWatcher;
    private TextWatcher passwordWatcher;

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        View loginView = this.getLayoutInflater().inflate(R.layout.activity_login, null);
        etName = (EditText) loginView.findViewById(R.id.username);
        etPass = (EditText) loginView.findViewById(R.id.password);

        btUsernameClear = (Button) loginView.findViewById(R.id.bt_username_clear);
        btPwdClear = (Button) loginView.findViewById(R.id.bt_pwd_clear);
        btPwdEye = (Button) loginView.findViewById(R.id.bt_pwd_eye);
        btUsernameClear.setOnClickListener(this);
        btPwdClear.setOnClickListener(this);
        btPwdEye.setOnClickListener(this);
        initWatcher();
        etName.addTextChangedListener(usernameWatcher);
        etPass.addTextChangedListener(passwordWatcher);

        mLoginButton = (Button) loginView.findViewById(R.id.login);
        mLoginError = (Button) loginView.findViewById(R.id.login_error);
        mRegister = (Button) loginView.findViewById(R.id.register);
        mLoginButton.setOnClickListener(this);
        mLoginError.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        return loginView;
    }

    private void initWatcher() {
        usernameWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                etPass.setText("");
                if (s.toString().length() > 0) {
                    btUsernameClear.setVisibility(View.VISIBLE);
                } else {
                    btUsernameClear.setVisibility(View.INVISIBLE);
                }
            }
        };

        passwordWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    btPwdClear.setVisibility(View.VISIBLE);
                } else {
                    btPwdClear.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:  //登陆
                //   login();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.login_error: //无法登陆(忘记密码了吧)
                Intent pwdIntent = new Intent(this,ForgetPwdActivity.class);
                startActivity(pwdIntent);
                break;
            case R.id.register:    //注册新的用户
                Intent regIntent = new Intent();
                regIntent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(regIntent);

                break;
            case R.id.bt_username_clear:
                etName.setText("");
                etPass.setText("");
                break;
            case R.id.bt_pwd_clear:
                etPass.setText("");
                break;
            case R.id.bt_pwd_eye:
                if (etPass.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    btPwdEye.setBackgroundResource(R.drawable.eye_on);
                    etPass.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    btPwdEye.setBackgroundResource(R.drawable.eye_off);
                    etPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                etPass.setSelection(etPass.getText().toString().length());
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (isReLogin) {
                Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
                mHomeIntent.addCategory(Intent.CATEGORY_HOME);
                mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                LoginActivity.this.startActivity(mHomeIntent);
            } else {
                LoginActivity.this.finish();
            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
