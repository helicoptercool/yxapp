package com.ty.app.yxapp.dwcenter.network;

import android.util.Log;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.bean.Event;
import com.ty.app.yxapp.dwcenter.bean.EventUpload;
import com.ty.app.yxapp.dwcenter.bean.FileUpload;
import com.ty.app.yxapp.dwcenter.bean.OnlyStrResult;
import com.ty.app.yxapp.dwcenter.bean.OrgDataInfo;
import com.ty.app.yxapp.dwcenter.bean.StringResult;
import com.ty.app.yxapp.dwcenter.bean.UserInfo;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

import java.io.Serializable;
import java.io.StringReader;

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
    public static final int CODE_SUCCESS = 0;


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
        Log.e(TAG, "" + code);
        if (code == 0) return true;
        return false;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result register(Response response) {
        StringResult result = (StringResult) response.body();
        if (result != null) {

            setCode(result.getCode());
            setMessage(result.getMsg());
        }
        return this;
    }

    public Result loginResult(Response response) {
/*        StringResult result = (StringResult) response.body();
        if (result != null) {
            setCode(result.getCode());
            setMessage(result.getMsg());
            setData(response.body().toString());
        }
        return this;*/

        String loginResult =  response.body().toString().trim();
        if (LOGIN_SUCCESS.equals(loginResult)) {
            setCode(CODE_SUCCESS);
            setMessage(AndroidUtils.getString(R.string.success));
        } else {
            setCode(-1);
            setMessage(AndroidUtils.getString(R.string.login_error));
        }
        return this;
    }

    public Result setPassword(Response response) {
        StringResult result = (StringResult) response.body();
        if (result != null) {
            setCode(result.getCode());
            setMessage(result.getMsg());
            setData(result.getBody());
        }
        return this;
    }

    public Result getEvents(Response response) {
        if (response.body() != null) {
            setCode(((Event) response.body()).getCode());
            setMessage(((Event) response.body()).getMsg());
            setData(((Event) response.body()).getBody());
        }
        return this;
    }

    public Result reportEvent(Response response) {
        EventUpload result = (EventUpload) response.body();
        if (result != null) {
            setCode(result.getCode());
            setMessage(result.getMsg());
            setData(result.getBody());
        }
        return this;
    }

    public Result getUserInfo(Response response) {
        UserInfo result = (UserInfo) response.body();
        if (result != null) {
            setCode(result.getCode());
            setMessage(result.getMsg());
            setData(result.getBody());
        }
        return this;
    }

    public Result getOrgData(Response response) {
        Log.e(TAG,response.message()+"----"+response.body());
        OrgDataInfo result = (OrgDataInfo) response.body();
        if (result != null) {
            setCode(result.getCode());
            setMessage(result.getMsg());
            setData(result.getBody());
        }
        return this;
    }


    public Result uploadFile(Response response) {
        FileUpload result = (FileUpload) response.body();
        if (result != null) {
            if (result.isResult()) {
                setCode(CODE_SUCCESS);
                setMessage(result.getMsg());
                setData(result.getData());
            }
        }
        return this;
    }

    public Result setCoordinate(Response response){
        StringResult result = (StringResult) response.body();
        if(result != null){
            setCode(result.getCode());
            setMessage(result.getMsg());
            setData(result.getBody());
        }
        return this;
    }

}
