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
import com.ty.app.yxapp.dwcenter.ui.im.ChatController;

import java.util.logging.Logger;
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

        Button mLoginButton = (Button) loginView.findViewById(R.id.login);
        TextView mLoginError = (TextView) loginView.findViewById(R.id.forget_password);
        TextView mRegister = (TextView) loginView.findViewById(R.id.register);
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
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.forget_password:
                Intent pwdIntent = new Intent(this, ForgetPwdActivity.class);
                startActivity(pwdIntent);
                break;
            case R.id.register:
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

    private void login() {
        String phone = etName.getText().toString();
        String password = etPass.getText().toString();
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher m = p.matcher(phone);

        RetrofitHelper.getInstance().Login("wangjie", "wangjie123456", new RetrofitHelper.OnResultListener() {
            @Override
            public void onResult(Result result) {
                if(result.isOK()){
                    ChatController.getIntance().login("wangjie123456", "wangjie123456", new ChatController.Callback() {
                        @Override
                        public void success() {
                            Log.d(TAG,"huanxin login success");
                        }

                        @Override
                        public void failure(int code, String message) {
                            if(code == 202){//判断当前用户没有注册环信账号
//                                ChatController.getIntance().createAccount("","");
                                Log.d(TAG,"create account");
                            }
                            Log.d(TAG,"huanxin login error:"+code+" ,message:"+message);
                        }
                    });
                }else{
                    //TODO:登录失败
                }
            }
        });

    }

}
