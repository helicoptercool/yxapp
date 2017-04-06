package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.network.Result;
import com.ty.app.yxapp.dwcenter.network.RetrofitHelper;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.network.RequestServer;
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.utils.SPManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText etName, etPass;


    private Button btUsernameClear;
    private Button btPwdClear;
//    private Button btPwdEye;
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
        btUsernameClear.setOnClickListener(this);
        btPwdClear.setOnClickListener(this);
        initWatcher();
        etName.addTextChangedListener(usernameWatcher);
        etPass.addTextChangedListener(passwordWatcher);

        Button mLoginButton = (Button) loginView.findViewById(R.id.login);
        TextView mLoginError = (TextView) loginView.findViewById(R.id.forget_password);
        TextView mRegister = (TextView) loginView.findViewById(R.id.tx_register);
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
            case R.id.login:
                login();
                break;
            case R.id.forget_password:
                Intent pwdIntent = new Intent(this, ForgetPwdActivity.class);
                startActivity(pwdIntent);
                break;
            case R.id.tx_register:
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
        }
    }

    private void login() {
        final String phone = etName.getText().toString();
        final String password = etPass.getText().toString();
        Log.e(TAG,"name="+phone+",pwd="+password);
        if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)){
            AndroidUtils.ShowToast(AndroidUtils.getString(R.string.fill_in_error));
        }else{
            RetrofitHelper.getInstance().Login(phone, password, new RetrofitHelper.OnResultListener() {
                @Override
                public void onResult(Result result) {
                    Log.e(TAG,result.getMessage()+","+result.getCode());
                    if(result.isOK()){
                        SPManager manager = new SPManager();
                        manager.writeSp(Constants.SP_USER_NAME,phone);
                        manager.writeSp(Constants.SP_PASSWORD,password);
                        manager.writeSp(Constants.SP_IS_LOGIN,true);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        AndroidUtils.ShowToast(result.getMessage());
                    }
                }
            });
        }
    }

}
