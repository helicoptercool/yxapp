package com.ty.app.yxapp.dwcenter.network;

import android.util.Log;

import com.ty.app.yxapp.dwcenter.bean.Event;
import com.ty.app.yxapp.dwcenter.bean.FileUpload;
import com.ty.app.yxapp.dwcenter.bean.StringResult;
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by heli on 2017/4/3.
 */
public class RetrofitHelper {
    private static final String TAG = "RetrofitHelper";
    private static final String LOGIN = "login";
    private static final String GET_EVENTS = "getEvents";
    private static final String REPORT_EVENT = "reportEvent";
    private static final String GET_USER_INFO = "getUserInfo";
    private static final String GET_ORG_DATA_INFO = "getOrgDataInfo";
    private static final String SET_PASSWORD = "setPassword";
    private static final String UPLOAD_VIDEO = "uploadVideo";
    private static final String UPLOAD_AUDIO = "uploadAudio";

    private static RetrofitHelper mRetrofitHelper;
    private static Retrofit mRetrofit;
    private RequestServer requestServer;

    private RetrofitHelper() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.TEST_SEVICE_ADDRESS)
                        //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                        //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestServer();
    }

    public static RetrofitHelper getInstance() {
        if (mRetrofitHelper == null) {
            synchronized (RetrofitHelper.class) {
                if (mRetrofitHelper == null) {
                    mRetrofitHelper = new RetrofitHelper();
                }
            }
        }
        return mRetrofitHelper;
    }

    private void RequestServer() {
        if (mRetrofit != null) {
            requestServer = mRetrofit.create(RequestServer.class);
        }
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    /**
     * Login
     */
    public void Login(String name, String psw, OnResultListener onResultListener) {
        Log.e(TAG, "login");
        OnCallBackListener onCallBackListener = new OnCallBackListener(LOGIN, onResultListener);
        Call<StringResult> call = requestServer.getLoginStatus(name, psw);
        if (call != null) call.enqueue(onCallBackListener);
    }


    public void getEvents(String account, String eventType, OnResultListener onResultListener) {
        OnCallBackListener onCallBackListener = new OnCallBackListener(GET_EVENTS, onResultListener);
        Call<Event> call = requestServer.getEvents(account, eventType);
        if (call != null) {
            call.enqueue(onCallBackListener);
        }
    }


    public void uploadVideo(File file, String fileName, OnResultListener onResultListener) {
        OnCallBackListener onCallBackListener = new OnCallBackListener(UPLOAD_VIDEO, onResultListener);
        Call<FileUpload> call = requestServer.uploadVideo(file, fileName);
        if (call != null) {
            call.enqueue(onCallBackListener);
        }

    }

    public void setUploadAudio(File file, String fileName, OnResultListener onResultListener){
        OnCallBackListener onCallBackListener = new OnCallBackListener(UPLOAD_AUDIO,onResultListener);
        Call<FileUpload> call = requestServer.uploadAudio(file, fileName);
        if(call != null){
            call.enqueue(onCallBackListener);
        }
    }

    public interface OnResultListener {
        void onResult(Result result);
    }

    public class OnCallBackListener implements Callback {
        private OnResultListener onResultListener;
        private String flag;

        public OnCallBackListener(String flag, OnResultListener onResultListener) {
            this.flag = flag;
            this.onResultListener = onResultListener;
        }

        @Override
        public void onResponse(Call call, Response response) {
            Log.e(TAG, response.message() + "," + response.code() + "," + response.body() + "," + response.errorBody());
            if (call.isCanceled()) return;
            int code = -1;
            String message = "网络异常";
            String url = call.request().url().toString();

            if (!response.isSuccess()) {
                code = ((Event) response.body()).getCode();
                message = ((Event) response.body()).getMsg();
                if (onResultListener != null) {
                    onResultListener.onResult(new Result(code, message));
                }
            } else {
                code = ((Event) response.body()).getCode();
                try {
                    Result result = new Result();
                    switch (flag){
                        case LOGIN:
                            result = result.LoginResult(response);
                            break;
                        case GET_EVENTS:
                            result = result.getEvents(response);
                            break;
                        case UPLOAD_VIDEO:
                            result  = result.uploadFile(response);
                            break;
                        case UPLOAD_AUDIO:
                            result = result.uploadFile(response);
                            break;
                        default:
                            break;
                    }
                    if (onResultListener != null) {
                        onResultListener.onResult(result);
                    }
                    Log.d(TAG, "url :" + url + " result: " + response.body());
                } catch (Exception e) {
                    Log.e(TAG, "url :" + url + " ," + new Throwable(e).toString());
                    if (onResultListener != null) {
                        onResultListener.onResult(new Result(code, message));
                    }
                    return;
                }
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            Log.e(TAG, t.toString());
            if (call.isCanceled()) return;
            Result result = new Result(-1, "网络异常");
            if (onResultListener != null) onResultListener.onResult(result);
        }
    }
}
