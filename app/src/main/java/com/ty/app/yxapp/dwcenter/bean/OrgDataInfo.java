package com.ty.app.yxapp.dwcenter.bean;

import java.util.List;

/**
 * Created by heli on 2017/4/8.
 */
public class OrgDataInfo {
    private int code;
    private String msg;
    private OrgDataBody body;

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

    public OrgDataBody getBody() {
        return body;
    }

    public void setBody(OrgDataBody body) {
        this.body = body;
    }

    private class OrgDataBody {
        private String id;
        private String name;
        private String type;
        private String pid;
        private List<User> users;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }
    }
}
