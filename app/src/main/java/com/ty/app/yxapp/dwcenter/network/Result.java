package com.ty.app.yxapp.dwcenter.network;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.Serializable;

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

    public Result LoginResult(String res){
        setData(res);
        if(!TextUtils.isEmpty(res) && "1".equals(res)) setCode(0);
        else setCode(-1);
        return new Result();
    }

}
