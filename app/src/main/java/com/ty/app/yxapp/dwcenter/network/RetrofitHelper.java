package com.ty.app.yxapp.dwcenter.network;

import android.util.Log;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.bean.Event;
import com.ty.app.yxapp.dwcenter.bean.EventUpload;
import com.ty.app.yxapp.dwcenter.bean.FileUpload;
import com.ty.app.yxapp.dwcenter.bean.OrgDataInfo;
import com.ty.app.yxapp.dwcenter.bean.StringResult;
import com.ty.app.yxapp.dwcenter.bean.UserInfo;
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Query;

/**
 * Created by heli on 2017/4/3.
 */
public class RetrofitHelper {
    private static final String TAG = "RetrofitHelper";
    private static final String REGISTER = "register";
    private static final String LOGIN = "login";
    private static final String GET_EVENTS = "getEvents";
    private static final String REPORT_EVENT = "reportEvent";
    private static final String GET_ORG_DATA_INFO = "getOrgDataInfo";
    private static final String GET_USER_INFO = "getUserInfo";
    private static final String SET_PASSWORD = "setPassword";
    private static final String UPLOAD_VIDEO = "uploadVideo";
    private static final String UPLOAD_AUDIO = "uploadAudio";
    private static final String UPLOAD_IMAGE = "uploadImage";
    private static final String SET_COORDINATE = "setCoordinate";

    private static RetrofitHelper mRetrofitHelper;
    private static Retrofit mRetrofit;
    private RequestServer requestServer;

    private RetrofitHelper() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_SEVICE_ADDRESS)
                .addConverterFactory(ScalarsConverterFactory.create())
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

    public void register(String account, String password, String name, String mobilephone, String identityNum, OnResultListener onResultListener) {
        OnCallBackListener onCallBackListener = new OnCallBackListener(REGISTER, onResultListener);
        Call<StringResult> call = requestServer.register(account, password, name, mobilephone, identityNum);
        if (call != null) {
            call.enqueue(onCallBackListener);
        }
    }

    public void login(String name, String psw, OnResultListener onResultListener) {
        Log.e(TAG, "login");
        OnCallBackListener onCallBackListener = new OnCallBackListener(LOGIN, onResultListener);
        Call<String> call = requestServer.login(name, psw);
        if (call != null) {
            call.enqueue(onCallBackListener);
        }
    }

    public void setPassword(String account, String newPassword, String oldPassword, OnResultListener onResultListener) {
        OnCallBackListener onCallBackListener = new OnCallBackListener(SET_PASSWORD, onResultListener);
        Call<StringResult> call = requestServer.setPassword(account, newPassword, oldPassword);
        if (call != null) {
            call.enqueue(onCallBackListener);
        }
    }

    public void getEvents(String account, String eventType, OnResultListener onResultListener) {
        OnCallBackListener onCallBackListener = new OnCallBackListener(GET_EVENTS, onResultListener);
        Call<Event> call = requestServer.getEvents(account, eventType);
        if (call != null) {
            call.enqueue(onCallBackListener);
        }
    }

    public void reportEvent(String account, String eventType, String address, String eventX, String eventY,
                            String eventMs, String eventMc, String eventId, String eventClyj, OnResultListener onResultListener) {
        OnCallBackListener onCallBackListener = new OnCallBackListener(REPORT_EVENT, onResultListener);
        Call<EventUpload> call = requestServer.reportEvent(account, eventType, address, eventX, eventY, eventMs, eventMc, eventId, eventClyj);
        if (call != null) {
            call.enqueue(onCallBackListener);
        }
    }

    public void getUserInfo(String orgNo, String account, OnResultListener onResultListener) {
        OnCallBackListener onCallBackListener = new OnCallBackListener(GET_USER_INFO, onResultListener);
        Call<UserInfo> call = requestServer.getUserInfo(orgNo, account);
        if (call != null) {
            call.enqueue(onCallBackListener);
        }
    }

    public void getOrgData(String orgNo, String account, OnResultListener onResultListener) {
        OnCallBackListener onCallBackListener = new OnCallBackListener(GET_ORG_DATA_INFO, onResultListener);
        Call<OrgDataInfo> call = requestServer.getOrgData(orgNo, account);
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

    public void uploadAudio(File file, String fileName, OnResultListener onResultListener) {
        OnCallBackListener onCallBackListener = new OnCallBackListener(UPLOAD_AUDIO, onResultListener);
        Call<FileUpload> call = requestServer.uploadAudio(file, fileName);
        if (call != null) {
            call.enqueue(onCallBackListener);
        }
    }

    public void uploadImage(File file, String fileName, OnResultListener onResultListener) {
        OnCallBackListener onCallBackListener = new OnCallBackListener(UPLOAD_IMAGE, onResultListener);
        Call<FileUpload> call = requestServer.uploadImage(file, fileName);
        if (call != null) {
            call.enqueue(onCallBackListener);
        }
    }

    public void setCoordinate(String account,String address,
                              String x, String y,String coordsType,String deviceId, OnResultListener onResultListener){
        OnCallBackListener onCallBackListener = new OnCallBackListener(SET_COORDINATE,onResultListener);
        Call<StringResult> call = requestServer.setCoordinate(account,address,x,y,coordsType,deviceId);
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
            int code = -200;
            String message = AndroidUtils.getString(R.string.no_network);
            String url = call.request().url().toString();

            if (!response.isSuccess()) {
                code = response.code();
                message = response.message();
                if (onResultListener != null) {
                    onResultListener.onResult(new Result(code, message));
                }
            } else {
                try {
                    Result result = new Result();
                    switch (flag) {
                        case REGISTER:
                            result = result.register(response);
                            break;
                        case LOGIN:
                            result = result.loginResult(response);
                            break;
                        case SET_PASSWORD:
                            result = result.setPassword(response);
                            break;
                        case GET_EVENTS:
                            result = result.getEvents(response);
                            break;
                        case REPORT_EVENT:
                            result = result.reportEvent(response);
                            break;
                        case GET_USER_INFO:
                            result = result.getUserInfo(response);
                            break;
                        case GET_ORG_DATA_INFO:
                            result = result.getOrgData(response);
                            break;
                        case UPLOAD_VIDEO:
                            result = result.uploadFile(response);
                            break;
                        case UPLOAD_AUDIO:
                            result = result.uploadFile(response);
                            break;
                        case UPLOAD_IMAGE:
                            result = result.uploadFile(response);
                            break;
                        case SET_COORDINATE:
                            result = result.setCoordinate(response);
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
