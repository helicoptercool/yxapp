package com.ty.app.yxapp.dwcenter.bean;

/**
 * Created by heli on 2017/4/3.
 */
public class StringResult {
    private int code;
    private String msg;
    private LoginBody body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LoginBody getBody() {
        return body;
    }

    public void setBody(LoginBody body) {
        this.body = body;
    }

    private class LoginBody {

        private String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
