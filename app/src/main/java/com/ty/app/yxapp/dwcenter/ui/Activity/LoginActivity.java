package com.ty.app.yxapp.dwcenter.ui.Activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.Activity.base.BaseActivity;

import java.util.zip.Inflater;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_name, et_pass;
    private Button mLoginButton, mLoginError, mRegister, ONLYTEST;
    int selectIndex = 1;
    int tempSelect = selectIndex;
    boolean isReLogin = false;
    private int SERVER_FLAG = 0;
    private RelativeLayout countryselect;
    private TextView coutry_phone_sn, coutryName;//
    // private String [] coutry_phone_sn_array,coutry_name_array;
    public final static int LOGIN_ENABLE = 0x01;    //注册完毕了
    public final static int LOGIN_UNABLE = 0x02;    //注册完毕了
    public final static int PASS_ERROR = 0x03;      //注册完毕了
    public final static int NAME_ERROR = 0x04;      //注册完毕了


    private Button bt_username_clear;
    private Button bt_pwd_clear;
    private Button bt_pwd_eye;
    private TextWatcher username_watcher;
    private TextWatcher password_watcher;

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        View loginView = this.getLayoutInflater().inflate(R.layout.activity_login, null);
        et_name = (EditText) loginView.findViewById(R.id.username);
        et_pass = (EditText) loginView.findViewById(R.id.password);

        bt_username_clear = (Button) loginView.findViewById(R.id.bt_username_clear);
        bt_pwd_clear = (Button) loginView.findViewById(R.id.bt_pwd_clear);
        bt_pwd_eye = (Button) loginView.findViewById(R.id.bt_pwd_eye);
        bt_username_clear.setOnClickListener(this);
        bt_pwd_clear.setOnClickListener(this);
        bt_pwd_eye.setOnClickListener(this);
//        initWatcher();
        et_name.addTextChangedListener(username_watcher);
        et_pass.addTextChangedListener(password_watcher);

        mLoginButton = (Button) loginView.findViewById(R.id.login);
        mLoginError = (Button) loginView.findViewById(R.id.login_error);
        mRegister = (Button) loginView.findViewById(R.id.register);
//        ONLYTEST     = (Button) findViewById(R.id.registfer);
//        ONLYTEST.setOnClickListener(this);
//        ONLYTEST.setOnLongClickListener((OnLongClickListener) this);
        mLoginButton.setOnClickListener(this);
        mLoginError.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        return loginView;
    }

    private void initViews() {

    }

    private void initWatcher() {
        username_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                et_pass.setText("");
                if (s.toString().length() > 0) {
                    bt_username_clear.setVisibility(View.VISIBLE);
                } else {
                    bt_username_clear.setVisibility(View.INVISIBLE);
                }
            }
        };

        password_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    bt_pwd_clear.setVisibility(View.VISIBLE);
                } else {
                    bt_pwd_clear.setVisibility(View.INVISIBLE);
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
//   Intent login_error_intent=new Intent();
//   login_error_intent.setClass(LoginActivity.this, ForgetCodeActivity.class);
//   startActivity(login_error_intent);
                break;
            case R.id.register:    //注册新的用户
//   Intent intent=new Intent();
//   intent.setClass(LoginActivity.this, ValidatePhoneNumActivity.class);
//   startActivity(intent);

                break;
            case R.id.bt_username_clear:
                et_name.setText("");
                et_pass.setText("");
                break;
            case R.id.bt_pwd_clear:
                et_pass.setText("");
                break;
            case R.id.bt_pwd_eye:
                if (et_pass.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    bt_pwd_eye.setBackgroundResource(R.drawable.l01);
                    et_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                } else {
                    bt_pwd_eye.setBackgroundResource(R.drawable.little);
                    et_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                et_pass.setSelection(et_pass.getText().toString().length());
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
