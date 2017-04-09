package com.ty.app.yxapp.dwcenter.bean;

/**
 * Created by heli on 2017/4/8.
 */
public class EventReport {

    private int code;
    private String msg;
    private RepordBody body;

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

    public RepordBody getBody() {
        return body;
    }

    public void setBody(RepordBody body) {
        this.body = body;
    }

    private class RepordBody {
        private String record_account;
        private String record_xm;
        private String record_czsj;
        private String record_clyj;
        private String record_district_code;
        private String record_town_code;
        private String record_village_code;
        private String record_role;
        private String record_sjzt;
        private String record_sjjd;
        private String record_tplj;
        private String record_yylj;
        private String record_splj;

        public String getRecord_account() {
            return record_account;
        }

        public void setRecord_account(String record_account) {
            this.record_account = record_account;
        }

        public String getRecord_xm() {
            return record_xm;
        }

        public void setRecord_xm(String record_xm) {
            this.record_xm = record_xm;
        }

        public String getRecord_czsj() {
            return record_czsj;
        }

        public void setRecord_czsj(String record_czsj) {
            this.record_czsj = record_czsj;
        }

        public String getRecord_clyj() {
            return record_clyj;
        }

        public void setRecord_clyj(String record_clyj) {
            this.record_clyj = record_clyj;
        }

        public String getRecord_district_code() {
            return record_district_code;
        }

        public void setRecord_district_code(String record_district_code) {
            this.record_district_code = record_district_code;
        }

        public String getRecord_town_code() {
            return record_town_code;
        }

        public void setRecord_town_code(String record_town_code) {
            this.record_town_code = record_town_code;
        }

        public String getRecord_village_code() {
            return record_village_code;
        }

        public void setRecord_village_code(String record_village_code) {
            this.record_village_code = record_village_code;
        }

        public String getRecord_role() {
            return record_role;
        }

        public void setRecord_role(String record_role) {
            this.record_role = record_role;
        }

        public String getRecord_sjzt() {
            return record_sjzt;
        }

        public void setRecord_sjzt(String record_sjzt) {
            this.record_sjzt = record_sjzt;
        }

        public String getRecord_sjjd() {
            return record_sjjd;
        }

        public void setRecord_sjjd(String record_sjjd) {
            this.record_sjjd = record_sjjd;
        }

        public String getRecord_tplj() {
            return record_tplj;
        }

        public void setRecord_tplj(String record_tplj) {
            this.record_tplj = record_tplj;
        }

        public String getRecord_yylj() {
            return record_yylj;
        }

        public void setRecord_yylj(String record_yylj) {
            this.record_yylj = record_yylj;
        }

        public String getRecord_splj() {
            return record_splj;
        }

        public void setRecord_splj(String record_splj) {
            this.record_splj = record_splj;
        }
    }

}
