package com.ty.app.yxapp.dwcenter.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by heli on 2017/4/3.
 */
public class Event  implements Serializable {

    private int code;
    private String msg;
    private List<EventBody> body;
    
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

    public List<EventBody> getBody() {
        return body;
    }

    public void setBody(List<EventBody> body) {
        this.body = body;
    }

    public class EventBody implements Serializable{
        private String event_id;
        private String event_title;
        private String event_mc;
        private String event_sjzt;
        private String event_sjjd;
        private String event_sjly;
        private String event_zdly;
        private String event_x;
        private String event_y;
        private String event_dz;
        private String event_account;
        private String event_xm;
        private String event_lxfs;
        private String event_district_code;
        private String event_town_code;
        private String event_village_code;
        private String event_creattime;
        private String event_tplj;
        private String event_yylj;
        private String event_splj;
        private String event_dlmc;
        private String event_xlmc;
        private String event_lower_role;
        private String event_lower_code;
        private String event_lower_no;
        private List<EventRecord> event_record;

        public String getEvent_id() {
            return event_id;
        }

        public void setEvent_id(String event_id) {
            this.event_id = event_id;
        }

        public String getEvent_title() {
            return event_title;
        }

        public void setEvent_title(String event_title) {
            this.event_title = event_title;
        }

        public String getEvent_mc() {
            return event_mc;
        }

        public void setEvent_mc(String event_mc) {
            this.event_mc = event_mc;
        }

        public String getEvent_sjzt() {
            return event_sjzt;
        }

        public void setEvent_sjzt(String event_sjzt) {
            this.event_sjzt = event_sjzt;
        }

        public String getEvent_sjjd() {
            return event_sjjd;
        }

        public void setEvent_sjjd(String event_sjjd) {
            this.event_sjjd = event_sjjd;
        }

        public String getEvent_sjly() {
            return event_sjly;
        }

        public void setEvent_sjly(String event_sjly) {
            this.event_sjly = event_sjly;
        }

        public String getEvent_zdly() {
            return event_zdly;
        }

        public void setEvent_zdly(String event_zdly) {
            this.event_zdly = event_zdly;
        }

        public String getEvent_x() {
            return event_x;
        }

        public void setEvent_x(String event_x) {
            this.event_x = event_x;
        }

        public String getEvent_y() {
            return event_y;
        }

        public void setEvent_y(String event_y) {
            this.event_y = event_y;
        }

        public String getEvent_dz() {
            return event_dz;
        }

        public void setEvent_dz(String event_dz) {
            this.event_dz = event_dz;
        }

        public String getEvent_account() {
            return event_account;
        }

        public void setEvent_account(String event_account) {
            this.event_account = event_account;
        }

        public String getEvent_xm() {
            return event_xm;
        }

        public void setEvent_xm(String event_xm) {
            this.event_xm = event_xm;
        }

        public String getEvent_lxfs() {
            return event_lxfs;
        }

        public void setEvent_lxfs(String event_lxfs) {
            this.event_lxfs = event_lxfs;
        }

        public String getEvent_district_code() {
            return event_district_code;
        }

        public void setEvent_district_code(String event_district_code) {
            this.event_district_code = event_district_code;
        }

        public String getEvent_town_code() {
            return event_town_code;
        }

        public void setEvent_town_code(String event_town_code) {
            this.event_town_code = event_town_code;
        }

        public String getEvent_village_code() {
            return event_village_code;
        }

        public void setEvent_village_code(String event_village_code) {
            this.event_village_code = event_village_code;
        }

        public String getEvent_creattime() {
            return event_creattime;
        }

        public void setEvent_creattime(String event_creattime) {
            this.event_creattime = event_creattime;
        }

        public String getEvent_tplj() {
            return event_tplj;
        }

        public void setEvent_tplj(String event_tplj) {
            this.event_tplj = event_tplj;
        }

        public String getEvent_yylj() {
            return event_yylj;
        }

        public void setEvent_yylj(String event_yylj) {
            this.event_yylj = event_yylj;
        }

        public String getEvent_splj() {
            return event_splj;
        }

        public void setEvent_splj(String event_splj) {
            this.event_splj = event_splj;
        }

        public String getEvent_dlmc() {
            return event_dlmc;
        }

        public void setEvent_dlmc(String event_dlmc) {
            this.event_dlmc = event_dlmc;
        }

        public String getEvent_xlmc() {
            return event_xlmc;
        }

        public void setEvent_xlmc(String event_xlmc) {
            this.event_xlmc = event_xlmc;
        }

        public String getEvent_lower_role() {
            return event_lower_role;
        }

        public void setEvent_lower_role(String event_lower_role) {
            this.event_lower_role = event_lower_role;
        }

        public String getEvent_lower_code() {
            return event_lower_code;
        }

        public void setEvent_lower_code(String event_lower_code) {
            this.event_lower_code = event_lower_code;
        }

        public String getEvent_lower_no() {
            return event_lower_no;
        }

        public void setEvent_lower_no(String event_lower_no) {
            this.event_lower_no = event_lower_no;
        }

        public List<EventRecord> getEvent_record() {
            return event_record;
        }

        public void setEvent_record(List<EventRecord> event_record) {
            this.event_record = event_record;
        }
    }

    public class EventRecord implements Serializable{
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
