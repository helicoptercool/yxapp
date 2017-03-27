package com.ty.app.yxapp.dwcenter.ui.activities.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by kss on 2017/3/26.
 */

public class MyApplication extends Application {
    public static Handler handler;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){
        handler = new Handler();
        context = getApplicationContext();
    }

}
