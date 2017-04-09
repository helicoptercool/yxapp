package com.ty.app.yxapp.dwcenter.bean;

import java.util.List;

/**
 * Created by heli on 2017/4/9.
 */
public class User {
    private String id;
    private String userName;
    private String loginName;
    private String passwd;
    private String operType;
    private List department;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public List getDepartment() {
        return department;
    }

    public void setDepartment(List department) {
        this.department = department;
    }
}
