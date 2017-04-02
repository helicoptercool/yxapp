package com.ty.app.yxapp.dwcenter.ui.activities.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;

/**
 * Created by kss on 2017/3/26.
 */

public class MyApplication extends Application {
    public static Handler handler;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
        init();
    }

    private void init(){
        handler = new Handler();
        context = getApplicationContext();
    }

}
