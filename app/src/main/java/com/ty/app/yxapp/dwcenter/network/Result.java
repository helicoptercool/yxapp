package com.ty.app.yxapp.dwcenter.network;

import android.text.TextUtils;

import java.io.Serializable;

import retrofit2.Response;

/**
 * Created by kss on 2017/4/3.
 */

public class Result implements Serializable {
    private int code;
    private String message;
    private Object data;

    public Result(){
        code = -1;
    }

    public Result(int code, String message){
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

    public boolean isOK(){
        if(code == 0) return true;
        return false;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result LoginResult(Response response){
        if(response.body() == null) new Result();
        setData(response.body().toString());
        if(!TextUtils.isEmpty(response.body().toString()) &&
                "1".equals(response.body().toString())) setCode(0);
        else setCode(-1);
        return this;
    }

    public Result getEvents(Response response){
        if(response.body() == null) new Result();
        setCode(0);
        setData(response.body());
        return this;
    }

}
