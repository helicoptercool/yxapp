package com.ty.app.yxapp.dwcenter.ui.activities;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.network.RequestServer;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.im.ChatController;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
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
    public void onBeforeCreate() {
    }

    @Override
    public View onCreate() {
        View view = getLayoutInflater().inflate(R.layout.activity_register, null);
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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

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
        switch (v.getId()) {
            case R.id.btn_register:
                singleSend(SMS_KEY, "大哥在测试短信", "15942095489");

                if (TextUtils.isEmpty(etPhone.getText().toString()) ||
                        TextUtils.isEmpty(etPwd.getText().toString()) ||
                        TextUtils.isEmpty(etRePwd.getText().toString())) {

                    AndroidUtils.ShowToast(AndroidUtils.getString(R.string.fill_in_error));

                } else {
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
        //环信注册
        ChatController.getIntance().createAccount(etPhone.getText().toString(), etPwd.getText().toString(), new ChatController.Callback() {
            @Override
            public void success() {
                Log.d(TAG,"huanxin register success");
            }

            @Override
            public void failure(int code, String message) {
                Log.d(TAG,"huanxin register failure");
            }
        });
    }

    /**
     * 使用JDK发送单条短信,智能匹配短信模板
     *
     * @param apikey 成功注册后登录云片官网,进入后台可查看
     * @param text   需要使用已审核通过的模板或者默认模板
     * @param mobile 接收的手机号,仅支持单号码发送
     */
    public static void testSendSms(String apikey, String mobile, String text) {
        YunpianRestClient client = new YunpianRestClient(apikey);//用apikey生成client,可作为全局静态变量
        SmsOperator smsOperator = client.getSmsOperator();//获取所需操作类
        ResultDO<SendSingleSmsInfo> result = smsOperator.singleSend(mobile, text);//发送短信,ResultDO<?>.isSuccess()判断是否成功
        Log.e(TAG, result.toString());
        System.out.println(result);
    }

    /**
     * 单条短信发送,智能匹配短信模板
     *
     * @param apikey 成功注册后登录云片官网,进入后台可查看
     * @param text   需要使用已审核通过的模板或者默认模板
     * @param mobile 接收的手机号,仅支持单号码发送
     * @return json格式字符串
     */
    public static String singleSend(final String apikey, final String text, final String mobile) {
        new Thread() {
            @Override
            public void run() {
                try {

                    Map<String, String> params = new HashMap<String, String>();//请求参数集合
                    params.put("apikey", apikey);
                    params.put("text", text);
                    params.put("mobile", mobile);
                    URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
                    String s = submitPostData(params, "utf-8", url);
                    Log.e(TAG,s);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }.start();


/*        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sms.yunpian.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestServer server = retrofit.create(RequestServer.class);
        Call<String> call = server.postSMS(apikey, text, mobile);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e(TAG, "postSMS," + response.code() + "," + response.message() + "," + response.errorBody().toString() + "," + response.isSuccess() + "," + response.body());
                if (response.body() != null) {
                    Log.e(TAG, response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });*/
//        return post("https://sms.yunpian.com/v2/sms/single_send.json", params);//请自行使用post方式请求,可使用Apache HttpClient
        return "";
    }

    public static String submitPostData(Map<String, String> params, String encode, URL url) {

        byte[] data = getRequestData(params, encode).toString().getBytes();//获得请求体
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);        //设置连接超时时间
            httpURLConnection.setDoInput(true);                  //打开输入流，以便从服务器获取数据
            httpURLConnection.setDoOutput(true);                 //打开输出流，以便向服务器提交数据
            httpURLConnection.setRequestMethod("POST");    //设置以Post方式提交数据
            httpURLConnection.setUseCaches(false);               //使用Post方式不能使用缓存
            //设置请求体的类型是文本类型
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //设置请求体的长度
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
            //获得输出流，向服务器写入数据
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data);

            int response = httpURLConnection.getResponseCode();            //获得服务器的响应码
            if (response == HttpURLConnection.HTTP_OK) {
                Log.e(TAG, response + "");
                InputStream inptStream = httpURLConnection.getInputStream();
                return dealResponseResult(inptStream);                     //处理服务器的响应结果
            }else {
                Log.e(TAG,"failuri:" +
                        ""+httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }

    public static String dealResponseResult(InputStream inputStream) {
        String resultData = null;      //存储处理结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;
    }

}
