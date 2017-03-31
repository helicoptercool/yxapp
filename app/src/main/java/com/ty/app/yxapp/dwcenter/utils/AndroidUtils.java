package com.ty.app.yxapp.dwcenter.utils;

import android.widget.Toast;

import com.ty.app.yxapp.dwcenter.ui.activities.base.MyApplication;

/**
 * Created by kss on 2017/3/26.
 */

public class AndroidUtils {

    private static float density = 1;

    static {
        density = MyApplication.context.getResources().getDisplayMetrics().density;
    }

    public static int dp(float value) {
        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }

    public static String getString(int res){
        return MyApplication.context.getString(res);
    }

    public static void ShowToast(String message){
        Toast.makeText(MyApplication.context,message,Toast.LENGTH_SHORT).show();
    }
}
