package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amap.api.maps2d.model.Text;
import com.bigkoo.svprogresshud.SVProgressHUD;
import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.network.RequestServer;
import com.ty.app.yxapp.dwcenter.network.Result;
import com.ty.app.yxapp.dwcenter.network.RetrofitHelper;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.ui.im.ChatController;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.utils.SPManager;
import com.yunpian.sdk.model.ResultDO;
import com.yunpian.sdk.model.SendSingleSmsInfo;
import com.yunpian.sdk.service.SmsOperator;
import com.yunpian.sdk.service.YunpianRestClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private static final String SMS_KEY = "3cd2d05c6da747bb62f35b9b84617009";

    private EditText etUserName;
    private EditText etPwd;
    private EditText etName;
    private EditText etPhone;
    private EditText etIdCard;

    private Button btnRegister;
    private Button btnUserNameClear;
    private Button btnPwdClear;
    private Button btnNameClear;
    private Button btnPhoneClear;
    private Button btnIdCardClear;

    private TextWatcher userNameWatcher;
    private TextWatcher pwdWatcher;
    private TextWatcher nameWatcher;
    private TextWatcher phoneWatcher;
    private TextWatcher idCardWatcher;

    private String userName;
    private String password;
    private String name;
    private String phone;
    private String idCard;

    @Override
    public void onBeforeCreate() {
    }

    @Override
    public View onCreate() {
        View view = getLayoutInflater().inflate(R.layout.activity_register, null);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        etUserName = (EditText) view.findViewById(R.id.reg_username);
        etPwd = (EditText) view.findViewById(R.id.reg_password);
        etName = (EditText) view.findViewById(R.id.reg_name);
        etPhone = (EditText) view.findViewById(R.id.reg_phone);
        etIdCard = (EditText) view.findViewById(R.id.reg_id_card);

        btnRegister = (Button) view.findViewById(R.id.btn_register);
        btnUserNameClear = (Button) view.findViewById(R.id.reg_username_clear);
        btnPwdClear = (Button) view.findViewById(R.id.reg_pwd_clear);
        btnNameClear = (Button) view.findViewById(R.id.reg_name_clear);
        btnPhoneClear = (Button) view.findViewById(R.id.reg_phone_clear);
        btnIdCardClear = (Button) view.findViewById(R.id.reg_id_card_clear);

        initWatcher();

        btnRegister.setOnClickListener(this);
        btnUserNameClear.setOnClickListener(this);
        btnPwdClear.setOnClickListener(this);
        btnNameClear.setOnClickListener(this);
        btnPhoneClear.setOnClickListener(this);
        btnIdCardClear.setOnClickListener(this);
    }

    private void initWatcher() {
        userNameWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    btnUserNameClear.setVisibility(View.VISIBLE);
                } else {
                    btnUserNameClear.setVisibility(View.INVISIBLE);
                }
            }
        };
        pwdWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    btnPwdClear.setVisibility(View.VISIBLE);
                } else {
                    btnPwdClear.setVisibility(View.INVISIBLE);
                }
            }
        };
        nameWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    btnNameClear.setVisibility(View.VISIBLE);
                } else {
                    btnNameClear.setVisibility(View.INVISIBLE);
                }
            }
        };
        phoneWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    btnPhoneClear.setVisibility(View.VISIBLE);
                } else {
                    btnPhoneClear.setVisibility(View.INVISIBLE);
                }
            }
        };
        idCardWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    btnIdCardClear.setVisibility(View.VISIBLE);
                } else {
                    btnIdCardClear.setVisibility(View.INVISIBLE);
                }
            }
        };

        etUserName.addTextChangedListener(userNameWatcher);
        etPwd.addTextChangedListener(pwdWatcher);
        etName.addTextChangedListener(nameWatcher);
        etPhone.addTextChangedListener(phoneWatcher);
        etIdCard.addTextChangedListener(idCardWatcher);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                userName = etUserName.getText().toString().trim();
                password = etPwd.getText().toString().trim();
                name = etName.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                idCard = etIdCard.getText().toString().trim();

                if (TextUtils.isEmpty(userName) ||
                        TextUtils.isEmpty(password) ||
                        TextUtils.isEmpty(name) ||
                        TextUtils.isEmpty(phone) ||
                        TextUtils.isEmpty(idCard)
                        ) {

                    AndroidUtils.ShowToast(AndroidUtils.getString(R.string.fill_in_error));

                } else {
                    register();
                }
                break;
            case R.id.reg_username_clear:
                etUserName.setText("");
                break;
            case R.id.reg_pwd_clear:
                etPwd.setText("");
                break;
            case R.id.reg_name_clear:
                etName.setText("");
                break;
            case R.id.reg_phone_clear:
                etPhone.setText("");
                break;
            case R.id.reg_id_card_clear:
                etIdCard.setText("");
                break;
            default:
                break;
        }

    }

    private void register() {
        final SVProgressHUD loading = new SVProgressHUD(this);
        loading.showWithStatus(AndroidUtils.getString(R.string.requesting));
        RetrofitHelper.getInstance().register(userName, password, name, phone, idCard, new RetrofitHelper.OnResultListener() {
            @Override
            public void onResult(Result result) {
                loading.dismiss();
                if (result.isOK()) {
                    AndroidUtils.ShowToast(result.getMessage());
                    SPManager manager = new SPManager();
                    manager.writeSp(Constants.SP_USER_NAME,userName);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    AndroidUtils.ShowToast(result.getMessage());
                }
            }
        });
        //环信注册
        ChatController.getIntance().createAccount(etPhone.getText().toString(), etPwd.getText().toString(), new ChatController.Callback() {
            @Override
            public void success() {
                Log.d(TAG, "huanxin register success");
            }

            @Override
            public void failure(int code, String message) {
                Log.d(TAG, "huanxin register failure");
            }
        });
    }


}
