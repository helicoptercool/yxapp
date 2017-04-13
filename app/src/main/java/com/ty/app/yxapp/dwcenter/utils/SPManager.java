package com.ty.app.yxapp.dwcenter.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.ui.activities.base.MyApplication;

/**
 * Created by heli on 17-4-6.
 */
public class SPManager {
    private SharedPreferences preferences;

    public SPManager() {
        preferences = MyApplication.context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
    }

    public void writeSp(String key, String value) {
        preferences.edit().putString(key,value).apply();
    }

    public void writeSp(String key, boolean value){
        preferences.edit().putBoolean(key,value).apply();
    }

    public String readSp(String key){
        return preferences.getString(key,"");
    }

    public boolean isLogin(){
        return preferences.getBoolean(Constants.SP_IS_LOGIN,false);
    }

    public void clearSp(){
        preferences.edit().clear().apply();
    }
}
