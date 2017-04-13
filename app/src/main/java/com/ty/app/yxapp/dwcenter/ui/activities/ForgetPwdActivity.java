package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.network.RegisterCodeTimerService;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.utils.RegisterCodeTimer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgetPwdActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = ForgetPwdActivity.class.getSimpleName();
    private final static String PHONE_PATTERN = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    private EditText etPhone;
    private EditText etPwd;
    private Button btnGetCode;
    private Button btnSure;
    private Intent mIntent;
    private boolean verify;
    private int verifyCode = -1;
    private Handler mCodeHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == RegisterCodeTimer.IN_RUNNING) {// 正在倒计时
                btnGetCode.setText(msg.obj.toString());
            } else if (msg.what == RegisterCodeTimer.END_RUNNING) {// 完成倒计时
                btnGetCode.setEnabled(true);
                btnGetCode.setBackground(getResources().getDrawable(R.drawable.verify_code_blue_shape));
                btnGetCode.setText(msg.obj.toString());
            }
        }
    };

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        View view = getLayoutInflater().inflate(R.layout.activity_forget_pwd, null);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        etPhone = (EditText) view.findViewById(R.id.foget_pwd_phone);
        etPwd = (EditText) view.findViewById(R.id.foget_pwd_password);
        btnGetCode = (Button) view.findViewById(R.id.tv_verfy_code);
        btnSure = (Button) view.findViewById(R.id.forget_password_sure_btn);
        btnGetCode.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        RegisterCodeTimerService.setHandler(mCodeHandler);
        mIntent = new Intent(this, RegisterCodeTimerService.class);
    }

    @Override
    public void onClick(View v) {
        String phone = etPhone.getText().toString();
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        if (!TextUtils.isEmpty(phone)) {
            if (matcher.find()) {
                switch (v.getId()) {
                    case R.id.tv_verfy_code:
                        btnGetCode.setEnabled(false);
                        btnGetCode.setBackground(getResources().getDrawable(R.drawable.verify_code_gray_shape));
                        startService(mIntent);
                        verifyCode = createRandom();
/*                        YunpianRestClient client = new YunpianRestClient("3cd2d05c6da747bb62f35b9b84617009");//用apikey生成client,可作为全局静态变量
                        SmsOperator smsOperator = client.getSmsOperator();//获取所需操作类
                        ResultDO<SendSingleSmsInfo> result = smsOperator.singleSend("15942095489", "你的验证码是"+verifyCode);//发送短信,ResultDO<?>.isSuccess()判断是否成功
                        Log.e(TAG, "verify code result" + result);*/
                        String result = "success=true";
                        //ResultDO{data=SendSingleSmsInfo{code=0, msg='发送成功', count=1, fee=0.05, unit='RMB', mobile='15942095489', sid='14670564798'}, success=true, e=null}
                        if (result.toString().contains("success=true")) {
                            verify = true;
                        } else {
//                            AndroidUtils.ShowToast(result.getData().getMsg());
                        }
                        break;
                    case R.id.forget_password_sure_btn:
                        if (verify && verifyCode > 0) {
                            if (TextUtils.isEmpty(etPwd.getText())) {
                                AndroidUtils.ShowToast(AndroidUtils.getString(R.string.input_verify_code));
                            } else if (!etPwd.getText().toString().equals("" + verifyCode)) {
                                AndroidUtils.ShowToast(AndroidUtils.getString(R.string.verify_code_error));
                            } else {
                                Intent intent = new Intent(this, SetActivity.class);
                                intent.putExtra("from", "forgetPwd");
                                startActivity(intent);
                            }
                        } else {
                            AndroidUtils.ShowToast(AndroidUtils.getString(R.string.verify_failure));
                        }
                        break;
                    default:
                        break;
                }
            } else {
                AndroidUtils.ShowToast(AndroidUtils.getString(R.string.input_correct_phone));
            }
        } else {
            AndroidUtils.ShowToast(AndroidUtils.getString(R.string.input_correct_phone));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(mIntent);
    }

    private int createRandom() {
        Random random = new Random();
        return random.nextInt(10000 - 1000 + 1) + 1000;
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
                System.out.println(response + "");
                InputStream inptStream = httpURLConnection.getInputStream();
                return dealResponseResult(inptStream);                     //处理服务器的响应结果
            } else {
                System.out.println("failuri:" + "" + httpURLConnection.getResponseCode());
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
