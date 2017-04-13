package com.ty.app.yxapp.dwcenter.ui.activities;

import android.app.Application;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.network.Result;
import com.ty.app.yxapp.dwcenter.network.RetrofitHelper;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.ui.activities.base.MyApplication;
import com.ty.app.yxapp.dwcenter.ui.widget.MyDialog;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.utils.SPManager;
import com.ty.app.yxapp.dwcenter.ui.im.ChatController;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText etName, etPass;
    boolean isReLogin = false;


    private Button btUsernameClear;
    private Button btPwdClear;
    private ImageButton ibSet;
    //    private Button btPwdEye;
    private TextWatcher usernameWatcher;
    private TextWatcher passwordWatcher;
    private SPManager manager;
    private long exitTime = 0;

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        View loginView = this.getLayoutInflater().inflate(R.layout.activity_login, null);
        etName = (EditText) loginView.findViewById(R.id.username);
        etPass = (EditText) loginView.findViewById(R.id.password);
        ibSet = (ImageButton) loginView.findViewById(R.id.ib_set_server);
        manager = new SPManager();
        etName.setText(manager.readSp(Constants.SP_USER_NAME));

        btUsernameClear = (Button) loginView.findViewById(R.id.bt_username_clear);
        btPwdClear = (Button) loginView.findViewById(R.id.bt_pwd_clear);
        btUsernameClear.setOnClickListener(this);
        btPwdClear.setOnClickListener(this);
        ibSet.setOnClickListener(this);
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
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
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
            case R.id.ib_set_server:
                MyDialog myDialog = new MyDialog(this);
                myDialog.setDialogCallback(dialogcallback);
                myDialog.show();
                break;
            default:
                break;
        }
    }

    MyDialog.Dialogcallback dialogcallback = new MyDialog.Dialogcallback() {
        @Override
        public void dialogdo(String string) {
            if (string.startsWith("http")) {
                SPManager manager = new SPManager();
                manager.writeSp(Constants.SP_SET_SERVER_ADDRESS, string);
            }
        }
    };

    private void login() {
        final String phone = etName.getText().toString();
        final String password = etPass.getText().toString();
        Log.e(TAG, "name=" + phone + ",pwd=" + password);
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            AndroidUtils.ShowToast(AndroidUtils.getString(R.string.fill_in_error));
        } else {
            final SVProgressHUD loading = new SVProgressHUD(this);
            loading.showWithStatus(AndroidUtils.getString(R.string.requesting));
            RetrofitHelper.getInstance().login(phone, password, new RetrofitHelper.OnResultListener() {
                @Override
                public void onResult(Result result) {
                    Log.e(TAG, result.getMessage() + "," + result.getCode() + "," + result.getData());
                    loading.dismiss();
                    if (result.isOK()) {
                        manager.writeSp(Constants.SP_USER_NAME, phone);
                        manager.writeSp(Constants.SP_PASSWORD, password);
                        manager.writeSp(Constants.SP_IS_LOGIN, true);
                        if (manager.readSp(Constants.SP_SET_SERVER_ADDRESS).equals("")) {
                            manager.writeSp(Constants.SP_SET_SERVER_ADDRESS, Constants.BASE_SEVICE_ADDRESS);
                        }
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        MyApplication.handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 100);

/*                        ChatController.getIntance().login(phone, password, new ChatController.Callback() {
                            @Override
                            public void success() {
                                Log.d(TAG, "huanxin login success");
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void failure(int code, String message) {
                                Log.d(TAG, "huanxin login failure:" + code + " ,message:" + message);
                                if (code == 202) {//当前用户没有环信账号，注册环信账号,注册成功后去登录环信
                                    ChatController.getIntance().createAccount(phone, password, new ChatController.Callback() {
                                        @Override
                                        public void success() {
                                            ChatController.getIntance().login(phone, password,null);
                                        }

                                        @Override
                                        public void failure(int code, String message) {
                                            Log.d(TAG, "huanxin createAccount failure");
                                        }
                                    });
                                    Log.d(TAG, "huanxin createAccount success");
                                }
                            }
                        });*/

                    } else {
                        AndroidUtils.ShowToast(result.getMessage());
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            AndroidUtils.ShowToast(AndroidUtils.getString(R.string.press_again_to_exit));
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
