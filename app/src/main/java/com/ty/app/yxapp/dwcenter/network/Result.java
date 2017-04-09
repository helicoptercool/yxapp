package com.ty.app.yxapp.dwcenter.network;

import android.text.TextUtils;
import android.util.Log;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.bean.Event;
import com.ty.app.yxapp.dwcenter.bean.FileUpload;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

import java.io.Serializable;

import retrofit2.Response;

/**
 * Created by kss on 2017/4/3.
 */

public class Result implements Serializable {
    private static final String TAG = Result.class.getSimpleName();
    private int code = -1;
    private String message;
    private Object data;
    public static final String LOGIN_SUCCESS = "1";
    private static final String LOGIN_FAIL = "-98";


    public Result() {
        code = -1;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public boolean isOK() {
        Log.e(TAG,""+code);
        if (code == 0) return true;
        return false;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result LoginResult(Response response) {
        if (response.body() == null) new Result();
        setData(response.body().toString());
        if(!TextUtils.isEmpty(response.body().toString())){
            switch (response.body().toString()){
                case LOGIN_SUCCESS:
                    setCode(1);
                    break;
                case LOGIN_FAIL:
                    setCode(-98);
                    setMessage(AndroidUtils.getString(R.string.login_error));
                    break;
                default:
//                    setCode(-1);
                    break;
            }
        }
        return this;
    }

    public Result getEvents(Response response) {
        if (response.body() == null) new Result();
        setCode(0);
        setData(((Event)response.body()).getBody());
        return this;
    }

    public Result uploadFile(Response response){
        if(response.body() != null){
            setCode(0);
            setData(((FileUpload)response.body()).getData());
            return this;
        }
        return this;
    }

}
