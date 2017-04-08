package com.ty.app.yxapp.dwcenter.network;

import android.text.TextUtils;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

import java.io.Serializable;

import retrofit2.Response;

/**
 * Created by kss on 2017/4/3.
 */

public class Result implements Serializable {
    private int code = -1;
    private String message;
    private Object data;
    private static final String LOGIN_SUCCESS = "1";
    private static final String LOGIN_FAIL = "0";

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
        if (code == 1) return true;
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
                    setCode(0);
                    setMessage(AndroidUtils.getString(R.string.login_error));
                    break;
                default:
                    setCode(-1);
                    break;
            }
        }
        return this;
    }

    public Result getEvents(Response response) {
        if (response.body() == null) new Result();
        setCode(1);
        setData(response.body());
        return this;
    }

}
