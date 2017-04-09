package com.ty.app.yxapp.dwcenter.bean;

/**
 * Created by heli on 2017/4/8.
 */
public class EventUpload {
    private int code;
    private String msg;
    private UploadBody body;

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

    public UploadBody getBody() {
        return body;
    }

    public void setBody(UploadBody body) {
        this.body = body;
    }

    private class UploadBody {
        private String account;
        private String eventType;
        private String address;
        private String eventX;
        private String eventY;
        private String eventMs;
        private String eventMc;
        private String eventId;
        private String eventClyj;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getEventType() {
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEventX() {
            return eventX;
        }

        public void setEventX(String eventX) {
            this.eventX = eventX;
        }

        public String getEventY() {
            return eventY;
        }

        public void setEventY(String eventY) {
            this.eventY = eventY;
        }

        public String getEventMs() {
            return eventMs;
        }

        public void setEventMs(String eventMs) {
            this.eventMs = eventMs;
        }

        public String getEventMc() {
            return eventMc;
        }

        public void setEventMc(String eventMc) {
            this.eventMc = eventMc;
        }

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getEventClyj() {
            return eventClyj;
        }

        public void setEventClyj(String eventClyj) {
            this.eventClyj = eventClyj;
        }
    }
}
