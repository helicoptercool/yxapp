package com.ty.app.yxapp.dwcenter.bean;

import java.util.List;

/**
 * Created by heli on 17-4-17.
 */
public class Task {
    private int code;
    private String msg;
    private List<TaskBody> body;

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

    public List<TaskBody> getBody() {
        return body;
    }

    public void setBody(List<TaskBody> body) {
        this.body = body;
    }

    public class TaskBody{
        private String account;
        private String ms;
        private String bj;
        private String gcjx;
        private String gcjy;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getMs() {
            return ms;
        }

        public void setMs(String ms) {
            this.ms = ms;
        }

        public String getBj() {
            return bj;
        }

        public void setBj(String bj) {
            this.bj = bj;
        }

        public String getGcjx() {
            return gcjx;
        }

        public void setGcjx(String gcjx) {
            this.gcjx = gcjx;
        }

        public String getGcjy() {
            return gcjy;
        }

        public void setGcjy(String gcjy) {
            this.gcjy = gcjy;
        }
    }
}
